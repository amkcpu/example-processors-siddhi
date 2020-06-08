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

import org.apache.commons.lang.StringUtils;
import org.apache.streampipes.processors.siddhi.filter.FilterOperator;
import org.apache.streampipes.wrapper.siddhi.engine.SiddhiEventEngine;

import java.util.List;

public class MultiFilter extends SiddhiEventEngine<MultiFilterParameters> {

    @Override
    protected String fromStatement(List<String> inputStreamNames, MultiFilterParameters params) {
        String[] statements = params.getFilterStatements();
        int timeWindow = params.getTimeWindow();

        // concatenate filter statements to a single statement string
        String statementsAsString = StringUtils.join(statements, " and ");

        String fromStatement = "from every " + inputStreamNames.get(0) +"[" + statementsAsString +"]";

        if (timeWindow > 0)
            fromStatement = fromStatement + "\nwithin " + timeWindow + " milliseconds";

        return fromStatement;
    }

    @Override
    protected String selectStatement(MultiFilterParameters params) {
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
