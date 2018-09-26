package com.flchen.seckilldemo.seckilldemo.web.mo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author feilongchen
 * @since 2018-09-20 9:52 AM
 */
@Data
public class ResponseMO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;

	private String message;

	private String debugInfo;

	private T data;

}
