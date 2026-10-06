import sys
import os

# Ensure the app package root is on the path so sibling modules resolve
sys.path.insert(0, os.path.dirname(__file__))

from flask import Flask
from routes.vehicles import vehicles_bp
from config import PORT, BACKEND_URL, USE_BACKEND

app = Flask(__name__)
app.register_blueprint(vehicles_bp)


if __name__ == "__main__":
    mode = f"backend → {BACKEND_URL}" if USE_BACKEND else "in-memory store"
    print(f"🚗  Vehicle Service API starting on port {PORT}  [{mode}]")
    app.run(host="0.0.0.0", port=PORT, debug=False)
