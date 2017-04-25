/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.antiferencematrix;

import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link AntiferenceMatrixBinding} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Neil Renaud - Initial contribution
 */
public class AntiferenceMatrixBindingConstants {

    public static final String BINDING_ID = "antiferencematrix";

    // List of all Thing Type UIDs
    public final static ThingTypeUID OUTPUT_TYPE = new ThingTypeUID(BINDING_ID, "output");

    // List of all Channel ids
    public final static String SOURCE_CHANNEL = "source";
    public final static String POWER_CHANNEL = "power";

    public final static Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(OUTPUT_TYPE);
}
