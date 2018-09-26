package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;
import com.flchen.seckilldemo.seckilldemo.repository.ProductAutoRepository;
import com.flchen.seckilldemo.seckilldemo.web.BaseControllerTestCase;
import com.flchen.seckilldemo.seckilldemo.web.mo.ProductAddMO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author feilongchen
 * @since 2018-09-20 11:40 AM
 */
public class ProductControllerTest extends BaseControllerTestCase {

	@Autowired
	private ProductAutoRepository productAutoRepository;

	private String urlTemplate = "/products";

	@Before
	public void setUp() {
		productAutoRepository.deleteAll();
	}

	@Test
	public void addProducts() throws Exception {

		List<ProductAddMO> products = prepareData();
		for(ProductAddMO product : products) {
			mockMvc.perform(populatePostBuilder(urlTemplate, product));
		}
	}

	private List<ProductAddMO> prepareData() {
		List<ProductAddMO> productDOS = new ArrayList<>(4);
		ProductAddMO p1 = new ProductAddMO();
		p1.setProductName("huawei");
		p1.setStock(1000L);
		p1.setPrice(1000.0);
		productDOS.add(p1);

		ProductAddMO p2 = new ProductAddMO();
		p2.setProductName("vivo");
		p2.setStock(1000L);
		p2.setPrice(1000.0);
		productDOS.add(p2);

		ProductAddMO p3 = new ProductAddMO();
		p3.setProductName("xiaomi");
		p3.setStock(1000L);
		p3.setPrice(1000.0);
		productDOS.add(p3);

		ProductAddMO p4 = new ProductAddMO();
		p4.setProductName("iphone");
		p4.setStock(1000L);
		p4.setPrice(1000.0);
		productDOS.add(p4);
		return productDOS;
	}
}
