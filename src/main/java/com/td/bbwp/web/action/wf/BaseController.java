package com.td.bbwp.web.action.wf;

import java.util.Optional;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.spring.BaseService;

public abstract class BaseController<T extends BaseEntity> {

	public abstract BaseService<T> getBaseService();

	// @Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return getBaseService().save(t);
	}

	// @Override
	public Optional<T> findOne(Long id) {
		// TODO Auto-generated method stub
		return getBaseService().findOne(id);
	}

	// @Override
	public Iterable<T> findAll() {
		// TODO Auto-generated method stub
		return getBaseService().findAll();
	}

	// @Override
	public void delete(Long id) {
		getBaseService().delete(id);
	}

}
