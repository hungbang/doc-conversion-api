package com.scorpion.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.scorpion.request.BaseProperties.USER_AGENT;

/**
 * Created by KAI on 8/31/18.
 * Copyright 2018 by doc-conversion-api
 * All rights reserved.
 */
public class ConvertHeaderProperties {
    final HttpHeaders headers = new HttpHeaders();

    private static ConvertHeaderProperties convertHeaderProperties = null;

    private String applicationId;
    private String secretKey;


    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public ConvertHeaderProperties applicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }


    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public ConvertHeaderProperties secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public ConvertHeaderProperties() throws IllegalAccessException {
        throw new IllegalAccessException("Action is forbidden.");
    }

    public ConvertHeaderProperties(String applicationId, String secretKey) {
        this.applicationId = applicationId;
        this.secretKey = secretKey;
    }

    public HttpHeaders httpHeaders(boolean withoutContentType) {
        headers.add("x-applicationid", applicationId);
        headers.add("x-secretkey", secretKey);
        headers.add("User-Agent", USER_AGENT);
        if (!withoutContentType)
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return headers;
    }


}
