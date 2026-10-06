# VehicleServiceCollectionSdkService

A list of all methods in the `VehicleServiceCollectionSdkService` service. Click on the method name to view detailed information about that method.

| Methods                                 | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| :-------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [createAVehicle](#createavehicle)       | Creates a new vehicle record in the registry. **Request body** (`application/json`): \| Field \| Type \| Required \| Description \| \|-------\|------\|----------\|-------------\| \| `nickName` \| string \| No \| A friendly name for the vehicle \| \| `vin` \| string \| Yes \| Vehicle Identification Number (17 characters) \| \| `make` \| string \| Yes \| Manufacturer (e.g. `Ford`, `Toyota`) \| \| `model` \| string \| Yes \| Model name (e.g. `Mustang`, `Camry`) \| \| `year` \| string \| Yes \| Four-digit model year (e.g. `"2024"`) \| \| `miles` \| number \| Yes \| Current odometer reading in miles \| **Responses:** - `201 Created` — Vehicle successfully created - `400 Bad Request` — A required attribute is missing - `409 Conflict` — A vehicle with the same VIN already exists - `500 Internal Server Error` — Unexpected server error             |
| [retrieveAVehicle](#retrieveavehicle)   | Retrieves a single vehicle record by its unique ID. **Path parameter:** \| Parameter \| Description \| \|-----------\|-------------\| \| `:id` \| The unique identifier of the vehicle to retrieve \| **Responses:** - `200 OK` — Vehicle record returned successfully - `404 Not Found` — No vehicle exists with the given ID - `500 Internal Server Error` — Unexpected server error                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| [updateACollection](#updateacollection) | Partially updates an existing vehicle record. Only the fields included in the request body will be modified. **Path parameter:** \| Parameter \| Description \| \|-----------\|-------------\| \| `:id` \| The unique identifier of the vehicle to update \| **Request body** (`application/json`) — all fields optional: \| Field \| Type \| Description \| \|-------\|------\|-------------\| \| `nickName` \| string \| Updated friendly name \| \| `vin` \| string \| Updated VIN \| \| `make` \| string \| Updated manufacturer \| \| `model` \| string \| Updated model name \| \| `year` \| string \| Updated model year \| \| `miles` \| number \| Updated odometer reading \| **Responses:** - `200 OK` — Vehicle updated successfully - `400 Bad Request` — Request body is malformed or contains invalid values - `500 Internal Server Error` — Unexpected server error |
| [deleteAVehicle](#deleteavehicle)       | Permanently removes a vehicle record from the registry. **Path parameter:** \| Parameter \| Description \| \|-----------\|-------------\| \| `:id` \| The unique identifier of the vehicle to delete \| **Responses:** - `204 No Content` — Vehicle deleted successfully (no response body) - `500 Internal Server Error` — Unexpected server error                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| [getAllVehicles](#getallvehicles)       | Returns a list of all vehicle records. Supports optional filtering by brand. **Query parameters:** \| Parameter \| Type \| Required \| Description \| \|-----------\|------\|----------\|-------------\| \| `brand` \| string \| No \| Filter results to vehicles matching this brand/make (e.g. `Honda`) \| **Responses:** - `200 OK` — List of vehicles returned successfully - `500 Internal Server Error` — Unexpected server error                                                                                                                                                                                                                                                                                                                                                                                                                                            |

## createAVehicle

Creates a new vehicle record in the registry. **Request body** (`application/json`): \| Field \| Type \| Required \| Description \| \|-------\|------\|----------\|-------------\| \| `nickName` \| string \| No \| A friendly name for the vehicle \| \| `vin` \| string \| Yes \| Vehicle Identification Number (17 characters) \| \| `make` \| string \| Yes \| Manufacturer (e.g. `Ford`, `Toyota`) \| \| `model` \| string \| Yes \| Model name (e.g. `Mustang`, `Camry`) \| \| `year` \| string \| Yes \| Four-digit model year (e.g. `"2024"`) \| \| `miles` \| number \| Yes \| Current odometer reading in miles \| **Responses:** - `201 Created` — Vehicle successfully created - `400 Bad Request` — A required attribute is missing - `409 Conflict` — A vehicle with the same VIN already exists - `500 Internal Server Error` — Unexpected server error

- HTTP Method: `POST`
- Endpoint: `/vehicles`

**Parameters**

| Name                  | Type                                                        | Required | Description  |
| :-------------------- | :---------------------------------------------------------- | :------- | :----------- |
| createAVehicleRequest | [CreateAVehicleRequest](../models/CreateAVehicleRequest.md) | ✅       | Request Body |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;
import com.vehicleservicecollectionsdk.models.CreateAVehicleRequest;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    CreateAVehicleRequest createAVehicleRequest = CreateAVehicleRequest.builder()
      .nickName("The Lisa Marie")
      .vin("4M2DV11W4RDJ53329")
      .make("Mercury")
      .model("Villager")
      .year("1994")
      .miles(159864L)
      .build();

    Object response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.createAVehicle(
      createAVehicleRequest
    );

    System.out.println(response);
  }
}

```

## retrieveAVehicle

Retrieves a single vehicle record by its unique ID. **Path parameter:** \| Parameter \| Description \| \|-----------\|-------------\| \| `:id` \| The unique identifier of the vehicle to retrieve \| **Responses:** - `200 OK` — Vehicle record returned successfully - `404 Not Found` — No vehicle exists with the given ID - `500 Internal Server Error` — Unexpected server error

- HTTP Method: `GET`
- Endpoint: `/vehicles/{id}`

**Parameters**

| Name | Type   | Required | Description |
| :--- | :----- | :------- | :---------- |
| id   | String | ✅       |             |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    Object response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.retrieveAVehicle(
      "{{vehicleId}}"
    );

    System.out.println(response);
  }
}

```

## updateACollection

Partially updates an existing vehicle record. Only the fields included in the request body will be modified. **Path parameter:** \| Parameter \| Description \| \|-----------\|-------------\| \| `:id` \| The unique identifier of the vehicle to update \| **Request body** (`application/json`) — all fields optional: \| Field \| Type \| Description \| \|-------\|------\|-------------\| \| `nickName` \| string \| Updated friendly name \| \| `vin` \| string \| Updated VIN \| \| `make` \| string \| Updated manufacturer \| \| `model` \| string \| Updated model name \| \| `year` \| string \| Updated model year \| \| `miles` \| number \| Updated odometer reading \| **Responses:** - `200 OK` — Vehicle updated successfully - `400 Bad Request` — Request body is malformed or contains invalid values - `500 Internal Server Error` — Unexpected server error

- HTTP Method: `PATCH`
- Endpoint: `/vehicles/{id}`

**Parameters**

| Name                  | Type                                                        | Required | Description  |
| :-------------------- | :---------------------------------------------------------- | :------- | :----------- |
| id                    | String                                                      | ✅       |              |
| createAVehicleRequest | [CreateAVehicleRequest](../models/CreateAVehicleRequest.md) | ✅       | Request Body |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;
import com.vehicleservicecollectionsdk.models.CreateAVehicleRequest;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    CreateAVehicleRequest createAVehicleRequest = CreateAVehicleRequest.builder()
      .nickName("The Lisa Marie")
      .vin("4M2DV11W4RDJ53329")
      .make("Mercury")
      .model("Villager")
      .year("1994")
      .miles(159864L)
      .build();

    Object response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.updateACollection(
      "{{vehicleId}}",
      createAVehicleRequest
    );

    System.out.println(response);
  }
}

```

## deleteAVehicle

Permanently removes a vehicle record from the registry. **Path parameter:** \| Parameter \| Description \| \|-----------\|-------------\| \| `:id` \| The unique identifier of the vehicle to delete \| **Responses:** - `204 No Content` — Vehicle deleted successfully (no response body) - `500 Internal Server Error` — Unexpected server error

- HTTP Method: `DELETE`
- Endpoint: `/vehicles/{id}`

**Parameters**

| Name | Type   | Required | Description |
| :--- | :----- | :------- | :---------- |
| id   | String | ✅       |             |

**Return Type**

`String`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    String response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.deleteAVehicle(
      "{{vehicleId}}"
    );

    System.out.println(response);
  }
}

```

## getAllVehicles

Returns a list of all vehicle records. Supports optional filtering by brand. **Query parameters:** \| Parameter \| Type \| Required \| Description \| \|-----------\|------\|----------\|-------------\| \| `brand` \| string \| No \| Filter results to vehicles matching this brand/make (e.g. `Honda`) \| **Responses:** - `200 OK` — List of vehicles returned successfully - `500 Internal Server Error` — Unexpected server error

- HTTP Method: `GET`
- Endpoint: `/vehicles`

**Parameters**

| Name              | Type                                                              | Required | Description               |
| :---------------- | :---------------------------------------------------------------- | :------- | :------------------------ |
| requestParameters | [GetAllVehiclesParameters](../models/GetAllVehiclesParameters.md) | ❌       | Request Parameters Object |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.vehicleservicecollectionsdk.VehicleServiceCollectionSdk;
import com.vehicleservicecollectionsdk.config.ApiKeyAuthConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;
import com.vehicleservicecollectionsdk.models.GetAllVehiclesParameters;

public class Main {

  public static void main(String[] args) {
    VehicleServiceCollectionSdkConfig config = VehicleServiceCollectionSdkConfig.builder()
      .apiKeyAuthConfig(ApiKeyAuthConfig.builder().apiKey("YOUR_API_KEY").build())
      .build();

    VehicleServiceCollectionSdk vehicleServiceCollectionSdk = new VehicleServiceCollectionSdk(
      config
    );

    GetAllVehiclesParameters requestParameters = GetAllVehiclesParameters.builder()
      .brand("Honda")
      .build();

    Object response = vehicleServiceCollectionSdk.vehicleServiceCollectionSdk.getAllVehicles(
      requestParameters
    );

    System.out.println(response);
  }
}

```
