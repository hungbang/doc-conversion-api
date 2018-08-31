package com.scorpion.manager;

import com.scorpion.request.ConvertHeaderProperties;
import com.scorpion.request.ConvertRequestBody;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by KAI on 8/29/18.
 * Copyright 2018 by doc-conversion-api-integration
 * All rights reserved.
 */
public class DocConversionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocConversionManager.class);

    private static final RestTemplate restTemplate = new RestTemplate();
    private static DocConversionManager docConversionManager = null;
    static final String USER_AGENT = "Mozilla/5.0";
    private ConvertHeaderProperties convertHeaderProperties;
    private ConvertRequestBody convertRequestBody;

    public DocConversionManager(String applicationId, String secretKey) {
        convertHeaderProperties = new ConvertHeaderProperties(applicationId, secretKey);
        this.enableRedirect();
    }

    private void enableRedirect() {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);
    }


    public void setConvertRequestBody(ConvertRequestBody convertRequestBody) {
        this.convertRequestBody = convertRequestBody;
    }

    public DocConversionManager convertRequestBody(ConvertRequestBody convertRequestBody) {
        this.convertRequestBody = convertRequestBody;
        return this;
    }

    public HttpEntity httpEntity() throws IOException {
        HttpEntity httpEntity = new HttpEntity(convertRequestBody.body(), convertHeaderProperties.httpHeaders(false));
        return httpEntity;
    }

    public HttpHeaders httpHeaders() {
        return this.convertHeaderProperties.httpHeaders(true);
    }

}
