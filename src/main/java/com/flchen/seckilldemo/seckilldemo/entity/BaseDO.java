package com.flchen.seckilldemo.seckilldemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.io.Serializable;

/**
 * @author feilongchen
 * @since 2018-09-20 9:26 AM
 */
@Data
public class BaseDO implements Serializable {

	@Id
	private String id;

	private Long createTime;

	@Version
	private Long version;
}
