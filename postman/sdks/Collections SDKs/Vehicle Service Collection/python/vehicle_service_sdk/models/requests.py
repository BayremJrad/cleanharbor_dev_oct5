"""
Request body models for create and update operations.
"""

from dataclasses import dataclass
from typing import Optional


@dataclass
class CreateVehicleRequest:
    """
    Request body for ``POST /vehicles``.

    Required fields (``vin``, ``make``, ``model``, ``year``, ``miles``) must be
    provided; ``nick_name`` is optional.

    Attributes:
        vin:       Vehicle Identification Number (required).
        make:      Manufacturer / brand, e.g. ``"Honda"`` (required).
        model:     Model name, e.g. ``"Accord"`` (required).
        year:      Model year as a string, e.g. ``"2020"`` (required).
        miles:     Odometer reading in miles (required).
        nick_name: Optional human-friendly nickname.
    """

    vin: str
    make: str
    model: str
    year: str
    miles: int
    nick_name: Optional[str] = None

    def to_dict(self) -> dict:
        """Serialize to a JSON-compatible dict, omitting ``None`` values."""
        payload: dict = {
            "vin": self.vin,
            "make": self.make,
            "model": self.model,
            "year": self.year,
            "miles": self.miles,
        }
        if self.nick_name is not None:
            payload["nickName"] = self.nick_name
        return payload


@dataclass
class UpdateVehicleRequest:
    """
    Request body for ``PATCH /vehicles/:id``.

    All fields are optional — only the fields you set will be sent to the
    server, allowing partial updates.

    Attributes:
        nick_name: New nickname.
        vin:       New VIN.
        make:      New manufacturer / brand.
        model:     New model name.
        year:      New model year.
        miles:     New odometer reading.
    """

    nick_name: Optional[str] = None
    vin: Optional[str] = None
    make: Optional[str] = None
    model: Optional[str] = None
    year: Optional[str] = None
    miles: Optional[int] = None

    def to_dict(self) -> dict:
        """Serialize to a JSON-compatible dict, omitting ``None`` values."""
        payload: dict = {}
        if self.nick_name is not None:
            payload["nickName"] = self.nick_name
        if self.vin is not None:
            payload["vin"] = self.vin
        if self.make is not None:
            payload["make"] = self.make
        if self.model is not None:
            payload["model"] = self.model
        if self.year is not None:
            payload["year"] = self.year
        if self.miles is not None:
            payload["miles"] = self.miles
        return payload
