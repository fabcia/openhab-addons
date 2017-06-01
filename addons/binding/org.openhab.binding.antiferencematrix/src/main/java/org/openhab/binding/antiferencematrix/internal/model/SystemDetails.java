package org.openhab.binding.antiferencematrix.internal.model;

public class SystemDetails {

    /*
     * {
     * "Result":true,
     * "Model":"AABB11",
     * "Version":"1.0.0-0",
     * "Serial":"A1SN6195135",
     * "MAC":"XX:XX:XX:XX:XX:XX",
     * "BoardRev":0,
     * "StatusMessage":"Healthy",
     * "Status":0
     * }
     */

    private String model;
    private String version;
    private String serial;
    private String mac;
    private int boardRev;
    private String statusMessage;
    private int status;
}
