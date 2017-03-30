package org.witchcraft.base.spring;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.witchcraft.base.entity.BaseEntity;

public interface BaseService<T extends BaseEntity> {
	
	public  T save(T t) ;
	public  Optional<T> findOne(Long id) ;
	public Iterable<T> findAll();
	
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(Long id);
	public long count();
	

}
