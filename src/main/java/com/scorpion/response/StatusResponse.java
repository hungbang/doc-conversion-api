package com.scorpion.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by KAI on 8/31/18.
 * Copyright 2018 by doc-conversion-api
 * All rights reserved.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponse {
    private String statusCode;
    private String authStatus;
    @JsonProperty(value = "services")
    private List<Service> services;
}
