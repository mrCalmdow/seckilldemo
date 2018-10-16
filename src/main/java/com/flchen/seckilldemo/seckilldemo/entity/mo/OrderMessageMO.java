package com.flchen.seckilldemo.seckilldemo.entity.mo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author feilongchen
 * @since 2018-09-28 12:23 PM
 */
@Data
public class OrderMessageMO implements Serializable {

	private String userId;

	private String productId;

	private Integer number;
}
