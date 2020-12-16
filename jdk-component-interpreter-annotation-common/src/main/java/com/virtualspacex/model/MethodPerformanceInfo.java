package com.virtualspacex.model;

import java.math.BigInteger;

public class MethodPerformanceInfo {

     //总执行次数
     private BigInteger totalCount = BigInteger.valueOf(0L);

     //异常次数
     private BigInteger errCount = BigInteger.valueOf(0L);
 
     //总耗时
     private BigInteger totalCost = BigInteger.valueOf(0L);
 
     //总耗时
     private BigInteger errCost = BigInteger.valueOf(0L);
 
     //正常平均耗时
     // private BigInteger averageCost = BigInteger.valueOf(0);
 
     //异常平均耗时
     // private BigInteger errAverageCost = BigInteger.valueOf(0);
 
     //最大耗时
     private BigInteger maxCost = BigInteger.valueOf(0L);
 
     //最短耗时
     private BigInteger minCost = BigInteger.valueOf(0L);
 
     public void errCost(BigInteger cost){
         totalCount = totalCount.add(BigInteger.valueOf(1L));
         totalCost = totalCost.add(cost);
         errCount = errCount.add(BigInteger.valueOf(1L));
         errCost = errCost.add(cost);
     }
 
     public void cost(BigInteger cost){
         totalCount = totalCount.add(BigInteger.valueOf(1L));
         totalCost = totalCost.add(cost);
 
         if(cost.compareTo(maxCost) > 0){
             maxCost = cost;
         } 
 
         if(cost.compareTo(minCost) < 0) {
             minCost = cost;
         }
     }
 
     public BigInteger getTotalCount() {
         return totalCount;
     }
 
     public BigInteger getErrCount() {
         return errCount;
     }
 
     public BigInteger getTotalCost() {
         return totalCost;
     }
 
     public BigInteger getErrCost() {
         return errCost;
     }
 
     public BigInteger getAverageCost() {
         return totalCost.divide(totalCount);
     }
 
     public BigInteger getErrAverageCost() {
         return errCost.divide(errCount);
     }
 
     public BigInteger getMaxCost() {
         return maxCost;
     }
 
     public BigInteger getMinCost() {
         return minCost;
     }
 
     @Override
     public String toString(){
         String ret = " totalCount : " + totalCount + " totalCost : " + totalCost;
         if(!totalCount.equals(0)) {
             ret = ret + " averageCost : " + totalCost.divide(totalCount);
         }
         return ret;
     }

}