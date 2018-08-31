package com.scorpion.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.File;

/**
 * Created by KAI on 8/31/18.
 * Copyright 2018 by doc-conversion-api
 * All rights reserved.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ConvertRequestProperty extends BaseProperties {
    private String optionsJSON;
    private File inputFile;
    private String viewerOptions;

}
