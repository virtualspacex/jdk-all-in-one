package cn.com.virtualspacex.model.bo;

import java.io.Serializable;

/**
 * 处理对象（缺号范围）业务实体
 */
public class SaleinfosFromLastTimeBO implements Serializable{

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
     *取引通番(sale_serial)の最大値
     */
    private String saleSerialList;
    
    private String maxSaleSerial;

	/**
     *sale_serial_end 取引通番
     */
    private String lastSaleSerialEnd;

    /**
     * sale_serial_end id
     */
    private int saleSerialEndId;

    public String getSaleSerialList() {
		return saleSerialList;
	}

	public void setSaleSerialList(String saleSerialList) {
		this.saleSerialList = saleSerialList;
	}

	public String getMaxSaleSerial() {
		return maxSaleSerial;
	}

	public void setMaxSaleSerial(String maxSaleSerial) {
		this.maxSaleSerial = maxSaleSerial;
	}

    public String getLastSaleSerialEnd() {
        return lastSaleSerialEnd;
    }

    public void setLastSaleSerialEnd(String saleSerialEnd) {
        this.lastSaleSerialEnd = saleSerialEnd;
    }

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

    public int getSaleSerialEndId() {
        return saleSerialEndId;
    }

    public void setSaleSerialEndId(int saleSerialEndId) {
        this.saleSerialEndId = saleSerialEndId;
    }
}
