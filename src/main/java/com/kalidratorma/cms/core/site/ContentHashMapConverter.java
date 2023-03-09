package com.kalidratorma.cms.core.site;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ContentHashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private static final Logger logger = LoggerFactory.getLogger(ContentHashMapConverter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> content) {

        String contentJson = null;
        try {
            contentJson = objectMapper.writeValueAsString(content);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return contentJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String contentJSON) {

        Map<String, Object> content = null;
        try {
            content = objectMapper.readValue(contentJSON, new TypeReference<HashMap<String, Object>>() {});
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return content;
    }

}