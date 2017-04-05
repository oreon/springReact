
package com.td.bbwp.service.commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.commerce.Product;

import com.td.bbwp.web.action.commerce.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	@Autowired
	private final ProductRepository productRepository = null;

	@Override
	public BaseRepository<Product> getRepository() {
		return productRepository;
	}
}
