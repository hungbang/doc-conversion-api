package com.scorpion.request;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by KAI on 8/31/18.
 * Copyright 2018 by doc-conversion-api
 * All rights reserved.
 */
public class ConvertRequestBody {
    private ConvertRequestProperty convertRequestProperty;

    public ConvertRequestBody(ConvertRequestProperty convertRequestProperty) {
        this.convertRequestProperty = convertRequestProperty;
    }

    public MultiValueMap body() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        FileSystemResource fileSystemResource = new FileSystemResource(convertRequestProperty.getInputFile());
        map.add("inputFile", fileSystemResource);
        if (StringUtils.isNotBlank(convertRequestProperty.getOptionsJSON()))
            map.add("optionsJSON", convertRequestProperty.getOptionsJSON());
        if (StringUtils.isNotBlank(convertRequestProperty.getViewerOptions()))
            map.add("viewerOptions", convertRequestProperty.getViewerOptions());
        return map;
    }
}
