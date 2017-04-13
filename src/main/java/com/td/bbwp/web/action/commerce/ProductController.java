
package com.td.bbwp.web.action.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.commerce.Product;
import com.td.bbwp.service.commerce.ProductService;

@RestController
@RequestMapping("/rest/products")
public class ProductController extends BaseController<Product> {

	@Autowired
	private ProductService productService;

	@Override
	public BaseService<Product> getBaseService() {
		return productService;
	}

}
