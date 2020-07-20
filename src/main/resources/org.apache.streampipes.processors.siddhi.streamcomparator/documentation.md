<!--
  ~ Licensed to the Apache Software Foundation (ASF) under one or more
  ~ contributor license agreements.  See the NOTICE file distributed with
  ~ this work for additional information regarding copyright ownership.
  ~ The ASF licenses this file to You under the Apache License, Version 2.0
  ~ (the "License"); you may not use this file except in compliance with
  ~ the License.  You may obtain a copy of the License at
  ~
  ~    http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  ~
  -->

## Stream Comparator

<p align="center"> 
    <img src="icon.png" width="150px;" class="pe-image-documentation"/>
</p>

***

## Description
Compare two streams based on one field. If they deviate with more than `x` percent
(where `x` is specified in the `Maximum Deviation` parameter), the events will not be filtered.
Else, the events from stream one will be passed.

***

## Required input
`Maximum Deviation`: Deviation in % that is allowed.

***

## Configuration

### Field
Specifies the field name where the filter operation should be applied on.


### Operation
Specifies the filter operation that should be applied on the field.

### Threshold value
Specifies the threshold value.

## Output
The processor outputs the input event if it satisfies the filter expression.