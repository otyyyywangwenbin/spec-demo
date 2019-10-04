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

package com.primeton.eos.dap.demo.order.common.config.feign;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Configuration
public class FeignJacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer addMixin() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.mixIn(Page.class, PageMixIn.class);
            }
        };
    }

    @JsonDeserialize(as = SimplePageImpl.class)
    private interface PageMixIn {
    }

    public static class SimplePageImpl<T> implements Page<T> {

        private final Page<T> delegate;

        public SimplePageImpl(@JsonProperty("content") List<T> content, @JsonProperty("number") int page, @JsonProperty("size") int size, @JsonProperty("totalElements") long totalElements) {
            delegate = new PageImpl<>(content, PageRequest.of(page, size), totalElements);
        }

        @JsonProperty
        @Override
        public int getTotalPages() {
            return delegate.getTotalPages();
        }

        @JsonProperty
        @Override
        public long getTotalElements() {
            return delegate.getTotalElements();
        }

        @JsonProperty("page")
        @Override
        public int getNumber() {
            return delegate.getNumber();
        }

        @JsonProperty
        @Override
        public int getSize() {
            return delegate.getSize();
        }

        @JsonProperty
        @Override
        public int getNumberOfElements() {
            return delegate.getNumberOfElements();
        }

        @JsonProperty
        @Override
        public List<T> getContent() {
            return delegate.getContent();
        }

        @JsonProperty
        @Override
        public boolean hasContent() {
            return delegate.hasContent();
        }

        @JsonIgnore
        @Override
        public Sort getSort() {
            return delegate.getSort();
        }

        @JsonProperty
        @Override
        public boolean isFirst() {
            return delegate.isFirst();
        }

        @JsonProperty
        @Override
        public boolean isLast() {
            return delegate.isLast();
        }

        @JsonIgnore
        @Override
        public boolean hasNext() {
            return delegate.hasNext();
        }

        @JsonIgnore
        @Override
        public boolean hasPrevious() {
            return delegate.hasPrevious();
        }

        @JsonIgnore
        @Override
        public Pageable nextPageable() {
            return delegate.nextPageable();
        }

        @JsonIgnore
        @Override
        public Pageable previousPageable() {
            return delegate.previousPageable();
        }

        @JsonIgnore
        @Override
        public Iterator<T> iterator() {
            return delegate.iterator();
        }

        @JsonIgnore
        @Override
        public <U> Page<U> map(Function<? super T, ? extends U> converter) {
            return delegate.map(converter);
        }
    }
}

/*
 * 修改历史
 * $Log$ 
 */