package org.openhab.binding.antiferencematrix.internal.discovery;

import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.openhab.binding.antiferencematrix.AntiferenceMatrixBindingConstants;
import org.openhab.binding.antiferencematrix.handler.AntiferenceMatrixBridgeHandler;

public class AntiferenceMatrixDiscoveryService extends AbstractDiscoveryService
        implements AntiferenceMatrixDiscoveryListener {

    AntiferenceMatrixBridgeHandler bridgeHandler;

    public AntiferenceMatrixDiscoveryService(AntiferenceMatrixBridgeHandler bridgeHandler) {
        super(AntiferenceMatrixBindingConstants.SUPPORTED_THING_TYPES_UIDS, 10);
    }

    @Override
    protected void startScan() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void startBackgroundDiscovery() {
        super.startBackgroundDiscovery();
        bridgeHandler.registerDiscoveryListener(this);
    }

    @Override
    protected void stopBackgroundDiscovery() {
        super.stopBackgroundDiscovery();
        bridgeHandler.unregisterDiscoveryListener();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
