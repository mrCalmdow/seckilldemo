package com.flchen.seckilldemo.seckilldemo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author feilongchen
 * @since 2018-09-20 9:23 AM
 */
@Data
@Document(collection = "user")
public class UserDO extends BaseDO {

	private String username;

	private String phone;
}
