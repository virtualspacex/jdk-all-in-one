package cn.com.virtualspacex.model;

/**
 * 取引通番欠番テーブル
 *
 * @author zeng-zhuanghu
 * @version 1.0
 * @date 2020/10/19
 * @since JDK8
 */
public class SaleSerialMissed {
    /**
     * id
     */
    private int id;
    /**
     * ロケーションコード
     */
    private String locationCode;
    /**
     * 自動販売機コード
     */
    private String vmCode;
    /**
     * 端末シリアルNo.
     */
    private String posCode;
    /**
     *取引通番
     */
    private String saleSerial;
    /**
     *リトライ回数
     */
    private String retryTimes;
    /**
     *再要求状況フラグ
     */
    private String isStatus;
    /**
     *削除フラグ
     */
    private String isDeleted;
    /**
     *生成時刻
     */
    private String createdAt;
    /**
     *更新時刻
     */
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getSaleSerial() {
        return saleSerial;
    }

    public void setSaleSerial(String saleSerial) {
        this.saleSerial = saleSerial;
    }

    public String getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(String retryTimes) {
        this.retryTimes = retryTimes;
    }

    public String getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
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
        return "SaleSerialMissed{" +
                "id='" + id + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", vmCode='" + vmCode + '\'' +
                ", posCode='" + posCode + '\'' +
                ", saleSerial='" + saleSerial + '\'' +
                ", retryTimes='" + retryTimes + '\'' +
                ", isStatus='" + isStatus + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
