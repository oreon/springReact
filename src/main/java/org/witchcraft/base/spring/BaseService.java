package org.witchcraft.base.spring;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.security.access.prepost.PreAuthorize;
import org.witchcraft.base.entity.BaseEntity;

public interface BaseService<T extends BaseEntity> {
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public  T save(T t) ;
	
	public  Optional<T> findOne(Long id) ;
	
	public Iterable<T> findAll();
	
	public  Stream<T> findAllStream();
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(Long id);
	
	public long count();
	

}
