
package com.td.bbwp.web.action.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.service.users.AppUserService;
import com.td.bbwp.users.AppUser;

@RestController
@RequestMapping("/rest/appUsers")
public class AppUserController extends BaseController<AppUser> {

	@Autowired
	private AppUserService appUserService;

	@Override
	public BaseService<AppUser> getBaseService() {
		return appUserService;
	}

}
