package org.apache.streampipes.processors.siddhi.testProcessor;/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

// import com.ibm.jvm.dtfjview.Output;

import org.apache.streampipes.model.DataProcessorType;
import org.apache.streampipes.model.graph.DataProcessorDescription;
import org.apache.streampipes.model.graph.DataProcessorInvocation;
import org.apache.streampipes.sdk.builder.ProcessingElementBuilder;
import org.apache.streampipes.sdk.builder.StreamRequirementsBuilder;
import org.apache.streampipes.sdk.extractor.ProcessingElementParameterExtractor;
import org.apache.streampipes.sdk.helpers.Labels;
import org.apache.streampipes.sdk.helpers.Locales;
import org.apache.streampipes.sdk.helpers.OutputStrategies;
import org.apache.streampipes.sdk.utils.Assets;
import org.apache.streampipes.wrapper.standalone.ConfiguredEventProcessor;
import org.apache.streampipes.wrapper.standalone.declarer.StandaloneEventProcessingDeclarer;

public class TestProcessorController extends StandaloneEventProcessingDeclarer<TestProcessorParameters>  {

    private final String FROM_STATEMENT = "from";

    @Override
    public DataProcessorDescription declareModel() {
        return ProcessingElementBuilder.create("org.apache.streampipes.processors.siddhi.selectfrom")
                .category(DataProcessorType.FILTER)
                .withLocales(Locales.EN)
                .withAssets(Assets.DOCUMENTATION)
                .requiredStream(StreamRequirementsBuilder
                        .create()
                        //.requiredProperty(EpRequirements.anyProperty())
                        .build())
                .requiredTextParameter(Labels.withId(FROM_STATEMENT))
                .outputStrategy(OutputStrategies.custom())
                .build();
    }

    @Override
    public ConfiguredEventProcessor<TestProcessorParameters> onInvocation(DataProcessorInvocation graph, ProcessingElementParameterExtractor extractor) {

        String from = extractor.singleValueParameter(FROM_STATEMENT, String.class);

        TestProcessorParameters params = new TestProcessorParameters(graph, from);

        return new ConfiguredEventProcessor<>(params, TestProcessor::new);

    }
}
