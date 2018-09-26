package com.flchen.seckilldemo.seckilldemo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author feilongchen
 * @since 2018-09-20 9:29 AM
 */
@Data
@Document(collection = "order")
public class OrderDO extends BaseDO {

	@DBRef
	private UserDO user;

	@DBRef
	private ProductDO product;

	private Integer state;

	private String message;
}
