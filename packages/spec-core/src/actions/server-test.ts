import { MockMethod } from "@typespec/spec-api";
import * as fs from "fs";
import * as path from "path";
import { logger } from "../logger.js";
import { loadScenarioMockApis } from "../scenarios-resolver.js";
import { makeServiceCall, uint8ArrayToString } from "./helper.js";

class ServerTestsGenerator {
  private name: string = "";
  private endpoint: string = "";
  private mockMethods: MockMethod[] | undefined;
  private serverBasePath: string = "";
  private scenariosPath: string = "";

  constructor(
    name: string,
    endpoint: string,
    mockMethods: MockMethod[] | undefined,
    serverBasePath: string,
    scenariosPath: string,
  ) {
    this.name = name;
    this.endpoint = endpoint;
    this.mockMethods = mockMethods;
    this.serverBasePath = serverBasePath;
    this.scenariosPath = scenariosPath;
  }

  private getConfigObj(mockMethod: MockMethod) {
    let config = {};
    if (mockMethod.request.validStatus) {
      config = {
        validateStatus: function (status: number) {
          return (status >= 200 && status < 300) || status === mockMethod.request.validStatus;
        },
      };
    }
    if (mockMethod.request.params) {
      config = {
        ...config,
        params: mockMethod.request.params,
      };
    }
    if (mockMethod.request.headers) {
      config = {
        ...config,
        headers: mockMethod.request.headers,
      };
    }
    if (["head", "get", "delete"].includes(mockMethod.method) && mockMethod.request.body) {
      config = {
        ...config,
        data: mockMethod.request.body,
      };
    }
    return config;
  }

  public async executeScenario() {
    if (!this.mockMethods) return;
    for (const mockMethod of this.mockMethods) {
      logger.info(`Executing ${this.name} endpoint - Method: ${mockMethod.method}`);

      if (mockMethod.response.body && mockMethod.response.body["nextLink"]) {
        mockMethod.response.body = {
          ...mockMethod.response.body,
          nextLink: `${this.serverBasePath}${mockMethod.response.body["nextLink"]}`,
        };
      }

      const response = await makeServiceCall(mockMethod.method, {
        endPoint: `${this.serverBasePath}${this.endpoint}`,
        options: {
          requestBody: mockMethod.request.body,
          config: this.getConfigObj(mockMethod),
        },
      });
      if (mockMethod.response.status !== response.status) {
        logger.error(`Status code mismatch for ${this.name} endpoint`);
        logger.error(`Expected: ${mockMethod.response.status} - Actual: ${response.status}`);
        throw new Error(`Status code mismatch for ${this.name} endpoint`);
      }
      if (mockMethod.response.body) {
        if (mockMethod.response.body["contentType"] === "application/xml") {
          if (
            JSON.stringify(mockMethod.response.body["rawContent"]) !== JSON.stringify(response.data)
          ) {
            logger.error(`Response data mismatch for ${this.name} endpoint`);
            logger.error(
              `Expected: ${mockMethod.response.body["rawContent"]} - Actual: ${response.data}`,
            );
            throw new Error(`Response data mismatch for ${this.name} endpoint`);
          }
        } else if (Buffer.isBuffer(mockMethod.response.body)) {
          if (
            mockMethod.request.headers &&
            mockMethod.request.headers["accept"] === "application/json"
          ) {
            if (response.data.content !== mockMethod.response.body.toString("base64")) {
              throw new Error(`Response data mismatch for ${this.name} endpoint`);
            }
          } else {
            if (
              uint8ArrayToString(response.data, "utf-8") !== mockMethod.response.body.toString()
            ) {
              throw new Error(`Response data mismatch for ${this.name} endpoint`);
            }
          }
        } else {
          if (JSON.stringify(mockMethod.response.body) !== JSON.stringify(response.data)) {
            logger.error(`Response data mismatch for ${this.name} endpoint`);
            logger.error(`Expected: ${mockMethod.response.body} - Actual: ${response.data}`);
            throw new Error(`Response data mismatch for ${this.name} endpoint`);
          }
        }
      }
      if (mockMethod.response.headers) {
        for (const key in mockMethod.response.headers) {
          if (mockMethod.response.headers[key] !== response.headers[key]) {
            logger.error(`Response headers mismatch for ${this.name} endpoint`);
            logger.error(
              `Expected: ${mockMethod.response.headers[key]} - Actual: ${response.headers[key]}`,
            );
            throw new Error(`Response headers mismatch for ${this.name} endpoint`);
          }
        }
      }
    }
  }
}

export async function serverTest(
  scenariosPath: string,
  serverBasePath: string,
  scenariosConfig: {
    runSingleScenario: string | undefined;
    runScenariosFromFile: string | undefined;
  },
) {
  // 1. Get Testcases to run
  const testCasesToRun: string[] = [];
  if (scenariosConfig.runSingleScenario) {
    testCasesToRun.push(scenariosConfig.runSingleScenario);
  } else if (scenariosConfig.runScenariosFromFile) {
    const data = fs.readFileSync(path.resolve(scenariosConfig.runScenariosFromFile), "utf8");
    const lines = data.split("\n");
    lines.forEach((line) => {
      testCasesToRun.push(line.trim());
    });
  }
  // 2. Load all the scenarios
  const scenarios = await loadScenarioMockApis(scenariosPath);
  // 3. Execute each scenario
  for (const [name, scenario] of Object.entries(scenarios)) {
    if (!Array.isArray(scenario.apis)) continue;
    for (const api of scenario.apis) {
      if (api.kind === "MockApi") continue;
      if (testCasesToRun.length === 0 || testCasesToRun.includes(name)) {
        const obj: ServerTestsGenerator = new ServerTestsGenerator(
          name,
          api.uri,
          api.mockMethods,
          serverBasePath,
          scenariosPath,
        );
        await obj.executeScenario();
      }
    }
  }
}