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
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link AntiferenceMatrixHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Neil Renaud - Initial contribution
 */
public class AntiferenceMatrixHandler extends BaseThingHandler {

    private Logger logger = LoggerFactory.getLogger(AntiferenceMatrixHandler.class);

    public AntiferenceMatrixHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (channelUID.getId().equals(POWER_CHANNEL) && command instanceof OnOffType) {
            AntiferenceMatrixBridgeHandler bridge = (AntiferenceMatrixBridgeHandler) getBridge().getHandler();
            int outputId = 0;
            bridge.changePower(outputId, (OnOffType) command);
        }
        if (channelUID.getId().equals(SOURCE_CHANNEL) && command instanceof DecimalType) {
            AntiferenceMatrixBridgeHandler bridge = (AntiferenceMatrixBridgeHandler) getBridge().getHandler();
            int outputId = 0;
            bridge.changeSource(outputId, (DecimalType) command);

        }
        Thing thing = getThing();
        Channel channel = getThing().getChannel(POWER_CHANNEL);

        // TODO: handle command

        // Note: if communication with thing fails for some reason,
        // indicate that by setting the status with detail information
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
        // "Could not control device at IP address x.x.x.x");
    }

    @Override
    public void initialize() {
        // TODO: Initialize the thing. If done set status to ONLINE to indicate proper working.
        // Long running initialization should be done asynchronously in background.
        updateStatus(ThingStatus.ONLINE);

        // Note: When initialization can NOT be done set the status with more details for further
        // analysis. See also class ThingStatusDetail for all available status details.
        // Add a description to give user information to understand why thing does not work
        // as expected. E.g.
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
        // "Can not access device as username and/or password are invalid");
    }
}
