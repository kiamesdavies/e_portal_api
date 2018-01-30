/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class MyObjectMapper extends ObjectMapper {

    private static SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ zzz");

    public MyObjectMapper() {
        super();
        this.setDateFormat(DATEFORMAT);

        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //add this
        //this.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    }

    public MyObjectMapper(JsonFactory factory) {
        super(factory);
        this.setDateFormat(DATEFORMAT);

        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public String writeValueAsString(Object value) throws JsonProcessingException {
        DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.setDateFormat(DATEFORMAT);
        return super.writeValueAsString(value);

    }

    public <T extends Object> T readValue(String content, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        try {
            DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
            this.setDateFormat(DATEFORMAT);
            return super.readValue(content, valueType);
        } catch (Exception e) {
            //backward compatibility
            this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            return super.readValue(content, valueType);

        } finally {
            //reset
            this.setDateFormat(DATEFORMAT);
        }
    }

    @Override
    public SimpleDateFormat getDateFormat() {
        return DATEFORMAT;
    }

}
