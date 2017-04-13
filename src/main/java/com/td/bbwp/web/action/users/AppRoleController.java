
package com.td.bbwp.web.action.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.service.users.AppRoleService;
import com.td.bbwp.users.AppRole;

@RestController
@RequestMapping("/rest/appRoles")
public class AppRoleController extends BaseController<AppRole> {

	@Autowired
	private AppRoleService appRoleService;

	@Override
	public BaseService<AppRole> getBaseService() {
		return appRoleService;
	}

}
