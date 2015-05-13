package edu.ycp.cs320.acadman.stripes;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationError;
import edu.ycp.cs320.acadman.controller.Controller;
import edu.ycp.cs320.acadman.model.Program;

public class ProgramsActionBean extends MyActionBean {
	private List<Program> programs;
	
    //@Validate(required=true)
    private String name;

    //@Validate(required=true)
    private String description;

    //@Validate(required=true)
    private int year;
    
    private int viewYear;
    
    private int id;

	private int progId;

	private String newname;

	private String newdescription;

	private int newyearid;
    
	private int viewId;
	
    public List<Program> getPrograms() { return programs; }
    
    public void setPrograms(List<Program> programs) { this.programs = programs;} 
    
    public void setViewId(int id) { this.viewId = id;}
    
    public int getViewId(){ return viewId;}
    
    public void setNewyearid(int id) { this.newyearid = id; }
    
    public int getNewyearid() { return newyearid; }
    
    public void setNewdescription(String name) { this.newdescription = name; }
    
    public String getNewdescription() { return newdescription; }
    
    public void setNewname(String name) { this.newname = name; }
    
    public String getNewname() { return newname; }
    
    public void setProgId(int id) { this.progId = id; }
    
    public int getProgId() { return progId; }
    
    public void setId(int id) { this.id = id; }
    
    public int getId() { return id; }
    
    public void setViewYear(int view) { this.viewYear = view; }
    
    public int getViewYear() { return viewYear; }
    
    public void setName(String name) { this.name = name; }
    
    public String getName() { return name; }
    
    public void setDescription(String description) { this.description = description; }
    
    public String getDescription() { return description; }
    
    public void setYear(int year) {this.year = year;}
    
    public int getYear() { return year;}
    
    public Resolution add() {
    	if(getContext().getUser().getPermissions() != 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	else{	
        Controller.addProgram(name, description, year);
        programs = Controller.retrieveAllPrograms();
        return new ForwardResolution("mainview/Programs.jsp");
    	}
    }
    
    public Resolution edit(){
    	if(getContext().getUser().getPermissions() != 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	Controller.editProgram(progId, newname, newdescription, newyearid);
    	programs = Controller.retrieveAllPrograms();
    	return new ForwardResolution("mainview/Programs.jsp");
    }
    
    @DefaultHandler
    public Resolution view() {
    	programs = Controller.retrieveAllPrograms();
    	return new ForwardResolution("mainview/Programs.jsp");
    }
    
    public Resolution delete() {
    	if(getContext().getUser().getPermissions() != 2){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	Controller.deleteProgram(id);
    	programs = Controller.retrieveAllPrograms();
    	return new ForwardResolution("mainview/Programs.jsp");
    }
    
    public Resolution outcomes() {
    	Program program = Controller.getProgram(viewId);
    	getContext().setProgram(program);
    	return new RedirectResolution("/Outcomes.action");
    }
}