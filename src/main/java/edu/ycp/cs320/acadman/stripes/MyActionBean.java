package edu.ycp.cs320.acadman.stripes;

import java.util.List;

import edu.ycp.cs320.acadman.controller.Controller;
import edu.ycp.cs320.acadman.model.Program;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;


public class MyActionBean implements ActionBean{
	
	private AMActionBeanContext context;
	//private List<Program> programs = Controller.getPrograms(3);
	
	/*@ValidateNestedProperties ({
	    @Validate(field="name", required=true, minlength=3, maxlength=200),
	    @Validate(field="description", required=false, maxlength=1000),
	    @Validate(field="year", required=true, maxlength=4)
	})*/
	
	@Override
	public AMActionBeanContext getContext() {
		return this.context;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.context = (AMActionBeanContext) context;
	}

	/*public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}*/

}
