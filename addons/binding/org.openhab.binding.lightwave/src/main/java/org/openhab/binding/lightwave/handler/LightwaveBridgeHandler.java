/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.lightwave.handler;

import static org.openhab.binding.lightwave.LightwaveBindingConstants.CHANNEL_1;

import java.math.BigDecimal;

import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.openhab.binding.lightwave.internal.api.LightwaveApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link LightwaveBridgeHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Neil Renaud - Initial contribution
 */
public class LightwaveBridgeHandler extends BaseThingHandler {

    LightwaveApi api = null;

    private Logger logger = LoggerFactory.getLogger(LightwaveBridgeHandler.class);

    public LightwaveBridgeHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (command instanceof RefreshType) {
            doRefresh();
        }
        if (channelUID.getId().equals(CHANNEL_1)) {
            // TODO: handle command

            // Note: if communication with thing fails for some reason,
            // indicate that by setting the status with detail information
            // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
            // "Could not control device at IP address x.x.x.x");
        }
    }

    private void doRefresh() {

    }

    @Override
    public void initialize() {
        Configuration configuration = getThing().getConfiguration();
        String hostname = (String) configuration.get("hostname");
        BigDecimal listenPort = (BigDecimal) configuration.get("listenPort");
        BigDecimal sendPort = (BigDecimal) configuration.get("sendPort");
        api = new LightwaveApi(hostname, listenPort.intValue(), sendPort.intValue());

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
