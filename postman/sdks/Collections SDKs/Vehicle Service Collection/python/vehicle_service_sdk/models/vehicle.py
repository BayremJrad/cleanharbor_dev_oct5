"""
Vehicle domain model.
"""

from dataclasses import dataclass
from typing import Optional


@dataclass
class Vehicle:
    """
    Represents a vehicle returned by the Vehicle Service API.

    Attributes:
        id:        Unique identifier assigned by the server.
        nick_name: Optional human-friendly nickname for the vehicle.
        vin:       Vehicle Identification Number.
        make:      Manufacturer / brand (e.g. ``"Honda"``).
        model:     Model name (e.g. ``"Accord"``).
        year:      Model year as a string (e.g. ``"2020"``).
        miles:     Current odometer reading in miles.
    """

    id: Optional[int] = None
    nick_name: Optional[str] = None
    vin: Optional[str] = None
    make: Optional[str] = None
    model: Optional[str] = None
    year: Optional[str] = None
    miles: Optional[int] = None

    @classmethod
    def from_dict(cls, data: dict) -> "Vehicle":
        """Deserialize a JSON response dict into a :class:`Vehicle` instance."""
        return cls(
            id=data.get("id"),
            nick_name=data.get("nickName"),
            vin=data.get("vin"),
            make=data.get("make"),
            model=data.get("model"),
            year=data.get("year"),
            miles=data.get("miles"),
        )

    def to_dict(self) -> dict:
        """Serialize this instance to a JSON-compatible dict (excludes ``None`` values)."""
        result = {}
        if self.id is not None:
            result["id"] = self.id
        if self.nick_name is not None:
            result["nickName"] = self.nick_name
        if self.vin is not None:
            result["vin"] = self.vin
        if self.make is not None:
            result["make"] = self.make
        if self.model is not None:
            result["model"] = self.model
        if self.year is not None:
            result["year"] = self.year
        if self.miles is not None:
            result["miles"] = self.miles
        return result

    def __repr__(self) -> str:
        return (
            f"Vehicle(id={self.id!r}, make={self.make!r}, model={self.model!r}, "
            f"year={self.year!r}, vin={self.vin!r})"
        )
