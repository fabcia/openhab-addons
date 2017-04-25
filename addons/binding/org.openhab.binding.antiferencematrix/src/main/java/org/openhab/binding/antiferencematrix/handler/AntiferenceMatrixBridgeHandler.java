/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.antiferencematrix.handler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.openhab.binding.antiferencematrix.internal.response.MatrixResponse;
import org.openhab.binding.antiferencematrix.internal.response.PortDetailsResponse;

import com.google.gson.Gson;

public class AntiferenceMatrixBridgeHandler extends BaseBridgeHandler {

    private static final String URL = "http://matrix.fantasticfox.com";

    private static final String POWER_FUNCTION_TEMPLATE = "%s/CEC/%s/Output/%s";
    private static final String ON_STRING = "On";
    private static final String OFF_STRING = "Off";

    private static final String SOURCE_FUNCTION_TEMPLATE = "%s/Module/Set/%s/%s";

    private static final String SYSTEM_DETAILS_TEMPLATE = "%s/System/Details";

    private static final String PORT_LIST_TEMPLATE = "%s/Port/List";

    private static final String PORT_DETAILS_TEMPLATE = "%s/Port/Details/%s/%s";
    private static final String INPUT_STRING = "Input";
    private static final String OUTPUT_STRING = "Output";

    private static final String SYSTEM_DETAILS_URL = String.format(SYSTEM_DETAILS_TEMPLATE, URL);
    private static final String PORT_LIST_URL = String.format(PORT_LIST_TEMPLATE, URL);

    private final HttpClient httpClient;
    private final Gson gson;

    public AntiferenceMatrixBridgeHandler(Bridge bridge) {
        super(bridge);
        httpClient = new HttpClient();
        gson = new Gson();
    }

    public void connect() {
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
    }

    public boolean changePower(int outputId, OnOffType command) {
        String url = getPowerFunction(command, outputId);
        String response = callUrl(url);
        MatrixResponse matrixResponse = gson.fromJson(response, MatrixResponse.class);
        return matrixResponse.getResult();
    }

    public boolean changeSource(int outputId, DecimalType command) {
        String url = getSourceFunction(command.intValue(), outputId);
        String response = callUrl(url);
        MatrixResponse matrixResponse = gson.fromJson(response, MatrixResponse.class);
        return matrixResponse.getResult();
    }

    public void updatePortStatus() {
        String response = callUrl(PORT_LIST_URL);
        PortDetailsResponse portDetailsResponse = gson.fromJson(response, PortDetailsResponse.class);
        // TODO process port details
    }

    /**
     * Calls the URL and returns the response as a String.
     *
     * @param url String of URL to call
     * @return The string response.
     */
    private String callUrl(String url) {
        ContentResponse response;
        try {
            response = httpClient.GET(url);
            return response.getContentAsString();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        // TODO Auto-generated method stub

    }

    private String getPowerFunction(OnOffType command, int outputId) {
        String onOrOff;
        switch (command) {
            case ON:
                onOrOff = ON_STRING;
                break;
            case OFF:
            default:
                onOrOff = OFF_STRING;
                break;
        }
        return String.format(POWER_FUNCTION_TEMPLATE, URL, onOrOff, outputId);
    }

    private String getSourceFunction(int sourceId, int outputId) {
        return String.format(SOURCE_FUNCTION_TEMPLATE, URL, sourceId, outputId);
    }

    private String getInputPortDetailsFunction(int inputId) {
        return String.format(PORT_DETAILS_TEMPLATE, URL, INPUT_STRING, inputId);
    }

    private String getOutputPortDetailsFunction(int outputId) {
        return String.format(PORT_DETAILS_TEMPLATE, URL, OUTPUT_STRING, outputId);
    }
}
