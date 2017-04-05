package org.witchcraft.base.entity;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

public interface BaseRepository<T extends BaseEntity> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T>{
	
	Optional<T> findById(@Param("id") Long id);
	
	Stream<T> allEntities();
	
//	@Override
//	@PreAuthorize("@taskInstanceRepository.findOne(#id)?.createdByUser.userName == authentication?.name  ||  hasRole('admin')")         
	//public Optional<T> findOne(@Param("id") Long id);
	
	

	//@Override
	//	@PreAuthorize("#taskInstance?.createdByUser.userName == authentication?.name  ||  hasRole('admin')")         
	//public T save( T taskInstance);

	@Override
	//@PreAuthorize("@taskInstanceRepository.findOne(#id)?.createdByUser.userName == authentication?.name  ||  hasRole('admin')")         
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(@Param("taskInstance") T taskInstance);

	@Override
	//	@PreAuthorize("@taskInstanceRepository.findOne(#id)?.createdByUser.userName == authentication?.name  ||  hasRole('admin')")         
	void delete(@Param("id") Long id);
	
	//Stream<T>  findAll();

	//T save(T t);

}
