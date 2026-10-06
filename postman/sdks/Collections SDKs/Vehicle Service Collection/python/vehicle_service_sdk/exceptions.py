"""
Typed exceptions for the Vehicle Service SDK.

Hierarchy:
    ApiError
    ├── BadRequestError  (HTTP 400)
    ├── NotFoundError    (HTTP 404)
    ├── ConflictError    (HTTP 409)
    └── ServerError      (HTTP 5xx)
"""


class ApiError(Exception):
    """Base exception for all Vehicle Service API errors."""

    def __init__(self, message: str, status_code: int = 0, response_body: str = "") -> None:
        super().__init__(message)
        self.status_code = status_code
        self.response_body = response_body

    def __str__(self) -> str:
        return f"[HTTP {self.status_code}] {super().__str__()}"


class BadRequestError(ApiError):
    """
    Raised when the server returns HTTP 400 Bad Request.

    Typically indicates a missing required field or an invalid value
    in the request body.
    """

    def __init__(self, message: str = "Bad Request — missing or invalid field",
                 response_body: str = "") -> None:
        super().__init__(message, status_code=400, response_body=response_body)


class NotFoundError(ApiError):
    """
    Raised when the server returns HTTP 404 Not Found.

    Indicates that the requested vehicle ID does not exist.
    """

    def __init__(self, message: str = "Resource not found",
                 response_body: str = "") -> None:
        super().__init__(message, status_code=404, response_body=response_body)


class ConflictError(ApiError):
    """
    Raised when the server returns HTTP 409 Conflict.

    Typically indicates that a vehicle with the same VIN already exists.
    """

    def __init__(self, message: str = "Conflict — VIN already exists",
                 response_body: str = "") -> None:
        super().__init__(message, status_code=409, response_body=response_body)


class ServerError(ApiError):
    """
    Raised when the server returns HTTP 5xx.

    Indicates an unexpected server-side failure.
    """

    def __init__(self, message: str = "Internal Server Error",
                 status_code: int = 500, response_body: str = "") -> None:
        super().__init__(message, status_code=status_code, response_body=response_body)
