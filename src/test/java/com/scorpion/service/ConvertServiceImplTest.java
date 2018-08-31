package com.scorpion.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scorpion.request.ConvertRequestProperty;
import com.scorpion.response.StatusResponse;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.util.Assert;

import java.io.File;

/**
 * Created by KAI on 8/31/18.
 * Copyright 2018 by doc-conversion-api
 * All rights reserved.
 */
@RunWith(SpringRunner.class)
public class ConvertServiceImplTest {

    private ConvertServiceImpl convertService;
    private String applicationId = "4608bdaf-d235-47fd-be88-19e887076faf";
    private String secretKey = "6085b971-625b-48cc-acca-ff2e5fe0d9b2";
    private String path = "/Users/KAI/Downloads/temp14_20-Aug-2018_09-49-22-497.docx";
    private String resultPath = "/Users/KAI/Downloads/temp14_20-Aug-2018_09-49-22-497.pdf";
    @Before
    public void setup(){
        convertService = new ConvertServiceImpl(applicationId, secretKey);
    }

    @Test
    public void convert() throws Exception {
        File file = new File(path);
        ConvertRequestProperty convertRequestProperty = ConvertRequestProperty.builder()
                .inputFile(file).build();
        byte[] bytes = convertService.convert(convertRequestProperty);
        FileUtils.writeByteArrayToFile(new File(resultPath), bytes);
        Assert.notNull(bytes);
    }

    @Test
    public void getStatus() throws Exception {
        StatusResponse status = convertService.getStatus();
        ObjectMapper objectMapper = new ObjectMapper();
        Assert.notNull(status);
        System.out.println(objectMapper.writeValueAsString(status));
    }

}