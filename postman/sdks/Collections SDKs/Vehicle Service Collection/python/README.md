# Vehicle Service Python SDK

A Python client library for the Vehicle Service API.

## Installation

```bash
pip install vehicle-service-sdk
```

Or install from source:

```bash
cd postman/sdks/Collections\ SDKs/Vehicle\ Service\ Collection/python
pip install -e .
```

## Requirements

- Python 3.7+
- `requests` library

## Quick Start

```python
from vehicle_service_sdk import VehicleServiceClient

# Initialize the client
client = VehicleServiceClient(base_url="http://localhost:3000")

# Get all vehicles
vehicles = client.vehicles.get_all()
for vehicle in vehicles:
    print(f"{vehicle.id}: {vehicle.make} {vehicle.model} ({vehicle.year})")

# Get vehicles filtered by brand
hondas = client.vehicles.get_all(brand="Honda")

# Create a new vehicle
from vehicle_service_sdk.models import CreateVehicleRequest

new_vehicle = client.vehicles.create(
    CreateVehicleRequest(
        vin="1HGCM82633A123456",
        make="Honda",
        model="Accord",
        year="2020",
        miles=15000,
        nick_name="My Honda"
    )
)
print(f"Created vehicle with ID: {new_vehicle.id}")

# Get a vehicle by ID
vehicle = client.vehicles.get(vehicle_id=new_vehicle.id)
print(f"Retrieved: {vehicle.nick_name}")

# Update a vehicle
from vehicle_service_sdk.models import UpdateVehicleRequest

updated = client.vehicles.update(
    vehicle_id=new_vehicle.id,
    request=UpdateVehicleRequest(miles=16000)
)

# Delete a vehicle
client.vehicles.delete(vehicle_id=new_vehicle.id)
```

## Configuration

```python
from vehicle_service_sdk import VehicleServiceClient

client = VehicleServiceClient(
    base_url="https://api.example.com",
    timeout=30  # seconds (default: 10)
)
```

## Error Handling

```python
from vehicle_service_sdk import VehicleServiceClient
from vehicle_service_sdk.exceptions import (
    NotFoundError,
    ConflictError,
    BadRequestError,
    ApiError
)

client = VehicleServiceClient(base_url="http://localhost:3000")

try:
    vehicle = client.vehicles.get(vehicle_id=999)
except NotFoundError as e:
    print(f"Vehicle not found: {e}")
except ConflictError as e:
    print(f"VIN already exists: {e}")
except BadRequestError as e:
    print(f"Invalid request: {e}")
except ApiError as e:
    print(f"API error {e.status_code}: {e}")
```

## API Reference

### `VehicleServiceClient`

| Parameter  | Type  | Default | Description              |
|------------|-------|---------|--------------------------|
| `base_url` | `str` | —       | Base URL of the API      |
| `timeout`  | `int` | `10`    | Request timeout (seconds)|

### `VehiclesService` (`client.vehicles`)

| Method                              | Description                        |
|-------------------------------------|------------------------------------|
| `get_all(brand=None)`               | List all vehicles, optionally filtered by brand |
| `create(request)`                   | Create a new vehicle               |
| `get(vehicle_id)`                   | Get a vehicle by ID                |
| `update(vehicle_id, request)`       | Partially update a vehicle         |
| `delete(vehicle_id)`                | Delete a vehicle by ID             |

### Models

#### `Vehicle`
| Field       | Type           | Description          |
|-------------|----------------|----------------------|
| `id`        | `Optional[int]`| Vehicle ID           |
| `nick_name` | `Optional[str]`| Nickname             |
| `vin`       | `Optional[str]`| VIN number           |
| `make`      | `Optional[str]`| Manufacturer         |
| `model`     | `Optional[str]`| Model name           |
| `year`      | `Optional[str]`| Model year           |
| `miles`     | `Optional[int]`| Odometer reading     |

#### `CreateVehicleRequest`
| Field       | Type           | Required | Description      |
|-------------|----------------|----------|------------------|
| `vin`       | `str`          | ✅       | VIN number       |
| `make`      | `str`          | ✅       | Manufacturer     |
| `model`     | `str`          | ✅       | Model name       |
| `year`      | `str`          | ✅       | Model year       |
| `miles`     | `int`          | ✅       | Odometer reading |
| `nick_name` | `Optional[str]`| ❌       | Nickname         |

#### `UpdateVehicleRequest`
All fields are optional — only provided fields will be updated.

### Exceptions

| Exception        | HTTP Status | Description                    |
|------------------|-------------|--------------------------------|
| `BadRequestError`| 400         | Missing or invalid field       |
| `NotFoundError`  | 404         | Vehicle not found              |
| `ConflictError`  | 409         | VIN already exists             |
| `ApiError`       | 5xx / other | General API error              |
