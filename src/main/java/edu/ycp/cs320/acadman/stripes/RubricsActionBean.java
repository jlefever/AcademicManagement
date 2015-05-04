package edu.ycp.cs320.acadman.stripes;

import edu.ycp.cs320.acadman.controller.Controller;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationError;
import edu.ycp.cs320.acadman.model.Rubric;

public class RubricsActionBean extends MyActionBean {
	
	private Rubric rubric;
	
	private int measurement;
	
    private int below;
    
    private int meets;
    
    private int exceeds;
    
    private int target;
    
    
    public Rubric getRubric() {return rubric;}

	public void setRubric(Rubric rubric) {this.rubric = rubric;}

	public int getMeasurement() {return measurement;}

	public void setMeasurement(int measurement) {this.measurement = measurement;}

	public int getBelow() {return below;}

	public void setBelow(int below) {this.below = below;}

	public int getMeets() {return meets;}

	public void setMeets(int meets) {this.meets = meets;}

	public int getExceeds() {return exceeds;}

	public void setExceeds(int exceeds) {this.exceeds = exceeds;}

	public int getTarget() {return target;}

	public void setTarget(int target) {this.target = target;}
    
    public Resolution edit(){
    	if(getContext().getUser().getPermissions() > 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	measurement = getContext().getMeasurement().getId();
    	Controller.editRubric(measurement, below, meets, exceeds, target);
    	rubric = Controller.getRubric(measurement);
    	Controller.Update(rubric);
    	return new ForwardResolution("mainview/Rubrics.jsp");
    }
    
    @DefaultHandler
    public Resolution view() {
    	measurement = getContext().getMeasurement().getId();
    	rubric = Controller.getRubric(measurement);
    	return new ForwardResolution("mainview/Rubrics.jsp");
    }
    
    public Resolution back(){
    	getContext().setMeasurement(null);
    	return new RedirectResolution("/Measurements.action");
    }
}