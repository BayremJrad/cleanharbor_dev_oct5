"""
VehiclesService — wraps all /vehicles endpoints.
"""

from typing import List, Optional

import requests

from ..config import SdkConfig
from ..exceptions import ApiError, BadRequestError, ConflictError, NotFoundError, ServerError
from ..models.requests import CreateVehicleRequest, UpdateVehicleRequest
from ..models.vehicle import Vehicle


class VehiclesService:
    """
    Provides CRUD operations for the ``/vehicles`` resource.

    Do not instantiate directly — access via :attr:`VehicleServiceClient.vehicles`.
    """

    def __init__(self, config: SdkConfig) -> None:
        self._config = config

    # ------------------------------------------------------------------
    # Internal helpers
    # ------------------------------------------------------------------

    def _url(self, path: str = "") -> str:
        """Build a full URL from the configured base URL and a relative path."""
        return f"{self._config.base_url}/vehicles{path}"

    def _default_headers(self) -> dict:
        headers = {"Content-Type": "application/json", "Accept": "application/json"}
        headers.update(self._config.headers)
        return headers

    def _raise_for_status(self, response: requests.Response) -> None:
        """Map HTTP error status codes to typed SDK exceptions."""
        if response.ok:
            return

        body = ""
        try:
            body = response.text
        except Exception:
            pass

        try:
            detail = response.json()
            message = detail.get("message") or detail.get("error") or str(detail)
        except Exception:
            message = body or response.reason or "Unknown error"

        status = response.status_code
        if status == 400:
            raise BadRequestError(message=message, response_body=body)
        if status == 404:
            raise NotFoundError(message=message, response_body=body)
        if status == 409:
            raise ConflictError(message=message, response_body=body)
        if status >= 500:
            raise ServerError(message=message, status_code=status, response_body=body)
        raise ApiError(message=message, status_code=status, response_body=body)

    # ------------------------------------------------------------------
    # Public API
    # ------------------------------------------------------------------

    def get_all(self, brand: Optional[str] = None) -> List[Vehicle]:
        """
        Retrieve all vehicles.

        Args:
            brand: Optional brand/make filter (e.g. ``"Honda"``).

        Returns:
            A list of :class:`~vehicle_service_sdk.models.Vehicle` objects.

        Raises:
            ApiError: On any non-2xx response.
        """
        params = {}
        if brand is not None:
            params["brand"] = brand

        response = requests.get(
            self._url(),
            params=params,
            headers=self._default_headers(),
            timeout=self._config.timeout,
        )
        self._raise_for_status(response)
        return [Vehicle.from_dict(item) for item in response.json()]

    def create(self, request: CreateVehicleRequest) -> Vehicle:
        """
        Create a new vehicle.

        Args:
            request: A :class:`~vehicle_service_sdk.models.CreateVehicleRequest`
                     with the vehicle details.

        Returns:
            The newly created :class:`~vehicle_service_sdk.models.Vehicle`.

        Raises:
            BadRequestError: If a required field is missing.
            ConflictError:   If a vehicle with the same VIN already exists.
            ApiError:        On any other non-2xx response.
        """
        response = requests.post(
            self._url(),
            json=request.to_dict(),
            headers=self._default_headers(),
            timeout=self._config.timeout,
        )
        self._raise_for_status(response)
        return Vehicle.from_dict(response.json())

    def get(self, vehicle_id: int) -> Vehicle:
        """
        Retrieve a single vehicle by its ID.

        Args:
            vehicle_id: The integer ID of the vehicle.

        Returns:
            The matching :class:`~vehicle_service_sdk.models.Vehicle`.

        Raises:
            NotFoundError: If no vehicle with that ID exists.
            ApiError:      On any other non-2xx response.
        """
        response = requests.get(
            self._url(f"/{vehicle_id}"),
            headers=self._default_headers(),
            timeout=self._config.timeout,
        )
        self._raise_for_status(response)
        return Vehicle.from_dict(response.json())

    def update(self, vehicle_id: int, request: UpdateVehicleRequest) -> Vehicle:
        """
        Partially update a vehicle (PATCH semantics — only supplied fields change).

        Args:
            vehicle_id: The integer ID of the vehicle to update.
            request:    An :class:`~vehicle_service_sdk.models.UpdateVehicleRequest`
                        containing only the fields to change.

        Returns:
            The updated :class:`~vehicle_service_sdk.models.Vehicle`.

        Raises:
            NotFoundError: If no vehicle with that ID exists.
            ConflictError: If the new VIN conflicts with an existing vehicle.
            ApiError:      On any other non-2xx response.
        """
        response = requests.patch(
            self._url(f"/{vehicle_id}"),
            json=request.to_dict(),
            headers=self._default_headers(),
            timeout=self._config.timeout,
        )
        self._raise_for_status(response)
        return Vehicle.from_dict(response.json())

    def delete(self, vehicle_id: int) -> None:
        """
        Delete a vehicle by its ID.

        Args:
            vehicle_id: The integer ID of the vehicle to delete.

        Returns:
            ``None`` on success.

        Raises:
            NotFoundError: If no vehicle with that ID exists.
            ApiError:      On any other non-2xx response.
        """
        response = requests.delete(
            self._url(f"/{vehicle_id}"),
            headers=self._default_headers(),
            timeout=self._config.timeout,
        )
        self._raise_for_status(response)
