package com.scorpion.service;

import com.scorpion.manager.DocConversionManager;
import com.scorpion.request.ConvertRequestBody;
import com.scorpion.request.ConvertRequestProperty;
import com.scorpion.response.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.scorpion.request.BaseProperties.CONVERT;
import static com.scorpion.request.BaseProperties.STATUS;

/**
 * Created by KAI on 8/30/18.
 * Copyright 2018 by doc-conversion-api
 * All rights reserved.
 */
public class ConvertServiceImpl implements ConvertService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ConvertServiceImpl.class);

    private String applicationId;
    private String secretKey;

    private DocConversionManager docConversionManager;
    private RestTemplate restTemplate = new RestTemplate();

    public ConvertServiceImpl() throws IllegalAccessException {
        throw new IllegalAccessException("Action is forbidden.");
    }

    public ConvertServiceImpl(String applicationId, String secretKey) {
        this.applicationId = applicationId;
        this.secretKey = secretKey;
        this.docConversionManager = new DocConversionManager(applicationId, secretKey);
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public ConvertServiceImpl applicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public ConvertServiceImpl secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    @Override
    public byte[] convert(ConvertRequestProperty convertRequestProperty) throws IOException {
        ConvertRequestBody convertRequestBody = new ConvertRequestBody(convertRequestProperty);
        HttpEntity httpEntity = this.docConversionManager.convertRequestBody(convertRequestBody)
                .httpEntity();
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(CONVERT, HttpMethod.POST, httpEntity, byte[].class);
        return responseEntity.getStatusCode().is2xxSuccessful() ? responseEntity.getBody() : null;
    }

    @Override
    public StatusResponse getStatus() {
        HttpHeaders httpHeaders = this.docConversionManager.httpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<StatusResponse> response =
                this.restTemplate.exchange(STATUS, HttpMethod.GET, httpEntity, StatusResponse.class);
        return response.getStatusCode().is2xxSuccessful() ? response.getBody() : new StatusResponse();
    }
}
