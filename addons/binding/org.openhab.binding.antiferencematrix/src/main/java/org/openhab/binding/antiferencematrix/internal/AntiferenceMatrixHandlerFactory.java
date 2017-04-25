/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.antiferencematrix.internal;

import static org.openhab.binding.antiferencematrix.AntiferenceMatrixBindingConstants.*;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.openhab.binding.antiferencematrix.handler.AntiferenceMatrixHandler;

/**
 * The {@link AntiferenceMatrixHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Neil Renaud - Initial contribution
 */
public class AntiferenceMatrixHandlerFactory extends BaseThingHandlerFactory {

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {

        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(OUTPUT_TYPE)) {
            return new AntiferenceMatrixHandler(thing);
        }

        return null;
    }
}
