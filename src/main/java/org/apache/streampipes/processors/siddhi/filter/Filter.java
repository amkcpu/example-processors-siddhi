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


package org.apache.streampipes.processors.siddhi.filter;

import org.apache.streampipes.wrapper.siddhi.engine.SiddhiDebugCallback;
import org.apache.streampipes.wrapper.siddhi.engine.SiddhiEventEngine;

import java.util.List;

public class Filter extends SiddhiEventEngine<FilterParameters> {

    public Filter() {
        super();
    }

    public Filter(SiddhiDebugCallback callback) {
        super(callback);
    }

    @Override
    protected String fromStatement(List<String> inputStreamNames, FilterParameters params) {
        String filterProperty = prepareName(params.getFilterProperty());
        String filterOperator = "";

        if (params.getFilterOperator() == FilterOperator.EQ) {
            filterOperator = "==";
        } else if (params.getFilterOperator() == FilterOperator.GE) {
            filterOperator = ">=";
        } else if (params.getFilterOperator() == FilterOperator.GT) {
            filterOperator = ">";
        } else if (params.getFilterOperator() == FilterOperator.LE) {
            filterOperator = "<=";
        } else if (params.getFilterOperator() == FilterOperator.LT) {
            filterOperator = "<";
        } else if (params.getFilterOperator() == FilterOperator.IE) {
            filterOperator = "!=";
        }

        // e.g. Filter for numberField value less than 10 and output all fields
        //
        // Siddhi query: from inputstreamname[numberField<10]
        return "from " + inputStreamNames.get(0) +"[" + filterProperty + filterOperator + params.getThreshold() +"]";
    }

    @Override
    protected String selectStatement(FilterParameters params) {
        return getCustomOutputSelectStatement(params.getGraph());
    }
}
