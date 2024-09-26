import { MockRequest, passOnSuccess, ScenarioMockApi, xml } from "@typespec/spec-api";

export const Scenarios: Record<string, ScenarioMockApi> = {};

export const simpleModel = `
<SimpleModel>
  <name>foo</name>
  <age>123</age>
</SimpleModel>
`;

export const modelWithSimpleArrays = `
<ModelWithSimpleArrays>
  <colors>
    <string>red</string>
    <string>green</string>
    <string>blue</string>
  </colors>
  <counts>
    <int32>1</int32>
    <int32>2</int32>
  </counts>
</ModelWithSimpleArrays>
`;

export const modelWithArrayOfModel = `
<ModelWithArrayOfModel>
  <items>
    <SimpleModel>
      <name>foo</name>
      <age>123</age>
    </SimpleModel>
    <SimpleModel>
      <name>bar</name>
      <age>456</age>
    </SimpleModel>
  </items>
</ModelWithArrayOfModel>
`;

export const modelWithOptionalField = `
<ModelWithOptionalField>
  <item>widget</item>
</ModelWithOptionalField>
`;

export const modelWithAttributes = `
<ModelWithAttributes id1="123" id2="foo">
  <enabled>true</enabled>
</ModelWithAttributes>
`;

export const modelWithUnwrappedArray = `
<ModelWithUnwrappedArray>
  <colors>red</colors>
  <colors>green</colors>
  <colors>blue</colors>
  <counts>
    <int32>1</int32>
    <int32>2</int32>
  </counts>
</ModelWithUnwrappedArray>
`;

export const modelWithRenamedArrays = `
<ModelWithRenamedArrays>
  <Colors>red</Colors>
  <Colors>green</Colors>
  <Colors>blue</Colors>
  <Counts>
    <int32>1</int32>
    <int32>2</int32>
  </Counts>
</ModelWithRenamedArrays>
`;

export const modelWithRenamedFields = `
<ModelWithRenamedFieldsSrc>
  <InputData>
    <name>foo</name>
    <age>123</age>
  </InputData>
  <OutputData>
    <name>bar</name>
    <age>456</age>
  </OutputData>
</ModelWithRenamedFieldsSrc>
`;

export const modelWithEmptyArray = `
<ModelWithEmptyArray>
  <items />
</ModelWithEmptyArray>
`;

export const modelWithText = `
<ModelWithText language="foo">
  This is some text.
</ModelWithText>
`;

export const modelWithDictionary = `
<ModelWithDictionary>
  <metadata>
    <Color>blue</Color>
    <Count>123</Count>
    <Enabled>false</Enabled>
  </metadata>
</ModelWithDictionary>
`;

export const modelWithEncodedNames = `
<ModelWithEncodedNamesSrc>
  <SimpleModelData>
    <name>foo</name>
    <age>123</age>
  </SimpleModelData>
  <PossibleColors>
    <string>red</string>
    <string>green</string>
    <string>blue</string>
  </PossibleColors>
</ModelWithEncodedNamesSrc>
`;

function createServerTests(uri: string, data?: any) {
  return passOnSuccess({
    uri,
    mockMethods: [
      {
        method: "get",
        request: {},
        response: {
          status: 200,
          data: xml(data),
        },
        handler: (req: MockRequest) => {
          return {
            status: 200,
            body: xml(data),
          };
        },
      },
      {
        method: "put",
        request: {
          body: data,
          config: {
            headers: {
              "content-type": "application/xml",
            },
          },
        },
        response: {
          status: 204,
        },
        handler: (req: MockRequest) => {
          req.expect.containsHeader("content-type", "application/xml");
          req.expect.xmlBodyEquals(data);
          return {
            status: 204,
          };
        },
      },
    ],
  });
}

Scenarios.Payload_Xml_SimpleModel = createServerTests("/payload/xml/simpleModel", simpleModel);
Scenarios.Payload_Xml_ModelWithSimpleArrays = createServerTests(
  "/payload/xml/modelWithSimpleArrays",
  modelWithSimpleArrays,
);
Scenarios.Payload_Xml_ModelWithArrayOfModel = createServerTests(
  "/payload/xml/modelWithArrayOfModel",
  modelWithArrayOfModel,
);
Scenarios.Payload_Xml_ModelWithOptionalField = createServerTests(
  "/payload/xml/modelWithOptionalField",
  modelWithOptionalField,
);
Scenarios.Payload_Xml_ModelWithAttributes = createServerTests(
  "/payload/xml/modelWithAttributes",
  modelWithAttributes,
);
Scenarios.Payload_Xml_ModelWithUnwrappedArray = createServerTests(
  "/payload/xml/modelWithUnwrappedArray",
  modelWithUnwrappedArray,
);
Scenarios.Payload_Xml_ModelWithRenamedArrays = createServerTests(
  "/payload/xml/modelWithRenamedArrays",
  modelWithRenamedArrays,
);
Scenarios.Payload_Xml_ModelWithRenamedFields = createServerTests(
  "/payload/xml/modelWithRenamedFields",
  modelWithRenamedFields,
);
Scenarios.Payload_Xml_ModelWithEmptyArray = createServerTests(
  "/payload/xml/modelWithEmptyArray",
  modelWithEmptyArray,
);
Scenarios.Payload_Xml_ModelWithText = createServerTests(
  "/payload/xml/modelWithText",
  modelWithText,
);
Scenarios.Payload_Xml_ModelWithDictionary = createServerTests(
  "/payload/xml/modelWithDictionary",
  modelWithDictionary,
);
Scenarios.Payload_Xml_ModelWithEncodedNames = createServerTests(
  "/payload/xml/modelWithEncodedNames",
  modelWithEncodedNames,
);
