/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2017 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Oct 4, 2019
 *******************************************************************************/

package com.primeton.eos.dap.demo.order.common.config;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class PageJacksonModule extends Module {

    public String getModuleName() {
        return getClass().getName();
    }

    public Version version() {
        return new Version(0, 1, 0, "", null, null);
    }

    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(Page.class, PageMixIn.class);
    }

    @JsonDeserialize(as = PageJacksonImpl.class)
    private interface PageMixIn {
    }

    // https://github.com/spring-cloud/spring-cloud-netflix/issues/556
    // https://github.com/spring-cloud/spring-cloud-netflix/pull/1604
    // https://stackoverflow.com/questions/50826061/jsonmappingexception-when-testing-endpoints-with-pageable-field
    public static class PageJacksonImpl<T> extends PageImpl<T> {
        private static final long serialVersionUID = -903734572632121766L;

        public PageJacksonImpl(@JsonProperty("content") List<T> content, @JsonProperty("number") int number,
                @JsonProperty("size") int size, @JsonProperty("totalElements") Long totalElements,
                @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
                @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort,
                @JsonProperty("first") boolean first, @JsonProperty("numberOfElements") int numberOfElements) {
            super(content, PageRequest.of(number, size), totalElements);
        }
    }

}

/*
 * 修改历史
 * $Log$ 
 */