// <auto-generated/>

#nullable disable

using System;
using System.ClientModel;
using System.ClientModel.Primitives;
using System.Text.Json;

namespace Payload.Xml.Models
{
    public partial class ModelWithSimpleArrays : IJsonModel<ModelWithSimpleArrays>
    {
        void IJsonModel<ModelWithSimpleArrays>.Write(Utf8JsonWriter writer, ModelReaderWriterOptions options) => throw null;

        protected virtual void JsonModelWriteCore(Utf8JsonWriter writer, ModelReaderWriterOptions options) => throw null;

        ModelWithSimpleArrays IJsonModel<ModelWithSimpleArrays>.Create(ref Utf8JsonReader reader, ModelReaderWriterOptions options) => throw null;

        protected virtual ModelWithSimpleArrays JsonModelCreateCore(ref Utf8JsonReader reader, ModelReaderWriterOptions options) => throw null;

        BinaryData IPersistableModel<ModelWithSimpleArrays>.Write(ModelReaderWriterOptions options) => throw null;

        protected virtual BinaryData PersistableModelWriteCore(ModelReaderWriterOptions options) => throw null;

        ModelWithSimpleArrays IPersistableModel<ModelWithSimpleArrays>.Create(BinaryData data, ModelReaderWriterOptions options) => throw null;

        protected virtual ModelWithSimpleArrays PersistableModelCreateCore(BinaryData data, ModelReaderWriterOptions options) => throw null;

        string IPersistableModel<ModelWithSimpleArrays>.GetFormatFromOptions(ModelReaderWriterOptions options) => throw null;

        public static implicit operator BinaryContent(ModelWithSimpleArrays modelWithSimpleArrays) => throw null;

        public static explicit operator ModelWithSimpleArrays(ClientResult result) => throw null;
    }
}