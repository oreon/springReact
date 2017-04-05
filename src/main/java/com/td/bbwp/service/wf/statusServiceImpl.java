
package com.td.bbwp.service.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.wf.status;

import com.td.bbwp.web.action.wf.statusRepository;

@Service
@Transactional
public class statusServiceImpl extends BaseServiceImpl<status> implements statusService {

	@Autowired
	private final statusRepository statusRepository = null;

	@Override
	public BaseRepository<status> getRepository() {
		return statusRepository;
	}
}
