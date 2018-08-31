package com.scorpion.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

/**
 * Created by KAI on 8/31/18.
 * Copyright 2018 by doc-conversion-api
 * All rights reserved.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Service {
    private String name;
    private String statusCode;
    private String errorMessage;

}
