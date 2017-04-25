package org.openhab.binding.antiferencematrix.internal.response;

public class SystemDetailsResponse extends MatrixResponse {

    private String model;
    private String version;
    private String serial;
    private String mac;
    private int boardRev;
    private String statusMessage;
    private int status;

    /*
     * {
     * "Result":true,
     * "Model":"FFMB44",
     * "Version":"1.6.1-1",
     * "Serial":"P8SN1004714",
     * "MAC":"00:15:82:0F:54:AA",
     * "BoardRev":0,
     * "StatusMessage":"Healthy",
     * "Status":0
     * }
     */

}
