package org.apache.streampipes.processors.siddhi.multifilter;/*
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
import org.apache.streampipes.sdk.StaticProperties;
import org.apache.streampipes.sdk.builder.ProcessingElementBuilder;
import org.apache.streampipes.sdk.builder.StreamRequirementsBuilder;
import org.apache.streampipes.sdk.extractor.ProcessingElementParameterExtractor;
import org.apache.streampipes.sdk.helpers.EpRequirements;
import org.apache.streampipes.sdk.helpers.Labels;
import org.apache.streampipes.sdk.helpers.Locales;
import org.apache.streampipes.sdk.helpers.OutputStrategies;
import org.apache.streampipes.sdk.utils.Assets;
import org.apache.streampipes.wrapper.standalone.ConfiguredEventProcessor;
import org.apache.streampipes.wrapper.standalone.declarer.StandaloneEventProcessingDeclarer;

public class MultiFilterController extends StandaloneEventProcessingDeclarer<MultiFilterParameters>  {

    // String[] STATEMENTS = {"s0mass_flow <= 100.0", "s0temperature > 20"};
    // int TIME_WINDOW = 0;
    private static final String STATEMENTS_COLLECTION_ID = "statements-collection";
    private static final String TIME_WINDOW_ID = "time-window";
    private static final String STATEMENT_BOX_ID = "statement-box";

    @Override
    public DataProcessorDescription declareModel() {
        return ProcessingElementBuilder.create("org.apache.streampipes.processors.siddhi.multifilter")
                .category(DataProcessorType.FILTER)
                .withLocales(Locales.EN)
                .withAssets(Assets.DOCUMENTATION)
                .requiredStream(StreamRequirementsBuilder
                        .create()
                        .requiredProperty(EpRequirements.numberReq())
                        .build())
                .requiredParameterAsCollection(Labels.withId(STATEMENTS_COLLECTION_ID),
                        StaticProperties.stringFreeTextProperty(Labels.withId(STATEMENT_BOX_ID)))
                .requiredIntegerParameter(Labels.withId(TIME_WINDOW_ID))
                .outputStrategy(OutputStrategies.custom())
                .build();
    }

    @Override
    public ConfiguredEventProcessor<MultiFilterParameters> onInvocation(DataProcessorInvocation graph, ProcessingElementParameterExtractor extractor) {

        String[] statements = extractor.singleValueParameterFromCollection(STATEMENTS_COLLECTION_ID, String.class).toArray(new String[0]);
        int timeWindow = extractor.singleValueParameter(TIME_WINDOW_ID, Integer.class);

        MultiFilterParameters params = new MultiFilterParameters(graph, statements, timeWindow);

        return new ConfiguredEventProcessor<>(params, MultiFilter::new);

    }
}
