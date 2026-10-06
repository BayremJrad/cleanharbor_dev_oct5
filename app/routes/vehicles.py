import sys
import os

# Add the Python SDK to the path
sys.path.insert(
    0,
    os.path.join(
        os.path.dirname(__file__),
        "..",
        "..",
        "postman",
        "sdks",
        "Collections SDKs",
        "Vehicle Service Collection",
        "python",
    ),
)

from flask import Blueprint, request, jsonify

from config import USE_BACKEND, BACKEND_URL

vehicles_bp = Blueprint("vehicles", __name__)

# ---------------------------------------------------------------------------
# In-memory store (used when USE_BACKEND=false)
# ---------------------------------------------------------------------------
_next_id = 4
_store = {
    1: {
        "id": 1,
        "nickName": "Daily Driver",
        "vin": "1HGBH41JXMN109186",
        "make": "Honda",
        "model": "Civic",
        "year": "2021",
        "miles": 15000,
    },
    2: {
        "id": 2,
        "nickName": "Weekend Cruiser",
        "vin": "2T1BURHE0JC043821",
        "make": "Toyota",
        "model": "Corolla",
        "year": "2019",
        "miles": 32000,
    },
    3: {
        "id": 3,
        "nickName": "Work Truck",
        "vin": "1FTFW1ET5DFC10312",
        "make": "Ford",
        "model": "F-150",
        "year": "2020",
        "miles": 48000,
    },
}

# ---------------------------------------------------------------------------
# SDK client (lazy-initialised when USE_BACKEND=true)
# ---------------------------------------------------------------------------
_sdk_client = None


def _get_sdk_client():
    global _sdk_client
    if _sdk_client is None:
        from vehicle_service_sdk import VehicleServiceClient

        _sdk_client = VehicleServiceClient(base_url=BACKEND_URL, timeout=10)
    return _sdk_client


# ---------------------------------------------------------------------------
# Helper
# ---------------------------------------------------------------------------
def _error(message, status=500):
    return jsonify({"error": message}), status


# ===========================================================================
# GET /vehicles
# ===========================================================================
@vehicles_bp.route("/vehicles", methods=["GET"])
def list_vehicles():
    brand = request.args.get("brand")

    if USE_BACKEND:
        try:
            from vehicle_service_sdk.exceptions import ApiError

            client = _get_sdk_client()
            vehicles = client.vehicles.get_all(brand=brand)
            return jsonify([v.__dict__ if hasattr(v, "__dict__") else v for v in vehicles]), 200
        except Exception as exc:
            return _error(str(exc), 500)

    # In-memory mode
    vehicles = list(_store.values())
    if brand:
        vehicles = [v for v in vehicles if v.get("make", "").lower() == brand.lower()]
    return jsonify(vehicles), 200


# ===========================================================================
# POST /vehicles
# ===========================================================================
@vehicles_bp.route("/vehicles", methods=["POST"])
def create_vehicle():
    data = request.get_json(silent=True) or {}

    required = ["vin", "make", "model", "year", "miles"]
    missing = [f for f in required if f not in data or data[f] is None]
    if missing:
        return _error(f"Missing required field(s): {', '.join(missing)}", 400)

    if USE_BACKEND:
        try:
            from vehicle_service_sdk import VehicleServiceClient
            from vehicle_service_sdk.models import CreateVehicleRequest
            from vehicle_service_sdk.exceptions import ConflictError, BadRequestError, ApiError

            client = _get_sdk_client()
            payload = CreateVehicleRequest(
                vin=data["vin"],
                make=data["make"],
                model=data["model"],
                year=data["year"],
                miles=data["miles"],
                nick_name=data.get("nickName"),
            )
            vehicle = client.vehicles.create(payload)
            return jsonify(vehicle.__dict__ if hasattr(vehicle, "__dict__") else vehicle), 201
        except ConflictError as exc:
            return _error(str(exc), 409)
        except BadRequestError as exc:
            return _error(str(exc), 400)
        except Exception as exc:
            return _error(str(exc), 500)

    # In-memory mode
    global _next_id
    vin = data["vin"]
    if any(v["vin"] == vin for v in _store.values()):
        return _error(f"Vehicle with VIN '{vin}' already exists.", 409)

    vehicle = {
        "id": _next_id,
        "nickName": data.get("nickName", ""),
        "vin": vin,
        "make": data["make"],
        "model": data["model"],
        "year": str(data["year"]),
        "miles": int(data["miles"]),
    }
    _store[_next_id] = vehicle
    _next_id += 1
    return jsonify(vehicle), 201


# ===========================================================================
# GET /vehicles/:id
# ===========================================================================
@vehicles_bp.route("/vehicles/<int:vehicle_id>", methods=["GET"])
def get_vehicle(vehicle_id):
    if USE_BACKEND:
        try:
            from vehicle_service_sdk.exceptions import NotFoundError, ApiError

            client = _get_sdk_client()
            vehicle = client.vehicles.get(vehicle_id)
            return jsonify(vehicle.__dict__ if hasattr(vehicle, "__dict__") else vehicle), 200
        except NotFoundError:
            return _error(f"Vehicle with id {vehicle_id} not found.", 404)
        except Exception as exc:
            return _error(str(exc), 500)

    # In-memory mode
    vehicle = _store.get(vehicle_id)
    if vehicle is None:
        return _error(f"Vehicle with id {vehicle_id} not found.", 404)
    return jsonify(vehicle), 200


# ===========================================================================
# PATCH /vehicles/:id
# ===========================================================================
@vehicles_bp.route("/vehicles/<int:vehicle_id>", methods=["PATCH"])
def update_vehicle(vehicle_id):
    data = request.get_json(silent=True) or {}

    if USE_BACKEND:
        try:
            from vehicle_service_sdk.models import UpdateVehicleRequest
            from vehicle_service_sdk.exceptions import NotFoundError, BadRequestError, ApiError

            client = _get_sdk_client()
            payload = UpdateVehicleRequest(
                nick_name=data.get("nickName"),
                vin=data.get("vin"),
                make=data.get("make"),
                model=data.get("model"),
                year=data.get("year"),
                miles=data.get("miles"),
            )
            vehicle = client.vehicles.update(vehicle_id, payload)
            return jsonify(vehicle.__dict__ if hasattr(vehicle, "__dict__") else vehicle), 200
        except NotFoundError:
            return _error(f"Vehicle with id {vehicle_id} not found.", 404)
        except BadRequestError as exc:
            return _error(str(exc), 400)
        except Exception as exc:
            return _error(str(exc), 500)

    # In-memory mode
    vehicle = _store.get(vehicle_id)
    if vehicle is None:
        return _error(f"Vehicle with id {vehicle_id} not found.", 404)

    allowed = {"nickName", "vin", "make", "model", "year", "miles"}
    for key, value in data.items():
        if key in allowed:
            vehicle[key] = value

    # Check VIN uniqueness if VIN is being updated
    new_vin = data.get("vin")
    if new_vin and any(v["vin"] == new_vin and v["id"] != vehicle_id for v in _store.values()):
        return _error(f"Vehicle with VIN '{new_vin}' already exists.", 409)

    _store[vehicle_id] = vehicle
    return jsonify(vehicle), 200


# ===========================================================================
# DELETE /vehicles/:id
# ===========================================================================
@vehicles_bp.route("/vehicles/<int:vehicle_id>", methods=["DELETE"])
def delete_vehicle(vehicle_id):
    if USE_BACKEND:
        try:
            from vehicle_service_sdk.exceptions import NotFoundError, ApiError

            client = _get_sdk_client()
            client.vehicles.delete(vehicle_id)
            return "", 204
        except NotFoundError:
            return _error(f"Vehicle with id {vehicle_id} not found.", 404)
        except Exception as exc:
            return _error(str(exc), 500)

    # In-memory mode
    if vehicle_id not in _store:
        return _error(f"Vehicle with id {vehicle_id} not found.", 404)
    del _store[vehicle_id]
    return "", 204
