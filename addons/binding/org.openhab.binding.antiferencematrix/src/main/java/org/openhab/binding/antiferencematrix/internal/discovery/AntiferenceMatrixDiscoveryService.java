package org.openhab.binding.antiferencematrix.internal.discovery;

import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.openhab.binding.antiferencematrix.AntiferenceMatrixBindingConstants;
import org.openhab.binding.antiferencematrix.handler.AntiferenceMatrixBridgeHandler;

public class AntiferenceMatrixDiscoveryService extends AbstractDiscoveryService {

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
        // TODO Auto-generated method stub
        super.startBackgroundDiscovery();
    }

    @Override
    protected void stopBackgroundDiscovery() {
        // TODO Auto-generated method stub
        super.stopBackgroundDiscovery();
    }

    private void doDiscovery() {
        bridgeHandler.updatePortStatus();
    }

}
