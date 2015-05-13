package edu.ycp.cs320.acadman.stripes;

import net.sourceforge.stripes.action.ActionBeanContext;
import edu.ycp.cs320.acadman.model.Indicator;
import edu.ycp.cs320.acadman.model.Measurement;
import edu.ycp.cs320.acadman.model.Outcome;
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.User;

public class AMActionBeanContext extends ActionBeanContext {

    /** Gets the currently logged in user, or null if no-one is logged in. */
    public User getUser() {
        return (User) getRequest().getSession().getAttribute("user");
    }

    /** Sets the currently logged in user. */
    public void setUser(User currentUser) {
        getRequest().getSession().setAttribute("user", currentUser);
    }
    
    public Program getProgram(){
    	return (Program) getRequest().getSession().getAttribute("program");
    }
    
    public void setProgram(Program currentProg){
    	getRequest().getSession().setAttribute("program", currentProg);
    }
    
    public Outcome getOutcome(){
    	return (Outcome) getRequest().getSession().getAttribute("outcome");
    }
    
    public void setOutcome(Outcome currentOutcome){
    	getRequest().getSession().setAttribute("outcome", currentOutcome);
    }

    public Indicator getIndicator(){
    	return (Indicator) getRequest().getSession().getAttribute("indicator");
    }
    
    public void setIndicator(Indicator currentIndic){
    	getRequest().getSession().setAttribute("indicator", currentIndic);
    }
    
    public Measurement getMeasurement(){
    	return (Measurement) getRequest().getSession().getAttribute("measurement");
    }
    
    public void setMeasurement(Measurement currentMeasure){
    	getRequest().getSession().setAttribute("measurement", currentMeasure);
    }
    
    /** Logs the user out by invalidating the session. */
    public void logout() {
        getRequest().getSession().invalidate();
    }
}