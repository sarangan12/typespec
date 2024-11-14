// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package _specs_.azure.core.page;

import _specs_.azure.core.page.implementation.TwoModelsAsPageItemsImpl;
import _specs_.azure.core.page.models.FirstItem;
import _specs_.azure.core.page.models.SecondItem;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.HttpResponseException;
import com.azure.core.exception.ResourceModifiedException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;

/**
 * Initializes a new instance of the synchronous PageClient type.
 */
@ServiceClient(builder = PageClientBuilder.class)
public final class TwoModelsAsPageItemClient {
    @Generated
    private final TwoModelsAsPageItemsImpl serviceClient;

    /**
     * Initializes an instance of TwoModelsAsPageItemClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Generated
    TwoModelsAsPageItemClient(TwoModelsAsPageItemsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Two operations with two different page item types should be successfully generated. Should generate model for
     * FirstItem.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     id: int (Required)
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of FirstItem items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listFirstItem(RequestOptions requestOptions) {
        return this.serviceClient.listFirstItem(requestOptions);
    }

    /**
     * Two operations with two different page item types should be successfully generated. Should generate model for
     * SecondItem.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     name: String (Required)
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @return paged collection of SecondItem items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<BinaryData> listSecondItem(RequestOptions requestOptions) {
        return this.serviceClient.listSecondItem(requestOptions);
    }

    /**
     * Two operations with two different page item types should be successfully generated. Should generate model for
     * FirstItem.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of FirstItem items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<FirstItem> listFirstItem() {
        // Generated convenience method for listFirstItem
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.listFirstItem(requestOptions)
            .mapPage(bodyItemValue -> bodyItemValue.toObject(FirstItem.class));
    }

    /**
     * Two operations with two different page item types should be successfully generated. Should generate model for
     * SecondItem.
     * 
     * @throws HttpResponseException thrown if the request is rejected by server.
     * @throws ClientAuthenticationException thrown if the request is rejected by server on status code 401.
     * @throws ResourceNotFoundException thrown if the request is rejected by server on status code 404.
     * @throws ResourceModifiedException thrown if the request is rejected by server on status code 409.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return paged collection of SecondItem items as paginated response with {@link PagedIterable}.
     */
    @Generated
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<SecondItem> listSecondItem() {
        // Generated convenience method for listSecondItem
        RequestOptions requestOptions = new RequestOptions();
        return serviceClient.listSecondItem(requestOptions)
            .mapPage(bodyItemValue -> bodyItemValue.toObject(SecondItem.class));
    }
}