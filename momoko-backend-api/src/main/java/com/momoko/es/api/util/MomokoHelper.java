package com.momoko.es.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

public class MomokoHelper {

    private static final Log log = LogFactory.getLog(MomokoHelper.class);

    private static ObjectMapper objectMapper = new ObjectMapper();;

    private MomokoHelper() {
    }


    public static String toStringInJson(Object o){

        MomokoHelper.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            return MomokoHelper.objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
