/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.markserrano.jsonquery.jpa.mapper;


import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.portal.commons.util.MyObjectMapper;

/**
 * Maps a JSON query {@link String} to a {@link JsonFilter} instance
 *
 * @param jsonString A JSON string
 *
 * @author Mark Anthony L. Serrano
 */
public class JsonObjectMapper {

    public static JsonFilter map(String jsonString) {

        if (jsonString != null) {
            MyObjectMapper mapper = new MyObjectMapper();

            try {
                return mapper.readValue(jsonString, JsonFilter.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
