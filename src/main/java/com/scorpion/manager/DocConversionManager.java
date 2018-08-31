package com.scorpion.manager;

import com.scorpion.request.ConvertHeaderProperties;
import com.scorpion.request.ConvertRequestBody;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

import static com.scorpion.request.BaseProperties.CONVERT;

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

    public HttpHeaders httpHeaders(){
       return this.convertHeaderProperties.httpHeaders(true);
    }

    public static void main(String[] args) throws IOException {
//        String path = "https://api.docconversionapi.com/status";
        final HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        final String dataFile = "/Users/KAI/Downloads/temp14_20-Aug-2018_09-49-22-497.docx";
        File file = new File(dataFile);
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        map.add("inputFile", fileSystemResource);
        headers.add("x-applicationid", "4608bdaf-d235-47fd-be88-19e887076faf");
        headers.add("x-secretkey", "6085b971-625b-48cc-acca-ff2e5fe0d9b2");
        headers.add("User-Agent", USER_AGENT);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity httpEntity = new HttpEntity(map, headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
                CONVERT,
                HttpMethod.POST,
                httpEntity,
                byte[].class);

        File file1 = new File("/Users/KAI/Downloads/temp14_20-Aug-2018_09-49-22-497.pdf");
        FileUtils.writeByteArrayToFile(file1, responseEntity.getBody());

        System.out.println(responseEntity.getBody());
    }

}
