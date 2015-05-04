package edu.ycp.cs320.acadman.stripes;

import java.util.List;

import edu.ycp.cs320.acadman.controller.Controller;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationError;
import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;

public class MeasurementsActionBean extends MyActionBean {
	private List<Measurement> measurements;
	
    //@Validate(required=true)
    private String name;

    //@Validate(required=true)
    private String description;

    //@Validate(required=true)
    private boolean ismet;
    
    private boolean newismet;
    
    private int indicator;
    
    private int id;

	private int measurementId;

	private String newname;

	private String newdescription;
	
	private int viewId;
    
	
    public List<Measurement> getMeasurements() { return measurements; }
     
    public void setMeasurements(List<Measurement> Measurements) { this.measurements = Measurements;} 
    
    public void setViewId(int id) { this.viewId = id;}
    
    public int getviewId() {return viewId;}
    
    public void setNewismet(boolean newismet) { this.newismet = newismet;}
    
    public boolean getNewismet() {return newismet;}
    
    public void setIsmet(boolean ismet) { this.ismet = ismet;}
    
    public boolean getIsmet(){ return ismet;}
    
    public void setNewdescription(String name) { this.newdescription = name; }
    
    public String getNewdescription() { return newdescription; }
    
    public void setNewname(String name) { this.newname = name; }
    
    public String getNewname() { return newname; }
    
    public void setMeasurementId(int id) { this.measurementId = id; }
    
    public int getMeasurementId() { return measurementId; }
    
    public void setId(int id) { this.id = id; }
    
    public int getId() { return id; }
    
    public void setName(String name) { this.name = name; }
    
    public String getName() { return name; }
    
    public void setDescription(String description) { this.description = description; }
    
    public String getDescription() { return description; }
    
    public void setIndicator(int outcome) {this.indicator = outcome;}
    
    public int getIndicator() { return indicator;}
    
    public Resolution add() {
    	if(getContext().getUser().getPermissions() > 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	else{
    	indicator = getContext().getIndicator().getId();
        Controller.addMeasurement(name, description, ismet, indicator);
        measurements = Controller.retrieveMeasurements(indicator);
        return new ForwardResolution("mainview/Measurements.jsp");
    	}
    }
    
    public Resolution edit(){
    	if(getContext().getUser().getPermissions() > 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	indicator = getContext().getIndicator().getId();
    	Controller.editMeasurement(measurementId, newname, newdescription, newismet, indicator);
    	measurements = Controller.retrieveMeasurements(indicator);
    	return new ForwardResolution("mainview/Measurements.jsp");
    }
    
    @DefaultHandler
    public Resolution view() {
    	indicator = getContext().getIndicator().getId();
    	measurements = Controller.retrieveMeasurements(indicator);
    	return new ForwardResolution("mainview/Measurements.jsp");
    }
    
    public Resolution delete() {
    	if(getContext().getUser().getPermissions() > 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	indicator = getContext().getIndicator().getId();
    	Controller.deleteMeasurement(id);
    	measurements = Controller.retrieveMeasurements(indicator);
    	return new ForwardResolution("mainview/Measurements.jsp");
    }
    
    public Resolution measurements(){
    	Measurement measurement = Controller.getMeasurement(viewId);
    	getContext().setMeasurement(measurement);
    	return new RedirectResolution("mainview/Test.jsp");
    }
    
    public Resolution back(){
    	getContext().setIndicator(null);
    	return new RedirectResolution("/Indicators.action");
    }
}