
package com.td.bbwp.service.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.wf.Field;

import com.td.bbwp.web.action.wf.FieldRepository;

@Service
@Transactional
public class FieldServiceImpl extends BaseServiceImpl<Field> implements FieldService {

	@Autowired
	private final FieldRepository fieldRepository = null;

	@Override
	public BaseRepository<Field> getRepository() {
		return fieldRepository;
	}
}
