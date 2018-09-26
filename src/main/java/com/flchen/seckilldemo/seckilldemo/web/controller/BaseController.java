package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.commom.CommomConstants;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;

/**
 * @author feilongchen
 * @since 2018-09-20 9:54 AM
 */
public abstract class BaseController {

	protected ResponseMO error(String message) {
		ResponseMO responseMO = new ResponseMO();
		responseMO.setMessage(message);
		responseMO.setCode(CommomConstants.RESPONSE_CODE_ERROR);
		return responseMO;
	}

	protected ResponseMO success() {
		ResponseMO responseMO = new ResponseMO();
		responseMO.setMessage("");
		responseMO.setCode(CommomConstants.RESPONSE_CODE_SUCCESS);
		return responseMO;
	}

	protected ResponseMO success(String message) {
		ResponseMO responseMO = new ResponseMO();
		responseMO.setMessage(message);
		responseMO.setCode(CommomConstants.RESPONSE_CODE_SUCCESS);
		return responseMO;
	}
	protected <T> ResponseMO success(String message, T data) {
		ResponseMO responseMO = new ResponseMO();
		responseMO.setMessage(message);
		responseMO.setCode(CommomConstants.RESPONSE_CODE_SUCCESS);
		responseMO.setData(data);
		return responseMO;
	}
}
