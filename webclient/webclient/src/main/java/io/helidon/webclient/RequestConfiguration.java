/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.helidon.webclient;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import io.helidon.media.common.MessageBodyReader;
import io.helidon.media.common.MessageBodyWriter;
import io.helidon.webclient.spi.WebClientService;

/**
 * Configuration of specific request.
 */
class RequestConfiguration extends WebClientConfiguration {

    private final URI requestURI;
    private final WebClientServiceRequest clientServiceRequest;
    private final List<WebClientService> services;
    private final Set<MessageBodyReader<?>> requestReaders;
    private final Set<MessageBodyWriter<?>> requestWriters;

    private RequestConfiguration(Builder builder) {
        super(builder);
        requestURI = builder.requestURI;
        clientServiceRequest = builder.clientServiceRequest;
        services = builder.services;
        requestReaders = builder.requestReaders;
        requestWriters = builder.messageBodyWriters;
    }

    URI requestURI() {
        return requestURI;
    }

    WebClientServiceRequest clientServiceRequest() {
        return clientServiceRequest;
    }

    List<WebClientService> services() {
        return services;
    }

    Set<MessageBodyReader<?>> requestReaders() {
        return requestReaders;
    }

    Set<MessageBodyWriter<?>> requestWriters() {
        return requestWriters;
    }

    static Builder builder(URI requestURI) {
        return new Builder(requestURI);
    }

    static final class Builder extends WebClientConfiguration.Builder<Builder, RequestConfiguration> {

        private WebClientServiceRequest clientServiceRequest;
        private URI requestURI;
        private List<WebClientService> services = new ArrayList<>();
        private Set<MessageBodyReader<?>> requestReaders;
        private Set<MessageBodyWriter<?>> messageBodyWriters;

        private Builder(URI requestURI) {
            this.requestURI = requestURI;
        }

        Builder clientServiceRequest(WebClientServiceRequest clientServiceRequest) {
            this.clientServiceRequest = clientServiceRequest;
            return this;
        }

        Builder services(List<WebClientService> services) {
            this.services = Collections.unmodifiableList(services);
            return this;
        }

        Builder requestReaders(Set<MessageBodyReader<?>> requestReaders) {
            this.requestReaders = requestReaders;
            return this;
        }

        Builder requestWriters(Set<MessageBodyWriter<?>> messageBodyWriters) {
            this.messageBodyWriters = messageBodyWriters;
            return this;
        }

        @Override
        public RequestConfiguration build() {
            return new RequestConfiguration(this);
        }


    }
}
