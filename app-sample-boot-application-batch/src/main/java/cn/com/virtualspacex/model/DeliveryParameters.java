package cn.com.virtualspacex.model;

public class DeliveryParameters {
    private String locationCode;

    private String vmCode;

    private  String posCode;

    private String token;

    private String saleSerial;

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getVmCode() {
        return vmCode;
    }

    public void setVmCode(String vmCode) {
        this.vmCode = vmCode;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSaleSerial() {
        return saleSerial;
    }

    public void setSaleSerial(String serial) {
        this.saleSerial = serial;
    }

}
