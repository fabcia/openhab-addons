/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.antiferencematrix.handler;

import static org.openhab.binding.antiferencematrix.AntiferenceMatrixBindingConstants.*;

import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.library.types.StringType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.openhab.binding.antiferencematrix.AntiferenceMatrixBindingConstants;
import org.openhab.binding.antiferencematrix.internal.model.OutputPortDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link AntiferenceMatrixOutputHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Neil Renaud - Initial contribution
 */
public class AntiferenceMatrixOutputHandler extends BaseThingHandler {

    private Logger logger = LoggerFactory.getLogger(AntiferenceMatrixOutputHandler.class);

    public AntiferenceMatrixOutputHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        AntiferenceMatrixBridgeHandler bridge = getMatrixBridge();
        if (bridge == null) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.BRIDGE_OFFLINE);
            return;
        }

        if (command instanceof RefreshType) {
            doRefresh();
        }
        if (channelUID.getId().equals(POWER_CHANNEL) && command instanceof OnOffType) {
            int outputId = getOutputId();
            bridge.changePower(outputId, (OnOffType) command);
        }
        if (channelUID.getId().equals(SOURCE_CHANNEL) && command instanceof DecimalType) {
            int outputId = 0;
            bridge.changeSource(outputId, (DecimalType) command);
        }
        updateStatus(ThingStatus.ONLINE);
    }

    private void doRefresh() {
        AntiferenceMatrixBridgeHandler bridge = getMatrixBridge();
        if (bridge == null) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.BRIDGE_OFFLINE);
            return;
        }
        OutputPortDetails outputPortDetails = bridge.getOutputPortDetails(getOutputId());
        if (!outputPortDetails.getResult()) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                    outputPortDetails.getErrorMessage());
        }
        updateState(new ChannelUID(getThing().getUID(), AntiferenceMatrixBindingConstants.PORT_STATUS_MESSAGE_CHANNEL),
                new StringType(outputPortDetails.getStatusMessage()));
        updateStatus(ThingStatus.ONLINE);
    }

    @Override
    public void initialize() {
        // Long running initialization should be done asynchronously in background.
        // Note: When initialization can NOT be done set the status with more details for further
        // analysis. See also class ThingStatusDetail for all available status details.
        // Add a description to give user information to understand why thing does not work
        // as expected. E.g.
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
        // "Can not access device as username and/or password are invalid");
        doRefresh();
    }

    private AntiferenceMatrixBridgeHandler getMatrixBridge() {
        AntiferenceMatrixBridgeHandler bridge = (AntiferenceMatrixBridgeHandler) getBridge().getHandler();
        return bridge;
    }

    public int getOutputId() {
        String outputId = getThing().getProperties().get(AntiferenceMatrixBindingConstants.PROPERTY_OUTPUT_ID);
        return Integer.valueOf(outputId);
    }
}
