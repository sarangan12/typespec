// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package server.path.multiple;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.FluxUtil;
import reactor.core.publisher.Mono;
import server.path.multiple.implementation.MultipleClientImpl;

/**
 * Initializes a new instance of the asynchronous MultipleClient type.
 */
@ServiceClient(builder = MultipleClientBuilder.class, isAsync = true)
public final class MultipleAsyncClient {
    @Generated
    private final MultipleClientImpl serviceClient;

    /**
     * Initializes an instance of MultipleAsyncClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    MultipleAsyncClient(MultipleClientImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The noOperationParams operation.
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> noOperationParamsWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.noOperationParamsWithResponseAsync(requestOptions);
    }

    /**
     * The withOperationPathParam operation.
     * 
     * @param keyword The keyword parameter.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> withOperationPathParamWithResponse(String keyword, RequestOptions requestOptions) {
        return this.serviceClient.withOperationPathParamWithResponseAsync(keyword, requestOptions);
    }

    /**
     * The noOperationParams operation.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> noOperationParams() {
        // Generated convenience method for noOperationParamsWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return noOperationParamsWithResponse(requestOptions).flatMap(FluxUtil::toMono);
    }

    /**
     * The withOperationPathParam operation.
     * 
     * @param keyword The keyword parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> withOperationPathParam(String keyword) {
        // Generated convenience method for withOperationPathParamWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return withOperationPathParamWithResponse(keyword, requestOptions).flatMap(FluxUtil::toMono);
    }
}