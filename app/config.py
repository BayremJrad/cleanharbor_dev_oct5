import os

PORT = int(os.environ.get("PORT", 3000))
BACKEND_URL = os.environ.get("BACKEND_URL", "http://localhost:8080")
USE_BACKEND = os.environ.get("USE_BACKEND", "false").lower() == "true"
