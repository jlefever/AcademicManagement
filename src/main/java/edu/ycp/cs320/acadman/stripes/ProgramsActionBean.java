package edu.ycp.cs320.acadman.stripes;

import java.util.List;

import edu.ycp.cs320.acadman.controller.Controller;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;
import edu.ycp.cs320.acadman.model.Program;

public class ProgramsActionBean extends MyActionBean {
	private List<Program> programs;
	
    //@Validate(required=true)
    private String name;

    //@Validate(required=true)
    private String description;

    //@Validate(required=true)
    private int year;
    
    private int view;
    
    public List<Program> getPrograms() { 
    	return programs; 
    	}
    
    public void setPrograms(List<Program> programs) { 
    	this.programs = programs;
    	} 
    
    /** The username of the user trying to log in. */
    public void setView(int view) { this.view = view; }

    /** The username of the user trying to log in. */
    public int getView() { return view; }
    
    /** The username of the user trying to log in. */
    public void setName(String name) { this.name = name; }

    /** The username of the user trying to log in. */
    public String getName() { return name; }

    /** The password of the user trying to log in. */
    public void setDescription(String description) { this.description = description; }

    /** The password of the user trying to log in. */
    public String getDescription() { return description; }
    
    public void setYear(int year) {this.year = year;}
    
    public int getYear() { return year;}
    
    @DefaultHandler
    public Resolution add() {
        Controller.addProgram(name, description, year);
        programs = Controller.getPrograms(year);
        return new ForwardResolution("mainview/Programs.jsp");
    }
    
    public Resolution view(){
    	programs = Controller.getPrograms(view);
    	return new ForwardResolution("mainview/Programs.jsp");
    }
}