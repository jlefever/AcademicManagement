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

public class IndicatorsActionBean extends MyActionBean {
	private List<Indicator> indicators;
	
    //@Validate(required=true)
    private String name;

    //@Validate(required=true)
    private String description;

    //@Validate(required=true)
    private int minmet;
    
    private int newminmet;
    
    private int outcome;
    
    private int id;

	private int indicatorId;

	private String newname;

	private String newdescription;
	
	private int viewId;
    
	
    public List<Indicator> getIndicators() { return indicators; }
     
    public void setIndicators(List<Indicator> Indicators) { this.indicators = Indicators;} 
    
    public void setViewId(int id) { this.viewId = id;}
    
    public int getviewId() {return viewId;}
    
    public void setNewminmet(int newminmet) { this.newminmet = newminmet;}
    
    public int getNewminmet() {return newminmet;}
    
    public void setMinmet(int minmet) { this.minmet = minmet;}
    
    public int getMinmet(){ return minmet;}
    
    public void setNewdescription(String name) { this.newdescription = name; }
    
    public String getNewdescription() { return newdescription; }
    
    public void setNewname(String name) { this.newname = name; }
    
    public String getNewname() { return newname; }
    
    public void setIndicatorId(int id) { this.indicatorId = id; }
    
    public int getIndicatorId() { return indicatorId; }
    
    public void setId(int id) { this.id = id; }
    
    public int getId() { return id; }
    
    public void setName(String name) { this.name = name; }
    
    public String getName() { return name; }
    
    public void setDescription(String description) { this.description = description; }
    
    public String getDescription() { return description; }
    
    public void setOutcome(int outcome) {this.outcome = outcome;}
    
    public int getOutcome() { return outcome;}
    
    public Resolution add() {
    	if(getContext().getUser().getPermissions() != 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	else{
    	outcome = getContext().getOutcome().getId();
        Controller.addIndicator(name, description, minmet, outcome);
        indicators = Controller.retrieveIndicators(outcome);
        return new ForwardResolution("mainview/Indicators.jsp");
    	}
    }
    
    public Resolution edit(){
    	if(getContext().getUser().getPermissions() != 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	outcome = getContext().getOutcome().getId();
    	Controller.editIndicator(indicatorId, newname, newdescription, newminmet, outcome);
    	indicators = Controller.retrieveIndicators(outcome);
    	return new ForwardResolution("mainview/Indicators.jsp");
    }
    
    @DefaultHandler
    public Resolution view() {
    	outcome = getContext().getOutcome().getId();
    	indicators = Controller.retrieveIndicators(outcome);
    	return new ForwardResolution("mainview/Indicators.jsp");
    }
    
    public Resolution delete() {
    	if(getContext().getUser().getPermissions() != 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	outcome = getContext().getOutcome().getId();
    	Controller.deleteIndicator(id);
    	indicators = Controller.retrieveIndicators(outcome);
    	return new ForwardResolution("mainview/Indicators.jsp");
    }
    
    public Resolution measurements(){
    	Indicator indicator = Controller.getIndicator(viewId);
    	getContext().setIndicator(indicator);
    	return new RedirectResolution("/Measurements.action");
    }
    
    public Resolution back(){
    	getContext().setOutcome(null);
    	return new RedirectResolution("/Outcomes.action");
    }
}