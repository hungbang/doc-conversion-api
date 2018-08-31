package com.scorpion.service;

import com.scorpion.request.ConvertRequestProperty;
import com.scorpion.response.StatusResponse;

import java.io.IOException;

/**
 * Created by KAI on 8/30/18.
 * Copyright 2018 by doc-conversion-api
 * All rights reserved.
 */

public interface ConvertService {
    byte[] convert(ConvertRequestProperty convertRequestProperty) throws IOException;
    StatusResponse getStatus();
}
