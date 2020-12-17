package cn.com.virtualspacex.model.bo;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 业务实体 主机故障
 */
public class VMFailureNumBO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * ロケーションコード
     */
    private String locationCode;
    /**
     *自動販売機コード
     */
    private String vmCode;
    /**
     *端末シリアルNo
     */
    private  String posCode;
    /**
     * sale_serial_end表 id
     */
    private String saleSerialEndId;
    /**
     * 故障数值
     */
    private BigInteger failureNum;

    private String checkAt;
    
    private String createAt;

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

    public String getSaleSerialEndId() {
        return saleSerialEndId;
    }

    public void setSaleSerialEndId(String saleSerialEndId) {
        this.saleSerialEndId = saleSerialEndId;
    }

    public BigInteger getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(BigInteger failureNum) {
        this.failureNum = failureNum;
    }

    public String getCheckAt() {
        return checkAt;
    }

    public void setCheckAt(String systemTime) {
        this.checkAt = systemTime;
    }
    
    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
