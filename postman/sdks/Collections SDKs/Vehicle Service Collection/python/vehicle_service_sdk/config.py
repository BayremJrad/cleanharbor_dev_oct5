"""
SDK configuration dataclass.
"""

from dataclasses import dataclass, field


@dataclass
class SdkConfig:
    """
    Configuration for the VehicleServiceClient.

    Attributes:
        base_url: The base URL of the Vehicle Service API
                  (e.g. ``"http://localhost:3000"``).
        timeout:  HTTP request timeout in seconds. Defaults to ``10``.
        headers:  Additional HTTP headers to include in every request.
    """

    base_url: str
    timeout: int = 10
    headers: dict = field(default_factory=dict)

    def __post_init__(self) -> None:
        # Strip trailing slash so callers can safely append paths like "/vehicles"
        self.base_url = self.base_url.rstrip("/")
