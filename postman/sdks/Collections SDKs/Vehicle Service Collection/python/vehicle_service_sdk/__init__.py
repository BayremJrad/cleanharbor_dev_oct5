"""
vehicle_service_sdk — Python SDK for the Vehicle Service API.

Quick start::

    from vehicle_service_sdk import VehicleServiceClient
    from vehicle_service_sdk.models import CreateVehicleRequest, UpdateVehicleRequest
    from vehicle_service_sdk.exceptions import NotFoundError, ConflictError

    client = VehicleServiceClient(base_url="http://localhost:3000")

    vehicles = client.vehicles.get_all()
    vehicle  = client.vehicles.get(vehicle_id=1)
"""

from .client import VehicleServiceClient
from .config import SdkConfig
from .exceptions import ApiError, BadRequestError, ConflictError, NotFoundError, ServerError
from .models import CreateVehicleRequest, UpdateVehicleRequest, Vehicle

__version__ = "1.0.0"

__all__ = [
    # Client
    "VehicleServiceClient",
    # Config
    "SdkConfig",
    # Models
    "Vehicle",
    "CreateVehicleRequest",
    "UpdateVehicleRequest",
    # Exceptions
    "ApiError",
    "BadRequestError",
    "NotFoundError",
    "ConflictError",
    "ServerError",
]
