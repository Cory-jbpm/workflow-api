/*
 *
 *   Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.serverless.workflow.api.mapper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.serverless.workflow.api.InitContext;

public class BaseObjectMapper extends ObjectMapper {

    private WorkflowModule workflowModule;

    public BaseObjectMapper(JsonFactory factory,
                            InitContext initContext) {
        super(factory);

        workflowModule = new WorkflowModule((initContext));

        configure(SerializationFeature.INDENT_OUTPUT,
                  true);
        registerModule(workflowModule);
    }

    public WorkflowModule getWorkflowModule() {
        return workflowModule;
    }
}
