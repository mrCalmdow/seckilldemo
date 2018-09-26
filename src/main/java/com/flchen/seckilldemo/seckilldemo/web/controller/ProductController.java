package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;
import com.flchen.seckilldemo.seckilldemo.repository.ProductAutoRepository;
import com.flchen.seckilldemo.seckilldemo.web.mo.ProductAddMO;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author feilongchen
 * @since 2018-09-20 11:04 AM
 */
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {

	@Autowired
	private ProductAutoRepository productAutoRepository;

	@PostMapping
	public ResponseMO addProduct(@RequestBody @Valid ProductAddMO productAddMO) {

		ProductDO productDO = productAutoRepository.findByProductName(productAddMO.getProductName());
		if(null != productDO) {
			return error("product name has exists!");
		}
		productDO = new ProductDO();
		BeanUtils.copyProperties(productAddMO, productDO);
		productAutoRepository.save(productDO);
		return success();
	}
}
