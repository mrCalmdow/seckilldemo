package com.flchen.seckilldemo.seckilldemo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author feilongchen
 * @since 2018-09-20 9:27 AM
 */
@Data
@Document(collection = "product")
public class ProductDO extends BaseDO {

	private String productName;

	private Double price;

	private Long stock;
}
