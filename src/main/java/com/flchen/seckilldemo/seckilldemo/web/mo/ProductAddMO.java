package com.flchen.seckilldemo.seckilldemo.web.mo;

import lombok.Data;

/**
 * @author feilongchen
 * @since 2018-09-20 11:05 AM
 */
@Data
public class ProductAddMO {

	private String productName;

	private Double price;

	private Long stock;
}
