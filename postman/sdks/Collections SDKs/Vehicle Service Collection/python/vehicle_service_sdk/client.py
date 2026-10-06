"""
Main entry point for the Vehicle Service SDK.
"""

from .config import SdkConfig
from .services.vehicles import VehiclesService


class VehicleServiceClient:
    """
    Top-level client for the Vehicle Service API.

    Usage::

        from vehicle_service_sdk import VehicleServiceClient

        client = VehicleServiceClient(base_url="http://localhost:3000")

        # List all vehicles
        vehicles = client.vehicles.get_all()

        # Create a vehicle
        from vehicle_service_sdk.models import CreateVehicleRequest
        new_vehicle = client.vehicles.create(
            CreateVehicleRequest(
                vin="1HGCM82633A123456",
                make="Honda",
                model="Accord",
                year="2020",
                miles=15000,
            )
        )

    Attributes:
        vehicles: :class:`~vehicle_service_sdk.services.VehiclesService` instance
                  providing all CRUD operations for the ``/vehicles`` resource.
    """

    def __init__(self, base_url: str, timeout: int = 10) -> None:
        """
        Initialize the client.

        Args:
            base_url: Base URL of the Vehicle Service API
                      (e.g. ``"http://localhost:3000"``).
                      A trailing slash is stripped automatically.
            timeout:  HTTP request timeout in seconds. Defaults to ``10``.
        """
        self._config = SdkConfig(base_url=base_url, timeout=timeout)
        self._vehicles = VehiclesService(self._config)

    @property
    def vehicles(self) -> VehiclesService:
        """Access the vehicles service for CRUD operations on ``/vehicles``."""
        return self._vehicles
