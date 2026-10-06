# Vehicle Service API — Flask App

A Flask REST API server that implements all five Vehicle Service endpoints.  
It can run in two modes:

| Mode | Description |
|------|-------------|
| **In-memory** (default) | Uses a built-in dict store — no backend required. Pre-seeded with 3 sample vehicles. |
| **Backend proxy** | Forwards every request to a real backend via the Python SDK. |

---

## Directory structure

```
app/
├── README.md
├── requirements.txt
├── main.py          # Entry point
├── config.py        # Environment-variable config
└── routes/
    ├── __init__.py
    └── vehicles.py  # All /vehicles route handlers
```

---

## Installation

```bash
# From the repo root
cd app
python -m venv .venv
source .venv/bin/activate      # Windows: .venv\Scripts\activate
pip install -r requirements.txt
```

---

## Running the server

### In-memory mode (default)

```bash
python main.py
```

### Backend-proxy mode

```bash
USE_BACKEND=true BACKEND_URL=http://localhost:8080 python main.py
```

---

## Environment variables

| Variable | Default | Description |
|----------|---------|-------------|
| `PORT` | `3000` | Port the Flask server listens on |
| `BACKEND_URL` | `http://localhost:8080` | Base URL of the real Vehicle Service backend |
| `USE_BACKEND` | `false` | Set to `true` to proxy requests via the Python SDK |

---

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `GET` | `/vehicles` | List all vehicles (optional `?brand=` filter) |
| `POST` | `/vehicles` | Create a new vehicle |
| `GET` | `/vehicles/:id` | Get a vehicle by ID |
| `PATCH` | `/vehicles/:id` | Partially update a vehicle |
| `DELETE` | `/vehicles/:id` | Delete a vehicle |

### Vehicle model

```json
{
  "id": 1,
  "nickName": "Daily Driver",
  "vin": "1HGBH41JXMN109186",
  "make": "Honda",
  "model": "Civic",
  "year": "2021",
  "miles": 15000
}
```

---

## Example curl commands

### List all vehicles
```bash
curl http://localhost:3000/vehicles
```

### Filter by brand
```bash
curl "http://localhost:3000/vehicles?brand=Honda"
```

### Get a vehicle by ID
```bash
curl http://localhost:3000/vehicles/1
```

### Create a vehicle
```bash
curl -X POST http://localhost:3000/vehicles \
  -H "Content-Type: application/json" \
  -d '{
    "nickName": "My Truck",
    "vin": "3VWFE21C04M000001",
    "make": "Volkswagen",
    "model": "Golf",
    "year": "2022",
    "miles": 5000
  }'
```

### Update a vehicle (partial)
```bash
curl -X PATCH http://localhost:3000/vehicles/1 \
  -H "Content-Type: application/json" \
  -d '{"miles": 16500}'
```

### Delete a vehicle
```bash
curl -X DELETE http://localhost:3000/vehicles/1
```

---

## Error responses

All errors return JSON in the form:

```json
{ "error": "Human-readable message" }
```

| Status | Meaning |
|--------|---------|
| `400` | Missing or invalid request body |
| `404` | Vehicle not found |
| `409` | VIN already exists |
| `500` | Unexpected server error |
