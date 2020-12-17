package cn.com.virtualspacex.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.virtualspacex.annotation.AutoExceptionHandler;
import com.google.common.collect.Lists;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.exception.BatchExceptionHandler;
import cn.com.virtualspacex.model.DeliveryParameters;
import cn.com.virtualspacex.model.LoginOutParameter;
import cn.com.virtualspacex.model.LoginParameter;
import cn.com.virtualspacex.model.SaleSerialMissed;
import cn.com.virtualspacex.model.ServiceResult;
import cn.com.virtualspacex.tasks.logger.Logger;
import cn.com.virtualspacex.tasks.properties.PropertiesManager;
import cn.com.virtualspacex.utils.HttpResponse;
import cn.com.virtualspacex.utils.HttpUtil;

public class SystemRequestService {
	
    @AutoExceptionHandler
    private BatchExceptionHandler batchExceptionHandler;
    
    /**
     * 登陆
     *
     * @return
     */
    public String login() {
    	String token = null;
    	HttpResponse response = null;
    	JSONObject responseData = null;
    	try {
	        LoginParameter loginParameter = new LoginParameter();
            loginParameter.setLoginid(PropertiesManager.LOGIN_ID);
            loginParameter.setPassword(PropertiesManager.LOGIN_PASSWORD);
	        String jsonString = JSON.toJSONString(loginParameter);
	        HttpUtil httpUtil = new HttpUtil();
 	        response = httpUtil.doPost(PropertiesManager.LOGIN_URL, jsonString, null);
            if (Constant.CODE_200 == response.statusLine.getStatusCode()) {
                if (response.entity != null) {
                	responseData = JSON.parseObject(response.entity);
                }
            } else {
                Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.TOKEN_RESPONSE_FAILED + Lists.newArrayList(response.toString()));
            }
            
	        if (null != responseData) {
	            JSONObject returninfodescription = (JSONObject) responseData.get(Constant.JSON_RETUEN);
	            if (Constant.OK.equals(returninfodescription.get(Constant.MESSAGE))) {
	                JSONObject result = (JSONObject) responseData.get(Constant.RESULT);
	                JSONObject loginresult = (JSONObject) result.get(Constant.LOGINRESULT);
	                token = (String) loginresult.get(Constant.TOKEN);
	            }
	        }
    	} catch(Throwable e) {
    		batchExceptionHandler.handle(e);
    	}
        return token;
    }
    
    /**
     * 注销
     *
     * @return
     */
    public void logout(String token) {
        //退出登录
        LoginOutParameter loginOutParameter = new LoginOutParameter();
        loginOutParameter.setToken(token);
        String loginOutParameterJson = JSON.toJSONString(loginOutParameter);
        try {
        	HttpUtil httpUtil = new HttpUtil();
        	httpUtil.doPost(PropertiesManager.LOGOUT_URL, loginOutParameterJson, token);
        } catch(Throwable e) {
    		batchExceptionHandler.handle(e);
    	}
    }
    
    /**
     * 下发处理
     *
     * @param saleSerialMissed
     */
    public ServiceResult<JSONObject> requestCallReq(String token, SaleSerialMissed saleSerialMissed) {
    	ServiceResult<JSONObject> result = new ServiceResult<JSONObject>();
        JSONObject responseData = null;
        HttpResponse response = null;
    	
    	DeliveryParameters deliveryParameters = new DeliveryParameters();
    	deliveryParameters.setToken(token);
        deliveryParameters.setLocationCode(saleSerialMissed.getLocationCode());
        deliveryParameters.setPosCode(saleSerialMissed.getPosCode());
        deliveryParameters.setVmCode(saleSerialMissed.getVmCode());
        deliveryParameters.setSaleSerial(saleSerialMissed.getSaleSerial());
        String deliveryParametersJson = JSON.toJSONString(deliveryParameters);
        
        try {
        	HttpUtil httpUtil = new HttpUtil();
        	response = httpUtil.doPost(PropertiesManager.FESALESINFOREQ_URL, deliveryParametersJson, deliveryParameters.getToken());
            //响应状态
            if (Constant.CODE_200 == response.statusLine.getStatusCode()) {
                // 获取响应实体
                if (response.entity != null) {
                	responseData = JSON.parseObject(response.entity);
                }
            } else {
                Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_W_00037, Lists.newArrayList(response.toString()));
            }
        } catch(Throwable e) {
        	batchExceptionHandler.handle(e);
        }

        if(responseData != null && Constant.CODE_200_STRING.equals(responseData.get(Constant.RETRURN_VALUE))) {
        	result.setResult(true);
        }
        
        result.setValue(responseData);
        
        return result;
    }
}
