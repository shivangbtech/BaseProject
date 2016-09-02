package com.example.baseproject.parser;

/**
 * @author Shivang Goel
 */

import android.util.Log;

import com.example.baseproject.models.base.BaseSerializable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {

	private String TAG = getClass().getSimpleName();

	/**
	 * Method call to parse Login Json Data
	 * @param jsonString
	 * @return
	 */
	public BaseSerializable serializeParser(String jsonString, Class beanClass, BaseSerializable bean){
		Log.d(TAG, jsonString);
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			bean = (BaseSerializable) gson.fromJson(jsonString, beanClass);
		} catch (Exception e) {
			bean = null;
			Log.e(TAG, "Exception in Serialize json Parser", e);
			e.printStackTrace();
		}
		return bean;
	}


/*	*//**
	 * Method call to get parsed data of result bean
	 * @param obj
	 * @return ResultBean
	 *//*
	public ResultBean getResultBean(JSONObject obj){
		ResultBean resultBean = new ResultBean();
		try {
			JSONObject resultJsonObject= obj.getJSONObject("resultBean");
			resultBean.setResult(resultJsonObject.getString("result").trim());
			resultBean.setDescription(resultJsonObject.getString("description").trim());
			resultBean.setErrorCode(resultJsonObject.getString("errorCode").trim());
		} catch (Exception e) {
			CommonsObjects.logger.log("Exception in get Result Bean.....", e);
			e.printStackTrace();
		}
		return resultBean;
	}*/

}

/**
 * @author Shivang Goel
 */