
package com.td.bbwp.web.action.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.service.wf.FieldService;
import com.td.bbwp.wf.Field;

@RestController
@RequestMapping("/rest/fields")
public class FieldController extends BaseController<Field> {

	@Autowired
	private FieldService fieldService;

	@Override
	public BaseService<Field> getBaseService() {
		return fieldService;
	}

}
