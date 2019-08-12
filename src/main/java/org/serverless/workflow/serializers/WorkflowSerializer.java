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

package org.serverless.workflow.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.serverless.workflow.Workflow;
import org.serverless.workflow.events.TriggerEvent;
import org.serverless.workflow.interfaces.State;

public class WorkflowSerializer extends StdSerializer<Workflow> {

    public WorkflowSerializer() {
        this(Workflow.class);
    }

    protected WorkflowSerializer(Class<Workflow> t) {
        super(t);
    }

    @Override
    public void serialize(Workflow workflow,
                          JsonGenerator gen,
                          SerializerProvider provider) throws IOException {

        gen.writeStartObject();

        if (workflow.getId() != null) {
            gen.writeStringField("id",
                                 workflow.getId());
        }

        if (workflow.getName() != null) {
            gen.writeStringField("name",
                                 workflow.getName());
        }

        if (workflow.getVersion() != null) {
            gen.writeStringField("version",
                                 workflow.getVersion());
        }

        if (workflow.getDescription() != null) {
            gen.writeStringField("description",
                                 workflow.getDescription());
        }

        if (workflow.getOwner() != null) {
            gen.writeStringField("owner",
                                 workflow.getOwner());
        }

        if (workflow.getTriggerDefs() != null && workflow.getTriggerDefs().size() > 0) {
            gen.writeArrayFieldStart("trigger-defs");
            for (TriggerEvent triggerEvent : workflow.getTriggerDefs()) {
                gen.writeObject(triggerEvent);
            }
            gen.writeEndArray();
        }

        if (workflow.getStates() != null && workflow.getStates().size() > 0) {
            gen.writeArrayFieldStart("states");
            for (State state : workflow.getStates()) {
                gen.writeObject(state);
            }
            gen.writeEndArray();
        } else {
            gen.writeArrayFieldStart("states");
            gen.writeEndArray();
        }

        gen.writeEndObject();
    }
}
