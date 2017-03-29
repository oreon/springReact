package org.witchcraft.base.spring;

import java.util.stream.Stream;

import org.witchcraft.base.entity.BaseEntity;

public interface BaseService<T extends BaseEntity> {
	
	public  T save(T t) ;
	public  T findOne(Long id) ;
	public Iterable<T> findAll();
	public void delete(Long id);
	

}
