package com.scorpion.example;

/**
 * Created by KAI on 8/31/18.
 * Copyright 2018 by doc-conversion-api
 * All rights reserved.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.scorpion.request.BaseProperties.CONVERT;

public class Convert {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://api.docconversionapi.com/status";
    private static final String dataFile = "/Users/KAI/Downloads/temp14_20-Aug-2018_09-49-22-497.docx";

    public static void main(String[] args) throws IOException {

        sendGET();
        System.out.println("POST DONE");
    }

    private static void sendGET() throws IOException {
        URL obj = new URL(CONVERT);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("X-ApplicationID", "4608bdaf-d235-47fd-be88-19e887076faf");
        con.setRequestProperty("X-SecretKey", "6085b971-625b-48cc-acca-ff2e5fe0d9b2");
        con.setRequestProperty("inputFile", dataFile);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }
}
