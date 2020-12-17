package cn.com.virtualspacex.model;

public class SaleSerialEnd {
    /**
     * id
     */
    private String id;
    /**
     *ロケーションコード
     */
    private String locationCode;
    /**
     *自動販売機コード
     */
    private String vmCode;
    /**
     *端末シリアルNo
     */
    private String posCode;
    /**
     *最終取引通番
     */
    private String saleSerialEnd;
    /**
     *故障回数
     */
    private String failureNum;
    /**
     *チェック対象時刻
     */
    private String checkedAt;
    /**
     *生成時刻
     */
    private String createdAt;
    /**
     *更新時刻
     */
    private  String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSaleSerialEnd() {
        return saleSerialEnd;
    }

    public void setSaleSerialEnd(String saleSerialEnd) {
        this.saleSerialEnd = saleSerialEnd;
    }

    public String getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(String failureNum) {
        this.failureNum = failureNum;
    }

    public String getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(String checkedAt) {
        this.checkedAt = checkedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "SaleSerialEnd{" +
                "id='" + id + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", vmCode='" + vmCode + '\'' +
                ", posCode='" + posCode + '\'' +
                ", saleSerialEnd='" + saleSerialEnd + '\'' +
                ", failureNum='" + failureNum + '\'' +
                ", checkedAt='" + checkedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
