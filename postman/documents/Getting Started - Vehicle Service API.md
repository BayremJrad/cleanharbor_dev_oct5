# Getting Started — Vehicle Service API

Welcome to the **Vehicle Service API**. This guide will walk you through everything you need to know to configure your environment and start sending successful requests using the **Vehicle Service Collection**.

---

## Prerequisites

Before you begin, make sure you have:

- Access to the **Vehicle Service** Postman workspace
- The **Vehicle Service Collection** available in your sidebar
- The **Mock Environment** selected as your active environment

---

## Step 1 — Select the Mock Environment

All requests in this collection use the `{{baseUrl}}` variable to construct endpoint URLs. This variable is defined in the **Mock Environment** and points to the hosted mock server:

```
https://e6bee6c3-e027-478c-986d-8b4b12923c25.mock.pstmn.io
```

**To activate the environment:**

1. Open the **Vehicle Service** workspace.
2. In the top-right corner of Postman, click the environment dropdown (it may show "No Environment").
3. Select **Mock Environment** from the list.

Once selected, `{{baseUrl}}` will automatically resolve to the mock server URL in every request.

---

## Step 2 — Authentication

**No authentication is required.** All endpoints in this collection are open — there are no API keys, tokens, or credentials to configure. You can send requests immediately after setting the environment.

---

## Step 3 — Send Your First Request

A great starting point is **Get all vehicles**, which returns the full list of vehicle records.

1. Open the **Vehicle Service Collection** in the sidebar.
2. Click **Get all vehicles**.
3. Click **Send**.
4. You should receive a `200 OK` response with a JSON array of vehicle records.

> **Tip:** The `brand` query parameter is optional. Remove it or change its value to filter results by manufacturer (e.g. `Toyota`, `Ford`).

---

## Step 4 — Create a Vehicle

To add a new vehicle to the registry:

1. Open the **Create a vehicle** request.
2. Review the JSON body — it contains a sample vehicle. Edit the fields as needed:

```json
{
  "nickName": "The Lisa Marie",
  "vin": "4M2DV11W4RDJ53329",
  "make": "Mercury",
  "model": "Villager",
  "year": "1994",
  "miles": 159864
}
```

| Field | Required | Notes |
|-------|----------|-------|
| `vin` | Yes | Must be a unique 17-character VIN |
| `make` | Yes | Manufacturer name |
| `model` | Yes | Model name |
| `year` | Yes | Four-digit year as a string |
| `miles` | Yes | Odometer reading as a number |
| `nickName` | No | Optional friendly label |

3. Click **Send**.
4. A successful response returns `201 Created` with the new vehicle object.

**Common errors:**
- `400` — A required field is missing from the body.
- `409` — A vehicle with that VIN already exists.

---

## Step 5 — Retrieve, Update, and Delete a Vehicle

All three operations require a vehicle `:id` path parameter. Copy the `id` value from a `201` or `200` response and paste it into the path parameter field.

### Retrieve a vehicle
- **Method:** `GET /vehicles/:id`
- Returns the full record for a single vehicle.
- Returns `404` if the ID does not exist.

### Update a vehicle
- **Method:** `PATCH /vehicles/:id`
- Send only the fields you want to change in the JSON body.
- Returns `200` with the updated record on success.

### Delete a vehicle
- **Method:** `DELETE /vehicles/:id`
- Returns `204 No Content` on success (no response body).

---

## Request Summary

| Request | Method | Endpoint | Success Code |
|---------|--------|----------|--------------|
| Create a vehicle | `POST` | `/vehicles` | `201` |
| Retrieve a vehicle | `GET` | `/vehicles/:id` | `200` |
| Update a vehicle | `PATCH` | `/vehicles/:id` | `200` |
| Delete a vehicle | `DELETE` | `/vehicles/:id` | `204` |
| Get all vehicles | `GET` | `/vehicles` | `200` |

---

## Troubleshooting

| Symptom | Likely cause | Fix |
|---------|-------------|-----|
| `Could not send request` / network error | No environment selected | Select **Mock Environment** from the environment dropdown |
| `{{baseUrl}}` appears literally in the URL | Environment not active | Confirm **Mock Environment** is selected |
| `400 Bad Request` on Create | Missing required field | Check that `vin`, `make`, `model`, `year`, and `miles` are all present |
| `409 Conflict` on Create | Duplicate VIN | Use a different VIN value |
| `404 Not Found` on Retrieve/Update/Delete | Wrong or stale ID | Copy the `id` from a fresh Create or Get All response |

---

## Next Steps

- Explore the saved **examples** on each request to see what real responses look like.
- Run the full collection using the **Collection Runner** to execute all requests in sequence.
- Check out the **Vehicle Service QA Testing** collection for automated test coverage.