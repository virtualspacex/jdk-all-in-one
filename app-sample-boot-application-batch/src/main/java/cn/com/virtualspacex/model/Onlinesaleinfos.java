package cn.com.virtualspacex.model;

/**
 * 一件明細受信テーブル
 * @author zeng-zhuanghu
 * @version 1.0
 * @date 2020/10/19
 * @since JDK8
 */
public class Onlinesaleinfos {
    /**
     * id
     */
    private String id;
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
     *コラム
     */
    private String rackNo;
    /**
     *取引通番
     */
    private String saleSerial;
    /**
     *商品コード
     */
    private  String goodCode;
    /**
     *冷熱
     */
    private String hotcold;
    /**
     *支払方式
     */
    private String payment;
    /**
     *売上価格
     */
    private String salePrice;
    /**
     *売上時間
     */
    private String saleTime;
    /**
     *ｼｽﾃﾑ時間
     */
    private String systemTime;
    /**
     *読込フラグ
     */
   private String isAggregated;
    /**
     *created_at
     */
   private String createdAt;
    /**
     *updated_at
     */
   private String updatedAt;

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

    public String getRackNo() {
        return rackNo;
    }

    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    public String getSaleSerial() {
        return saleSerial;
    }

    public void setSaleSerial(String saleSerial) {
        this.saleSerial = saleSerial;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getHotcold() {
        return hotcold;
    }

    public void setHotcold(String hotcold) {
        this.hotcold = hotcold;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public String getIsAggregated() {
        return isAggregated;
    }

    public void setIsAggregated(String isAggregated) {
        this.isAggregated = isAggregated;
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
        return "Onlinesaleinfos{" +
                "id='" + id + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", vmCode='" + vmCode + '\'' +
                ", posCode='" + posCode + '\'' +
                ", rackNo='" + rackNo + '\'' +
                ", saleSerial='" + saleSerial + '\'' +
                ", goodCode='" + goodCode + '\'' +
                ", hotcold='" + hotcold + '\'' +
                ", payment='" + payment + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", saleTime='" + saleTime + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", isAggregated='" + isAggregated + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
