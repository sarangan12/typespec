// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.model.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * The AzureEmbeddingModel model.
 */
@Immutable
public final class AzureEmbeddingModel implements JsonSerializable<AzureEmbeddingModel> {
    /*
     * The embedding property.
     */
    @Generated
    private final List<Integer> embedding;

    /**
     * Creates an instance of AzureEmbeddingModel class.
     * 
     * @param embedding the embedding value to set.
     */
    @Generated
    public AzureEmbeddingModel(List<Integer> embedding) {
        this.embedding = embedding;
    }

    /**
     * Get the embedding property: The embedding property.
     * 
     * @return the embedding value.
     */
    @Generated
    public List<Integer> getEmbedding() {
        return this.embedding;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("embedding", this.embedding, (writer, element) -> writer.writeInt(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AzureEmbeddingModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AzureEmbeddingModel if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AzureEmbeddingModel.
     */
    @Generated
    public static AzureEmbeddingModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            List<Integer> embedding = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("embedding".equals(fieldName)) {
                    embedding = reader.readArray(reader1 -> reader1.getInt());
                } else {
                    reader.skipChildren();
                }
            }
            return new AzureEmbeddingModel(embedding);
        });
    }
}