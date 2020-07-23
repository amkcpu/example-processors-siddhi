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
Compare two streams based on one field, with one acting as input stream and the other as reference.
If they deviate by more than `x` percent on the specified fields, the events from stream 1 will be passed on.

***

## Required input
### Field To Compare
Specifies the field name from stream 1 to use for the comparison (has to be numeric).

### Reference Field To Compare
Specifies the field name from stream 2, the reference stream, to use for the comparison (has to be numeric).

###Maximum Deviation
Deviation in % that is allowed.

***

## Output
The processor outputs the input event from stream 1 if the streams deviate by more than specified
in the `Maximum Deviation` parameter.