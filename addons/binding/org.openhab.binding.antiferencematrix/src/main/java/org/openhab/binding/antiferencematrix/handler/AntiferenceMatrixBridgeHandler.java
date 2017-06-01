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
import org.eclipse.smarthome.core.types.RefreshType;
import org.openhab.binding.antiferencematrix.AntiferenceMatrixBindingConstants;
import org.openhab.binding.antiferencematrix.internal.discovery.AntiferenceMatrixDiscoveryListener;
import org.openhab.binding.antiferencematrix.internal.response.MatrixResponse;
import org.openhab.binding.antiferencematrix.internal.response.PortDetailsResponse;

import com.google.gson.Gson;

public class AntiferenceMatrixBridgeHandler extends BaseBridgeHandler {

    private AntiferenceMatrixDiscoveryListener listener = null;

    public AntiferenceMatrixBridgeHandler(Bridge bridge) {
        super(bridge);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (command instanceof RefreshType) {
            handleRefreshCommand(channelUID, (RefreshCommand) command);
        }
        // TODO Handle other commands
    }

    protected void handleRefreshCommand(ChannelUID channelUID, RefreshType command) {
        MatrixData data = updateMatrixData();
        updateState(new ChannelUID(getThing().getUID(), AntiferenceMatrixBindingConstants.POWER_CHANNEL),
                data.getPower());

    }

    private void notifyDiscoveryListener() {
        listener.update();
    }

    public void registerDiscoveryListener(AntiferenceMatrixDiscoveryListener listener) {
        this.listener = listener;
    }

    public void unregisterDiscoveryListener() {
        this.listener = null;
    }

}
