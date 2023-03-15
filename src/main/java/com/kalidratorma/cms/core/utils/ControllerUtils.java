package com.kalidratorma.cms.core.utils;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public class ControllerUtils {
    public static MappingJacksonValue getFilteredMapper(Object object, String filterName, SimpleBeanPropertyFilter filter) {
        MappingJacksonValue mapper = new MappingJacksonValue(object);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName, filter);
        mapper.setFilters(filterProvider);
        return mapper;
    }
}
