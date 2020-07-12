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

## Event Counter

<p align="center"> 
    <img src="icon.png" width="150px;" class="pe-image-documentation"/>
</p>

***

## Description
Count events within specified time window and only forward them if threshold is exceeded.

***

## Required input
- Minimum Number Of Events: Specifies minimum number of events that have to occur before passing on any event.
- Time Window: Specifies a time window (in seconds). Set to 0 if not needed.

***

## Configuration


## Output
The processor outputs the input event if the number of events occurring in the time window exceed the threshold value.