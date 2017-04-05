
package com.td.bbwp.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.users.AppUser;

import com.td.bbwp.web.action.users.AppUserRepository;

@Service
@Transactional
public class AppUserServiceImpl extends BaseServiceImpl<AppUser> implements AppUserService {

	@Autowired
	private final AppUserRepository appUserRepository = null;

	@Override
	public BaseRepository<AppUser> getRepository() {
		return appUserRepository;
	}
}
