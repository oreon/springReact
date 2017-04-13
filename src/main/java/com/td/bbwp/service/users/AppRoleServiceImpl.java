
package com.td.bbwp.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.users.AppRole;
import com.td.bbwp.web.action.users.AppRoleRepository;

@Service
@Transactional
public class AppRoleServiceImpl extends BaseServiceImpl<AppRole> implements AppRoleService {

	@Autowired
	private final AppRoleRepository appRoleRepository = null;

	@Override
	public BaseRepository<AppRole> getRepository() {
		return appRoleRepository;
	}
}
