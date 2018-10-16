package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;
import com.flchen.seckilldemo.seckilldemo.repository.ProductAutoRepository;
import com.flchen.seckilldemo.seckilldemo.service.ProductService;
import com.flchen.seckilldemo.seckilldemo.web.mo.ProductAddMO;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseMO addProduct(@RequestBody @Valid ProductAddMO productAddMO) {

		ProductDO productDO = productAutoRepository.findByProductName(productAddMO.getProductName());
		if (null != productDO) {
			return error("product name has exists!");
		}
		productDO = new ProductDO();
		BeanUtils.copyProperties(productAddMO, productDO);
		productAutoRepository.save(productDO);
		productService.updateProductsCache(productDO);
		return success();
	}

	@GetMapping("/{name}")
	public ResponseMO getProduct(@PathVariable @NotNull String name) {
		ProductDO productDO = productService.getProductFromCache(name);
		if (null == productDO) {
			return error(name + " not exist!");
		}
		return success(productDO);
	}

	@GetMapping
	public ResponseMO getAll() {
		List<ProductDO> products = productService.getProductsFromCache();
		if(CollectionUtils.isEmpty(products)) {
			return error("have no product");
		}
		return success(products);
	}

	@PutMapping
	public ResponseMO updateProduct(@RequestBody @Valid ProductDO productDO) {

		ProductDO product = productService.getProductFromCache(productDO.getProductName());
		product.setStock(productDO.getStock());
		product.setPrice(productDO.getPrice());
		productAutoRepository.save(product);
		productService.updateProductsCache(product);
		return success(product);
	}
}
