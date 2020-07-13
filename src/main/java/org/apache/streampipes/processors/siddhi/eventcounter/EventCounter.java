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

import org.apache.streampipes.wrapper.siddhi.engine.SiddhiEventEngine;

import java.util.List;

public class EventCounter extends SiddhiEventEngine<EventCounterParameters> {

    @Override
    protected String fromStatement(List<String> inputStreamNames, EventCounterParameters params) {
        int timeWindowLength = params.getTimeWindowLength();
        String timestampField = params.getTimestampField();

        String fromStatement = "from " + inputStreamNames.get(0);

        // using Siddhi time window syntax (https://docs.wso2.com/display/CEP310/Windows#Windows-timeWindow)
        if (timeWindowLength > 0)
            fromStatement += "#window.time(" + timeWindowLength + " sec)";
            //fromStatement += "#window.externalTime(" + "s0timestamp" + ", " + timeWindowLength + " sec)";
            //fromStatement += "#window.externalTime(" + timestampField + ", " + timeWindowLength + " sec)";

        return fromStatement;
    }

    @Override
    protected String selectStatement(EventCounterParameters params) {
        int minNoEvents = params.getMinNoEvents();

        return getCustomOutputSelectStatement(params.getGraph()) + ", count() as count"
                + "\nhaving count >= " + minNoEvents;
    }
}
