/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.evohome.internal.api.models.v1;

/**
 * Response model for the changeable values
 * @author Neil Renaud
 *
 */
public class ChangeableValues {

    private String mode;
    private HeatSetPoint heatSetpoint;

    @Override
    public String toString() {
        return "mode[" + mode + "] heatSetPoint[" + heatSetpoint + "]";
    }

    public String getMode() {
        return mode;
    }

    public HeatSetPoint getHeatSetpoint() {
        return heatSetpoint;
    }
}
