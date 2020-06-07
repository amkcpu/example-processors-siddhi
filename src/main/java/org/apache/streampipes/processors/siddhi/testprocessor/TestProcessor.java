package org.apache.streampipes.processors.siddhi.testprocessor;/*
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

import org.apache.streampipes.processors.siddhi.filter.FilterOperator;
import org.apache.streampipes.wrapper.siddhi.engine.SiddhiEventEngine;

import java.util.List;

public class TestProcessor extends SiddhiEventEngine<TestProcessorParameters> {

    @Override
    protected String fromStatement(List<String> inputStreamNames, TestProcessorParameters params) {
        String[] filterProperties = params.getFilterProperties();
        String[] filterOperators = rewriteOperatorList(params.getFilterOperators());
        Double[] thresholds = params.getThresholds();
        
        return "from " + inputStreamNames.get(0) +"[" + filterProperties[0] + filterOperators[0] + thresholds[0] +"]";
    }

    @Override
    protected String selectStatement(TestProcessorParameters params) {
        return getCustomOutputSelectStatement(params.getGraph());
    }

    protected String[] rewriteOperatorList(FilterOperator[] list) {
        String[] operators = new String[list.length];

        for (int i = 0; i < list.length; i++) {
            if (list[i] == FilterOperator.EQ) {
                operators[i] = "==";
            } else if (list[i] == FilterOperator.GE) {
                operators[i] = ">=";
            } else if (list[i] == FilterOperator.GT) {
                operators[i] = ">";
            } else if (list[i] == FilterOperator.LE) {
                operators[i] = "<=";
            } else if (list[i] == FilterOperator.LT) {
                operators[i] = "<";
            } else if (list[i] == FilterOperator.IE) {
                operators[i] = "!=";
            }
        }

        return operators;
    }

    protected String getFilterStatementsAsString(String[] filterProperties, String[] filterOperators, Double[] thresholds) {
        return "";
    }

}
