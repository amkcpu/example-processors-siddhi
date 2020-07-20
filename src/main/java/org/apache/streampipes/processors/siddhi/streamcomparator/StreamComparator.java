package org.apache.streampipes.processors.siddhi.streamcomparator;/*
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

import org.apache.streampipes.model.graph.DataProcessorInvocation;
import org.apache.streampipes.wrapper.siddhi.engine.SiddhiEventEngine;

import java.util.List;

public class StreamComparator extends SiddhiEventEngine<StreamComparatorParameters> {

    @Override
    protected String fromStatement(List<String> inputStreamNames, StreamComparatorParameters params) {
        String mainStream = inputStreamNames.get(0);
        String referenceStream = inputStreamNames.get(1);

        return "from every e1 = " + mainStream + " and e2 = " + referenceStream;
    }

    @Override
    protected String selectStatement(StreamComparatorParameters params) {
        float maxDeviation = params.getMaxDeviation();

        String fieldToCompare = "e1." + prepareName(params.getFieldToCompare());
        String referenceFieldToCompare = "e2." + prepareName(params.getReferenceFieldToCompare());

        String compareFunction = String.format("100*math:abs(%1$s - %2$s) / ifThenElse(%1$s==0 and %2$s==0, 1.0, math:min(%1$s, %2$s))",
                fieldToCompare,
                referenceFieldToCompare);

        return getCustomOutputSelectStatement(params.getGraph(), 1) + ", " + compareFunction + " as diff"
        + "\nhaving diff > " + maxDeviation;
    }

}
