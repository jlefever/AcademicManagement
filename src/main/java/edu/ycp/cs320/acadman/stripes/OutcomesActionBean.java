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
import edu.ycp.cs320.acadman.model.Outcome;

public class OutcomesActionBean extends MyActionBean {
	
    //@Validate(required=true)
    private String name;

    //@Validate(required=true)
    private String description;

    //@Validate(required=true)
    private int minmet;
    
    private int newminmet;
    
    private int prog;
    
    private int id;

	private int outcomeId;

	private String newname;

	private String newdescription;
	
	private int viewId;
	
	private List<Outcome> outcomes;
    
	
    public List<Outcome> getOutcomes() { return outcomes; }
    
    public void setOutcomes(List<Outcome> Outcomes) { this.outcomes = Outcomes;} 
    
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
    
    public void setOutcomeId(int id) { this.outcomeId = id; }
    
    public int getOutcomeId() { return outcomeId; }
    
    public void setId(int id) { this.id = id; }
    
    public int getId() { return id; }
    
    public void setName(String name) { this.name = name; }
    
    public String getName() { return name; }
    
    public void setDescription(String description) { this.description = description; }
    
    public String getDescription() { return description; }
    
    public void setProg(int prog) {this.prog = prog;}
    
    public int getProg() { return prog;}
    
    public Resolution add() {
    	if(getContext().getUser().getPermissions() != 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	else{
    	prog = getContext().getProgram().getId();
        Controller.addOutcome(name, description, minmet, prog);
        outcomes = Controller.retrieveOutcomes(prog);
        return new ForwardResolution("mainview/Outcomes.jsp");
    	}
    }
    
    public Resolution edit(){
    	if(getContext().getUser().getPermissions() != 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	prog = getContext().getProgram().getId();
    	Controller.editOutcome(outcomeId, newname, newdescription, newminmet, prog);
    	outcomes = Controller.retrieveOutcomes(prog);
    	return new ForwardResolution("mainview/Outcomes.jsp");
    }
    
    @DefaultHandler
    public Resolution view() {
    	prog = getContext().getProgram().getId();
    	outcomes = Controller.retrieveOutcomes(prog);
    	return new ForwardResolution("mainview/Outcomes.jsp");
    }
    
    public Resolution delete() {
    	if(getContext().getUser().getPermissions() != 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	prog = getContext().getProgram().getId();
    	Controller.deleteOutcome(id);
    	outcomes = Controller.retrieveOutcomes(prog);
    	return new ForwardResolution("mainview/Outcomes.jsp");
    }
    
    public Resolution indicators(){
    	Outcome outcome = Controller.getOutcome(viewId);
    	getContext().setOutcome(outcome);
    	return new RedirectResolution("/Indicators.action");
    }
    
    public Resolution back(){
    	getContext().setProgram(null);
    	return new RedirectResolution("mainview/Programs.jsp");
    }
}