// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.versioning.typechangedfrom.generated;

// The Java test files under 'generated' package are generated for your reference.
// If you wish to modify these files, please copy them out of the 'generated' package, and modify there.
// See https://aka.ms/azsdk/dpg/java/tests for guide on adding a test.

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.test.TestMode;
import com.azure.core.test.TestProxyTestBase;
import com.azure.core.util.Configuration;
import com.versioning.typechangedfrom.TypeChangedFromClient;
import com.versioning.typechangedfrom.TypeChangedFromClientBuilder;

class TypeChangedFromClientTestBase extends TestProxyTestBase {
    protected TypeChangedFromClient typeChangedFromClient;

    @Override
    protected void beforeTest() {
        TypeChangedFromClientBuilder typeChangedFromClientbuilder = new TypeChangedFromClientBuilder()
            .endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT", "endpoint"))
            .version(Configuration.getGlobalConfiguration().get("VERSION", "version"))
            .httpClient(HttpClient.createDefault())
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BASIC));
        if (getTestMode() == TestMode.PLAYBACK) {
            typeChangedFromClientbuilder.httpClient(interceptorManager.getPlaybackClient());
        } else if (getTestMode() == TestMode.RECORD) {
            typeChangedFromClientbuilder.addPolicy(interceptorManager.getRecordPolicy());
        }
        typeChangedFromClient = typeChangedFromClientbuilder.buildClient();

    }
}
