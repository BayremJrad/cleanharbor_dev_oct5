package com.vehicleservicecollectionsdk.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vehicleservicecollectionsdk.config.RequestConfig;
import com.vehicleservicecollectionsdk.config.VehicleServiceCollectionSdkConfig;
import com.vehicleservicecollectionsdk.exceptions.ApiError;
import com.vehicleservicecollectionsdk.http.Environment;
import com.vehicleservicecollectionsdk.http.HttpMethod;
import com.vehicleservicecollectionsdk.http.ModelConverter;
import com.vehicleservicecollectionsdk.http.VehicleServiceCollectionSdkResponse;
import com.vehicleservicecollectionsdk.http.util.RequestBuilder;
import com.vehicleservicecollectionsdk.models.CreateAVehicleRequest;
import com.vehicleservicecollectionsdk.models.GetAllVehiclesParameters;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * VehicleServiceCollectionSdkService Service
 */
public class VehicleServiceCollectionSdkService extends BaseService {

  private RequestConfig createAVehicleConfig;
  private RequestConfig retrieveAVehicleConfig;
  private RequestConfig updateACollectionConfig;
  private RequestConfig deleteAVehicleConfig;
  private RequestConfig getAllVehiclesConfig;

  /**
   * Constructs a new instance of VehicleServiceCollectionSdkService.
   *
   * @param httpClient The HTTP client to use for requests
   * @param config The SDK configuration
   */
  public VehicleServiceCollectionSdkService(
    @NonNull OkHttpClient httpClient,
    VehicleServiceCollectionSdkConfig config
  ) {
    super(httpClient, config);
  }

  /**
   * Sets method-level configuration for {@code createAVehicle}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public VehicleServiceCollectionSdkService setCreateAVehicleConfig(RequestConfig config) {
    this.createAVehicleConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code retrieveAVehicle}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public VehicleServiceCollectionSdkService setRetrieveAVehicleConfig(RequestConfig config) {
    this.retrieveAVehicleConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code updateACollection}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public VehicleServiceCollectionSdkService setUpdateACollectionConfig(RequestConfig config) {
    this.updateACollectionConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code deleteAVehicle}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public VehicleServiceCollectionSdkService setDeleteAVehicleConfig(RequestConfig config) {
    this.deleteAVehicleConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code getAllVehicles}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public VehicleServiceCollectionSdkService setGetAllVehiclesConfig(RequestConfig config) {
    this.getAllVehiclesConfig = config;
    return this;
  }

  /**
   * Creates a new vehicle record in the registry.
   *
   * **Request body** (`application/json`):
   *
   * | Field | Type | Required | Description |
   * |-------|------|----------|-------------|
   * | `nickName` | string | No | A friendly name for the vehicle |
   * | `vin` | string | Yes | Vehicle Identification Number (17 characters) |
   * | `make` | string | Yes | Manufacturer (e.g. `Ford`, `Toyota`) |
   * | `model` | string | Yes | Model name (e.g. `Mustang`, `Camry`) |
   * | `year` | string | Yes | Four-digit model year (e.g. `"2024"`) |
   * | `miles` | number | Yes | Current odometer reading in miles |
   *
   * **Responses:**
   * - `201 Created` — Vehicle successfully created
   * - `400 Bad Request` — A required attribute is missing
   * - `409 Conflict` — A vehicle with the same VIN already exists
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
   * @return response of {@code Object}
   */
  public Object createAVehicle(@NonNull CreateAVehicleRequest createAVehicleRequest)
    throws ApiError {
    return this.createAVehicle(createAVehicleRequest, null);
  }

  /**
   * Creates a new vehicle record in the registry.
   *
   * **Request body** (`application/json`):
   *
   * | Field | Type | Required | Description |
   * |-------|------|----------|-------------|
   * | `nickName` | string | No | A friendly name for the vehicle |
   * | `vin` | string | Yes | Vehicle Identification Number (17 characters) |
   * | `make` | string | Yes | Manufacturer (e.g. `Ford`, `Toyota`) |
   * | `model` | string | Yes | Model name (e.g. `Mustang`, `Camry`) |
   * | `year` | string | Yes | Four-digit model year (e.g. `"2024"`) |
   * | `miles` | number | Yes | Current odometer reading in miles |
   *
   * **Responses:**
   * - `201 Created` — Vehicle successfully created
   * - `400 Bad Request` — A required attribute is missing
   * - `409 Conflict` — A vehicle with the same VIN already exists
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
   * @return response of {@code Object}
   */
  public Object createAVehicle(
    @NonNull CreateAVehicleRequest createAVehicleRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().createAVehicle(createAVehicleRequest, requestConfig).getData();
  }

  /**
   * Creates a new vehicle record in the registry.
   *
   * **Request body** (`application/json`):
   *
   * | Field | Type | Required | Description |
   * |-------|------|----------|-------------|
   * | `nickName` | string | No | A friendly name for the vehicle |
   * | `vin` | string | Yes | Vehicle Identification Number (17 characters) |
   * | `make` | string | Yes | Manufacturer (e.g. `Ford`, `Toyota`) |
   * | `model` | string | Yes | Model name (e.g. `Mustang`, `Camry`) |
   * | `year` | string | Yes | Four-digit model year (e.g. `"2024"`) |
   * | `miles` | number | Yes | Current odometer reading in miles |
   *
   * **Responses:**
   * - `201 Created` — Vehicle successfully created
   * - `400 Bad Request` — A required attribute is missing
   * - `409 Conflict` — A vehicle with the same VIN already exists
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> createAVehicleAsync(
    @NonNull CreateAVehicleRequest createAVehicleRequest
  ) throws ApiError {
    return this.createAVehicleAsync(createAVehicleRequest, null);
  }

  /**
   * Creates a new vehicle record in the registry.
   *
   * **Request body** (`application/json`):
   *
   * | Field | Type | Required | Description |
   * |-------|------|----------|-------------|
   * | `nickName` | string | No | A friendly name for the vehicle |
   * | `vin` | string | Yes | Vehicle Identification Number (17 characters) |
   * | `make` | string | Yes | Manufacturer (e.g. `Ford`, `Toyota`) |
   * | `model` | string | Yes | Model name (e.g. `Mustang`, `Camry`) |
   * | `year` | string | Yes | Four-digit model year (e.g. `"2024"`) |
   * | `miles` | number | Yes | Current odometer reading in miles |
   *
   * **Responses:**
   * - `201 Created` — Vehicle successfully created
   * - `400 Bad Request` — A required attribute is missing
   * - `409 Conflict` — A vehicle with the same VIN already exists
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> createAVehicleAsync(
    @NonNull CreateAVehicleRequest createAVehicleRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .createAVehicleAsync(createAVehicleRequest, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildCreateAVehicleRequest(
    @NonNull CreateAVehicleRequest createAVehicleRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "vehicles"
    )
      .setApiKeyAuth(resolveApiKeyAuthConfig(resolvedConfig))
      .setJsonContent(createAVehicleRequest)
      .build();
  }

  /**
   * Retrieves a single vehicle record by its unique ID.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to retrieve |
   *
   * **Responses:**
   * - `200 OK` — Vehicle record returned successfully
   * - `404 Not Found` — No vehicle exists with the given ID
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @return response of {@code Object}
   */
  public Object retrieveAVehicle(@NonNull String id) throws ApiError {
    return this.retrieveAVehicle(id, null);
  }

  /**
   * Retrieves a single vehicle record by its unique ID.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to retrieve |
   *
   * **Responses:**
   * - `200 OK` — Vehicle record returned successfully
   * - `404 Not Found` — No vehicle exists with the given ID
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @return response of {@code Object}
   */
  public Object retrieveAVehicle(@NonNull String id, RequestConfig requestConfig) throws ApiError {
    return withRawResponse().retrieveAVehicle(id, requestConfig).getData();
  }

  /**
   * Retrieves a single vehicle record by its unique ID.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to retrieve |
   *
   * **Responses:**
   * - `200 OK` — Vehicle record returned successfully
   * - `404 Not Found` — No vehicle exists with the given ID
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> retrieveAVehicleAsync(@NonNull String id) throws ApiError {
    return this.retrieveAVehicleAsync(id, null);
  }

  /**
   * Retrieves a single vehicle record by its unique ID.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to retrieve |
   *
   * **Responses:**
   * - `200 OK` — Vehicle record returned successfully
   * - `404 Not Found` — No vehicle exists with the given ID
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> retrieveAVehicleAsync(
    @NonNull String id,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .retrieveAVehicleAsync(id, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildRetrieveAVehicleRequest(@NonNull String id, RequestConfig resolvedConfig) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "vehicles/{id}"
    )
      .setApiKeyAuth(resolveApiKeyAuthConfig(resolvedConfig))
      .setPathParameter("id", id)
      .build();
  }

  /**
   * Partially updates an existing vehicle record. Only the fields included in the request body will be modified.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to update |
   *
   * **Request body** (`application/json`) — all fields optional:
   *
   * | Field | Type | Description |
   * |-------|------|-------------|
   * | `nickName` | string | Updated friendly name |
   * | `vin` | string | Updated VIN |
   * | `make` | string | Updated manufacturer |
   * | `model` | string | Updated model name |
   * | `year` | string | Updated model year |
   * | `miles` | number | Updated odometer reading |
   *
   * **Responses:**
   * - `200 OK` — Vehicle updated successfully
   * - `400 Bad Request` — Request body is malformed or contains invalid values
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
   * @return response of {@code Object}
   */
  public Object updateACollection(
    @NonNull String id,
    @NonNull CreateAVehicleRequest createAVehicleRequest
  ) throws ApiError {
    return this.updateACollection(id, createAVehicleRequest, null);
  }

  /**
   * Partially updates an existing vehicle record. Only the fields included in the request body will be modified.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to update |
   *
   * **Request body** (`application/json`) — all fields optional:
   *
   * | Field | Type | Description |
   * |-------|------|-------------|
   * | `nickName` | string | Updated friendly name |
   * | `vin` | string | Updated VIN |
   * | `make` | string | Updated manufacturer |
   * | `model` | string | Updated model name |
   * | `year` | string | Updated model year |
   * | `miles` | number | Updated odometer reading |
   *
   * **Responses:**
   * - `200 OK` — Vehicle updated successfully
   * - `400 Bad Request` — Request body is malformed or contains invalid values
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
   * @return response of {@code Object}
   */
  public Object updateACollection(
    @NonNull String id,
    @NonNull CreateAVehicleRequest createAVehicleRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().updateACollection(id, createAVehicleRequest, requestConfig).getData();
  }

  /**
   * Partially updates an existing vehicle record. Only the fields included in the request body will be modified.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to update |
   *
   * **Request body** (`application/json`) — all fields optional:
   *
   * | Field | Type | Description |
   * |-------|------|-------------|
   * | `nickName` | string | Updated friendly name |
   * | `vin` | string | Updated VIN |
   * | `make` | string | Updated manufacturer |
   * | `model` | string | Updated model name |
   * | `year` | string | Updated model year |
   * | `miles` | number | Updated odometer reading |
   *
   * **Responses:**
   * - `200 OK` — Vehicle updated successfully
   * - `400 Bad Request` — Request body is malformed or contains invalid values
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> updateACollectionAsync(
    @NonNull String id,
    @NonNull CreateAVehicleRequest createAVehicleRequest
  ) throws ApiError {
    return this.updateACollectionAsync(id, createAVehicleRequest, null);
  }

  /**
   * Partially updates an existing vehicle record. Only the fields included in the request body will be modified.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to update |
   *
   * **Request body** (`application/json`) — all fields optional:
   *
   * | Field | Type | Description |
   * |-------|------|-------------|
   * | `nickName` | string | Updated friendly name |
   * | `vin` | string | Updated VIN |
   * | `make` | string | Updated manufacturer |
   * | `model` | string | Updated model name |
   * | `year` | string | Updated model year |
   * | `miles` | number | Updated odometer reading |
   *
   * **Responses:**
   * - `200 OK` — Vehicle updated successfully
   * - `400 Bad Request` — Request body is malformed or contains invalid values
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> updateACollectionAsync(
    @NonNull String id,
    @NonNull CreateAVehicleRequest createAVehicleRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .updateACollectionAsync(id, createAVehicleRequest, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildUpdateACollectionRequest(
    @NonNull String id,
    @NonNull CreateAVehicleRequest createAVehicleRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.PATCH,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "vehicles/{id}"
    )
      .setApiKeyAuth(resolveApiKeyAuthConfig(resolvedConfig))
      .setPathParameter("id", id)
      .setJsonContent(createAVehicleRequest)
      .build();
  }

  /**
   * Permanently removes a vehicle record from the registry.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to delete |
   *
   * **Responses:**
   * - `204 No Content` — Vehicle deleted successfully (no response body)
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @return response of {@code String}
   */
  public String deleteAVehicle(@NonNull String id) throws ApiError {
    return this.deleteAVehicle(id, null);
  }

  /**
   * Permanently removes a vehicle record from the registry.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to delete |
   *
   * **Responses:**
   * - `204 No Content` — Vehicle deleted successfully (no response body)
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @return response of {@code String}
   */
  public String deleteAVehicle(@NonNull String id, RequestConfig requestConfig) throws ApiError {
    return withRawResponse().deleteAVehicle(id, requestConfig).getData();
  }

  /**
   * Permanently removes a vehicle record from the registry.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to delete |
   *
   * **Responses:**
   * - `204 No Content` — Vehicle deleted successfully (no response body)
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @return response of {@code CompletableFuture<String>}
   */
  public CompletableFuture<String> deleteAVehicleAsync(@NonNull String id) throws ApiError {
    return this.deleteAVehicleAsync(id, null);
  }

  /**
   * Permanently removes a vehicle record from the registry.
   *
   * **Path parameter:**
   *
   * | Parameter | Description |
   * |-----------|-------------|
   * | `:id` | The unique identifier of the vehicle to delete |
   *
   * **Responses:**
   * - `204 No Content` — Vehicle deleted successfully (no response body)
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param id String
   * @return response of {@code CompletableFuture<String>}
   */
  public CompletableFuture<String> deleteAVehicleAsync(
    @NonNull String id,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .deleteAVehicleAsync(id, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildDeleteAVehicleRequest(@NonNull String id, RequestConfig resolvedConfig) {
    return new RequestBuilder(
      HttpMethod.DELETE,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "vehicles/{id}"
    )
      .setApiKeyAuth(resolveApiKeyAuthConfig(resolvedConfig))
      .setPathParameter("id", id)
      .build();
  }

  /**
   * Returns a list of all vehicle records. Supports optional filtering by brand.
   *
   * **Query parameters:**
   *
   * | Parameter | Type | Required | Description |
   * |-----------|------|----------|-------------|
   * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
   *
   * **Responses:**
   * - `200 OK` — List of vehicles returned successfully
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @return response of {@code Object}
   */
  public Object getAllVehicles() throws ApiError {
    return this.getAllVehicles(GetAllVehiclesParameters.builder().build());
  }

  /**
   * Returns a list of all vehicle records. Supports optional filtering by brand.
   *
   * **Query parameters:**
   *
   * | Parameter | Type | Required | Description |
   * |-----------|------|----------|-------------|
   * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
   *
   * **Responses:**
   * - `200 OK` — List of vehicles returned successfully
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param requestParameters {@link GetAllVehiclesParameters} Request Parameters Object
   * @return response of {@code Object}
   */
  public Object getAllVehicles(@NonNull GetAllVehiclesParameters requestParameters)
    throws ApiError {
    return this.getAllVehicles(requestParameters, null);
  }

  /**
   * Returns a list of all vehicle records. Supports optional filtering by brand.
   *
   * **Query parameters:**
   *
   * | Parameter | Type | Required | Description |
   * |-----------|------|----------|-------------|
   * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
   *
   * **Responses:**
   * - `200 OK` — List of vehicles returned successfully
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param requestParameters {@link GetAllVehiclesParameters} Request Parameters Object
   * @return response of {@code Object}
   */
  public Object getAllVehicles(
    @NonNull GetAllVehiclesParameters requestParameters,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().getAllVehicles(requestParameters, requestConfig).getData();
  }

  /**
   * Returns a list of all vehicle records. Supports optional filtering by brand.
   *
   * **Query parameters:**
   *
   * | Parameter | Type | Required | Description |
   * |-----------|------|----------|-------------|
   * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
   *
   * **Responses:**
   * - `200 OK` — List of vehicles returned successfully
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> getAllVehiclesAsync() throws ApiError {
    return this.getAllVehiclesAsync(GetAllVehiclesParameters.builder().build());
  }

  /**
   * Returns a list of all vehicle records. Supports optional filtering by brand.
   *
   * **Query parameters:**
   *
   * | Parameter | Type | Required | Description |
   * |-----------|------|----------|-------------|
   * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
   *
   * **Responses:**
   * - `200 OK` — List of vehicles returned successfully
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param requestParameters {@link GetAllVehiclesParameters} Request Parameters Object
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> getAllVehiclesAsync(
    @NonNull GetAllVehiclesParameters requestParameters
  ) throws ApiError {
    return this.getAllVehiclesAsync(requestParameters, null);
  }

  /**
   * Returns a list of all vehicle records. Supports optional filtering by brand.
   *
   * **Query parameters:**
   *
   * | Parameter | Type | Required | Description |
   * |-----------|------|----------|-------------|
   * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
   *
   * **Responses:**
   * - `200 OK` — List of vehicles returned successfully
   * - `500 Internal Server Error` — Unexpected server error
   *
   * @param requestParameters {@link GetAllVehiclesParameters} Request Parameters Object
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> getAllVehiclesAsync(
    @NonNull GetAllVehiclesParameters requestParameters,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .getAllVehiclesAsync(requestParameters, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildGetAllVehiclesRequest(
    @NonNull GetAllVehiclesParameters requestParameters,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "vehicles"
    )
      .setApiKeyAuth(resolveApiKeyAuthConfig(resolvedConfig))
      .setOptionalQueryParameter("brand", requestParameters.getBrand())
      .build();
  }

  /**
   * Returns an accessor whose methods mirror this service but return the full HTTP response
   * (status code, headers, and raw body) wrapped alongside the parsed data.
   *
   * @return An accessor exposing raw-response variants of this service's methods
   */
  public WithRawResponse withRawResponse() {
    return new WithRawResponse();
  }

  /**
   * Per-call accessor exposing raw-response variants of {@link VehicleServiceCollectionSdkService}'s methods.
   * Reuses the enclosing service's request builders and configuration.
   */
  public class WithRawResponse {

    /**
     * Creates a new vehicle record in the registry.
     *
     * **Request body** (`application/json`):
     *
     * | Field | Type | Required | Description |
     * |-------|------|----------|-------------|
     * | `nickName` | string | No | A friendly name for the vehicle |
     * | `vin` | string | Yes | Vehicle Identification Number (17 characters) |
     * | `make` | string | Yes | Manufacturer (e.g. `Ford`, `Toyota`) |
     * | `model` | string | Yes | Model name (e.g. `Mustang`, `Camry`) |
     * | `year` | string | Yes | Four-digit model year (e.g. `"2024"`) |
     * | `miles` | number | Yes | Current odometer reading in miles |
     *
     * **Responses:**
     * - `201 Created` — Vehicle successfully created
     * - `400 Bad Request` — A required attribute is missing
     * - `409 Conflict` — A vehicle with the same VIN already exists
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
     * @return response of {@code VehicleServiceCollectionSdkResponse<Object>}
     */
    public VehicleServiceCollectionSdkResponse<Object> createAVehicle(
      @NonNull CreateAVehicleRequest createAVehicleRequest
    ) throws ApiError {
      return this.createAVehicle(createAVehicleRequest, null);
    }

    /**
     * Creates a new vehicle record in the registry.
     *
     * **Request body** (`application/json`):
     *
     * | Field | Type | Required | Description |
     * |-------|------|----------|-------------|
     * | `nickName` | string | No | A friendly name for the vehicle |
     * | `vin` | string | Yes | Vehicle Identification Number (17 characters) |
     * | `make` | string | Yes | Manufacturer (e.g. `Ford`, `Toyota`) |
     * | `model` | string | Yes | Model name (e.g. `Mustang`, `Camry`) |
     * | `year` | string | Yes | Four-digit model year (e.g. `"2024"`) |
     * | `miles` | number | Yes | Current odometer reading in miles |
     *
     * **Responses:**
     * - `201 Created` — Vehicle successfully created
     * - `400 Bad Request` — A required attribute is missing
     * - `409 Conflict` — A vehicle with the same VIN already exists
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
     * @return response of {@code VehicleServiceCollectionSdkResponse<Object>}
     */
    public VehicleServiceCollectionSdkResponse<Object> createAVehicle(
      @NonNull CreateAVehicleRequest createAVehicleRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(createAVehicleConfig, requestConfig);
      Request request = buildCreateAVehicleRequest(createAVehicleRequest, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new VehicleServiceCollectionSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Creates a new vehicle record in the registry.
     *
     * **Request body** (`application/json`):
     *
     * | Field | Type | Required | Description |
     * |-------|------|----------|-------------|
     * | `nickName` | string | No | A friendly name for the vehicle |
     * | `vin` | string | Yes | Vehicle Identification Number (17 characters) |
     * | `make` | string | Yes | Manufacturer (e.g. `Ford`, `Toyota`) |
     * | `model` | string | Yes | Model name (e.g. `Mustang`, `Camry`) |
     * | `year` | string | Yes | Four-digit model year (e.g. `"2024"`) |
     * | `miles` | number | Yes | Current odometer reading in miles |
     *
     * **Responses:**
     * - `201 Created` — Vehicle successfully created
     * - `400 Bad Request` — A required attribute is missing
     * - `409 Conflict` — A vehicle with the same VIN already exists
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<Object>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<Object>> createAVehicleAsync(
      @NonNull CreateAVehicleRequest createAVehicleRequest
    ) throws ApiError {
      return this.createAVehicleAsync(createAVehicleRequest, null);
    }

    /**
     * Creates a new vehicle record in the registry.
     *
     * **Request body** (`application/json`):
     *
     * | Field | Type | Required | Description |
     * |-------|------|----------|-------------|
     * | `nickName` | string | No | A friendly name for the vehicle |
     * | `vin` | string | Yes | Vehicle Identification Number (17 characters) |
     * | `make` | string | Yes | Manufacturer (e.g. `Ford`, `Toyota`) |
     * | `model` | string | Yes | Model name (e.g. `Mustang`, `Camry`) |
     * | `year` | string | Yes | Four-digit model year (e.g. `"2024"`) |
     * | `miles` | number | Yes | Current odometer reading in miles |
     *
     * **Responses:**
     * - `201 Created` — Vehicle successfully created
     * - `400 Bad Request` — A required attribute is missing
     * - `409 Conflict` — A vehicle with the same VIN already exists
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<Object>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<Object>> createAVehicleAsync(
      @NonNull CreateAVehicleRequest createAVehicleRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(createAVehicleConfig, requestConfig);
      Request request = buildCreateAVehicleRequest(createAVehicleRequest, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new VehicleServiceCollectionSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Retrieves a single vehicle record by its unique ID.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to retrieve |
     *
     * **Responses:**
     * - `200 OK` — Vehicle record returned successfully
     * - `404 Not Found` — No vehicle exists with the given ID
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @return response of {@code VehicleServiceCollectionSdkResponse<Object>}
     */
    public VehicleServiceCollectionSdkResponse<Object> retrieveAVehicle(@NonNull String id)
      throws ApiError {
      return this.retrieveAVehicle(id, null);
    }

    /**
     * Retrieves a single vehicle record by its unique ID.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to retrieve |
     *
     * **Responses:**
     * - `200 OK` — Vehicle record returned successfully
     * - `404 Not Found` — No vehicle exists with the given ID
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @return response of {@code VehicleServiceCollectionSdkResponse<Object>}
     */
    public VehicleServiceCollectionSdkResponse<Object> retrieveAVehicle(
      @NonNull String id,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(retrieveAVehicleConfig, requestConfig);
      Request request = buildRetrieveAVehicleRequest(id, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new VehicleServiceCollectionSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Retrieves a single vehicle record by its unique ID.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to retrieve |
     *
     * **Responses:**
     * - `200 OK` — Vehicle record returned successfully
     * - `404 Not Found` — No vehicle exists with the given ID
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<Object>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<Object>> retrieveAVehicleAsync(
      @NonNull String id
    ) throws ApiError {
      return this.retrieveAVehicleAsync(id, null);
    }

    /**
     * Retrieves a single vehicle record by its unique ID.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to retrieve |
     *
     * **Responses:**
     * - `200 OK` — Vehicle record returned successfully
     * - `404 Not Found` — No vehicle exists with the given ID
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<Object>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<Object>> retrieveAVehicleAsync(
      @NonNull String id,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(retrieveAVehicleConfig, requestConfig);
      Request request = buildRetrieveAVehicleRequest(id, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new VehicleServiceCollectionSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Partially updates an existing vehicle record. Only the fields included in the request body will be modified.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to update |
     *
     * **Request body** (`application/json`) — all fields optional:
     *
     * | Field | Type | Description |
     * |-------|------|-------------|
     * | `nickName` | string | Updated friendly name |
     * | `vin` | string | Updated VIN |
     * | `make` | string | Updated manufacturer |
     * | `model` | string | Updated model name |
     * | `year` | string | Updated model year |
     * | `miles` | number | Updated odometer reading |
     *
     * **Responses:**
     * - `200 OK` — Vehicle updated successfully
     * - `400 Bad Request` — Request body is malformed or contains invalid values
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
     * @return response of {@code VehicleServiceCollectionSdkResponse<Object>}
     */
    public VehicleServiceCollectionSdkResponse<Object> updateACollection(
      @NonNull String id,
      @NonNull CreateAVehicleRequest createAVehicleRequest
    ) throws ApiError {
      return this.updateACollection(id, createAVehicleRequest, null);
    }

    /**
     * Partially updates an existing vehicle record. Only the fields included in the request body will be modified.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to update |
     *
     * **Request body** (`application/json`) — all fields optional:
     *
     * | Field | Type | Description |
     * |-------|------|-------------|
     * | `nickName` | string | Updated friendly name |
     * | `vin` | string | Updated VIN |
     * | `make` | string | Updated manufacturer |
     * | `model` | string | Updated model name |
     * | `year` | string | Updated model year |
     * | `miles` | number | Updated odometer reading |
     *
     * **Responses:**
     * - `200 OK` — Vehicle updated successfully
     * - `400 Bad Request` — Request body is malformed or contains invalid values
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
     * @return response of {@code VehicleServiceCollectionSdkResponse<Object>}
     */
    public VehicleServiceCollectionSdkResponse<Object> updateACollection(
      @NonNull String id,
      @NonNull CreateAVehicleRequest createAVehicleRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(updateACollectionConfig, requestConfig);
      Request request = buildUpdateACollectionRequest(id, createAVehicleRequest, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new VehicleServiceCollectionSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Partially updates an existing vehicle record. Only the fields included in the request body will be modified.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to update |
     *
     * **Request body** (`application/json`) — all fields optional:
     *
     * | Field | Type | Description |
     * |-------|------|-------------|
     * | `nickName` | string | Updated friendly name |
     * | `vin` | string | Updated VIN |
     * | `make` | string | Updated manufacturer |
     * | `model` | string | Updated model name |
     * | `year` | string | Updated model year |
     * | `miles` | number | Updated odometer reading |
     *
     * **Responses:**
     * - `200 OK` — Vehicle updated successfully
     * - `400 Bad Request` — Request body is malformed or contains invalid values
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<Object>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<Object>> updateACollectionAsync(
      @NonNull String id,
      @NonNull CreateAVehicleRequest createAVehicleRequest
    ) throws ApiError {
      return this.updateACollectionAsync(id, createAVehicleRequest, null);
    }

    /**
     * Partially updates an existing vehicle record. Only the fields included in the request body will be modified.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to update |
     *
     * **Request body** (`application/json`) — all fields optional:
     *
     * | Field | Type | Description |
     * |-------|------|-------------|
     * | `nickName` | string | Updated friendly name |
     * | `vin` | string | Updated VIN |
     * | `make` | string | Updated manufacturer |
     * | `model` | string | Updated model name |
     * | `year` | string | Updated model year |
     * | `miles` | number | Updated odometer reading |
     *
     * **Responses:**
     * - `200 OK` — Vehicle updated successfully
     * - `400 Bad Request` — Request body is malformed or contains invalid values
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @param createAVehicleRequest {@link CreateAVehicleRequest} Request Body
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<Object>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<Object>> updateACollectionAsync(
      @NonNull String id,
      @NonNull CreateAVehicleRequest createAVehicleRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(updateACollectionConfig, requestConfig);
      Request request = buildUpdateACollectionRequest(id, createAVehicleRequest, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new VehicleServiceCollectionSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Permanently removes a vehicle record from the registry.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to delete |
     *
     * **Responses:**
     * - `204 No Content` — Vehicle deleted successfully (no response body)
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @return response of {@code VehicleServiceCollectionSdkResponse<String>}
     */
    public VehicleServiceCollectionSdkResponse<String> deleteAVehicle(@NonNull String id)
      throws ApiError {
      return this.deleteAVehicle(id, null);
    }

    /**
     * Permanently removes a vehicle record from the registry.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to delete |
     *
     * **Responses:**
     * - `204 No Content` — Vehicle deleted successfully (no response body)
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @return response of {@code VehicleServiceCollectionSdkResponse<String>}
     */
    public VehicleServiceCollectionSdkResponse<String> deleteAVehicle(
      @NonNull String id,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(deleteAVehicleConfig, requestConfig);
      Request request = buildDeleteAVehicleRequest(id, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new VehicleServiceCollectionSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.toBodyString(bodyBytes)
      );
    }

    /**
     * Permanently removes a vehicle record from the registry.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to delete |
     *
     * **Responses:**
     * - `204 No Content` — Vehicle deleted successfully (no response body)
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<String>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<String>> deleteAVehicleAsync(
      @NonNull String id
    ) throws ApiError {
      return this.deleteAVehicleAsync(id, null);
    }

    /**
     * Permanently removes a vehicle record from the registry.
     *
     * **Path parameter:**
     *
     * | Parameter | Description |
     * |-----------|-------------|
     * | `:id` | The unique identifier of the vehicle to delete |
     *
     * **Responses:**
     * - `204 No Content` — Vehicle deleted successfully (no response body)
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param id String
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<String>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<String>> deleteAVehicleAsync(
      @NonNull String id,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(deleteAVehicleConfig, requestConfig);
      Request request = buildDeleteAVehicleRequest(id, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new VehicleServiceCollectionSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.toBodyString(bodyBytes)
        );
      });
    }

    /**
     * Returns a list of all vehicle records. Supports optional filtering by brand.
     *
     * **Query parameters:**
     *
     * | Parameter | Type | Required | Description |
     * |-----------|------|----------|-------------|
     * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
     *
     * **Responses:**
     * - `200 OK` — List of vehicles returned successfully
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @return response of {@code VehicleServiceCollectionSdkResponse<Object>}
     */
    public VehicleServiceCollectionSdkResponse<Object> getAllVehicles() throws ApiError {
      return this.getAllVehicles(GetAllVehiclesParameters.builder().build());
    }

    /**
     * Returns a list of all vehicle records. Supports optional filtering by brand.
     *
     * **Query parameters:**
     *
     * | Parameter | Type | Required | Description |
     * |-----------|------|----------|-------------|
     * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
     *
     * **Responses:**
     * - `200 OK` — List of vehicles returned successfully
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param requestParameters {@link GetAllVehiclesParameters} Request Parameters Object
     * @return response of {@code VehicleServiceCollectionSdkResponse<Object>}
     */
    public VehicleServiceCollectionSdkResponse<Object> getAllVehicles(
      @NonNull GetAllVehiclesParameters requestParameters
    ) throws ApiError {
      return this.getAllVehicles(requestParameters, null);
    }

    /**
     * Returns a list of all vehicle records. Supports optional filtering by brand.
     *
     * **Query parameters:**
     *
     * | Parameter | Type | Required | Description |
     * |-----------|------|----------|-------------|
     * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
     *
     * **Responses:**
     * - `200 OK` — List of vehicles returned successfully
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param requestParameters {@link GetAllVehiclesParameters} Request Parameters Object
     * @return response of {@code VehicleServiceCollectionSdkResponse<Object>}
     */
    public VehicleServiceCollectionSdkResponse<Object> getAllVehicles(
      @NonNull GetAllVehiclesParameters requestParameters,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(getAllVehiclesConfig, requestConfig);
      Request request = buildGetAllVehiclesRequest(requestParameters, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new VehicleServiceCollectionSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Returns a list of all vehicle records. Supports optional filtering by brand.
     *
     * **Query parameters:**
     *
     * | Parameter | Type | Required | Description |
     * |-----------|------|----------|-------------|
     * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
     *
     * **Responses:**
     * - `200 OK` — List of vehicles returned successfully
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<Object>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<Object>> getAllVehiclesAsync()
      throws ApiError {
      return this.getAllVehiclesAsync(GetAllVehiclesParameters.builder().build());
    }

    /**
     * Returns a list of all vehicle records. Supports optional filtering by brand.
     *
     * **Query parameters:**
     *
     * | Parameter | Type | Required | Description |
     * |-----------|------|----------|-------------|
     * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
     *
     * **Responses:**
     * - `200 OK` — List of vehicles returned successfully
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param requestParameters {@link GetAllVehiclesParameters} Request Parameters Object
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<Object>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<Object>> getAllVehiclesAsync(
      @NonNull GetAllVehiclesParameters requestParameters
    ) throws ApiError {
      return this.getAllVehiclesAsync(requestParameters, null);
    }

    /**
     * Returns a list of all vehicle records. Supports optional filtering by brand.
     *
     * **Query parameters:**
     *
     * | Parameter | Type | Required | Description |
     * |-----------|------|----------|-------------|
     * | `brand` | string | No | Filter results to vehicles matching this brand/make (e.g. `Honda`) |
     *
     * **Responses:**
     * - `200 OK` — List of vehicles returned successfully
     * - `500 Internal Server Error` — Unexpected server error
     *
     * @param requestParameters {@link GetAllVehiclesParameters} Request Parameters Object
     * @return response of {@code CompletableFuture<VehicleServiceCollectionSdkResponse<Object>>}
     */
    public CompletableFuture<VehicleServiceCollectionSdkResponse<Object>> getAllVehiclesAsync(
      @NonNull GetAllVehiclesParameters requestParameters,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(getAllVehiclesConfig, requestConfig);
      Request request = buildGetAllVehiclesRequest(requestParameters, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new VehicleServiceCollectionSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }
  }
}
