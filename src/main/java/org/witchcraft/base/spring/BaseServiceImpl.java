package org.witchcraft.base.spring;

import java.util.Optional;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.BaseRepository;

public abstract class BaseServiceImpl<T extends BaseEntity> {
	
	public abstract BaseRepository<T> getRepository();
	
	
	//@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return getRepository().save(t);
	}

	//@Override
	public Optional<T> findOne(Long id) {
		// TODO Auto-generated method stub
		return  getRepository().findById(id);
	}

	//@Override
	public Iterable<T> findAll() {
		// TODO Auto-generated method stub
		return getRepository().findAll();
	}

	//@Override
	public void delete(Long id) {
		getRepository().delete(id);
		// TODO Auto-generated method stub
		
	}

}
