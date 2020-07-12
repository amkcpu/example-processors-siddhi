/*
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

package org.apache.streampipes.processors.siddhi.eventcounter;

import org.apache.streampipes.model.DataProcessorType;
import org.apache.streampipes.model.graph.DataProcessorDescription;
import org.apache.streampipes.model.graph.DataProcessorInvocation;
import org.apache.streampipes.model.schema.PropertyScope;
import org.apache.streampipes.sdk.builder.ProcessingElementBuilder;
import org.apache.streampipes.sdk.builder.StreamRequirementsBuilder;
import org.apache.streampipes.sdk.extractor.ProcessingElementParameterExtractor;
import org.apache.streampipes.sdk.helpers.*;
import org.apache.streampipes.sdk.utils.Assets;
import org.apache.streampipes.wrapper.standalone.ConfiguredEventProcessor;
import org.apache.streampipes.wrapper.standalone.declarer.StandaloneEventProcessingDeclarer;

public class EventCounterController extends StandaloneEventProcessingDeclarer<EventCounterParameters> {

	private static final String MIN_NO_EVENTS = "min-no-events";
	private static final String TIME_WINDOW_LENGTH = "time-window-length";

	@Override
	public DataProcessorDescription declareModel() {
		return ProcessingElementBuilder.create("org.apache.streampipes.processors.siddhi.eventcounter")
				.category(DataProcessorType.PATTERN_DETECT)
				.withLocales(Locales.EN)
				.withAssets(Assets.DOCUMENTATION)
				.requiredStream(StreamRequirementsBuilder
						.create()
						.build())
				.requiredIntegerParameter(Labels.withId(MIN_NO_EVENTS))
				.requiredIntegerParameter(Labels.withId(TIME_WINDOW_LENGTH))
				.outputStrategy(OutputStrategies.custom())
				.build();
	}

	@Override
	public ConfiguredEventProcessor<EventCounterParameters> onInvocation(DataProcessorInvocation graph, ProcessingElementParameterExtractor extractor) {

		int minNoEvents = extractor.singleValueParameter(MIN_NO_EVENTS, Integer.class);
		int timeWindowLength = extractor.singleValueParameter(TIME_WINDOW_LENGTH, Integer.class);

		EventCounterParameters staticParam = new EventCounterParameters(graph, minNoEvents, timeWindowLength);

		return new ConfiguredEventProcessor<>(staticParam, EventCounter::new);
	}

}
