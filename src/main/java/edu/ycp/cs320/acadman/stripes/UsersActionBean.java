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
import edu.ycp.cs320.acadman.model.Program;
import edu.ycp.cs320.acadman.model.User;

public class UsersActionBean extends MyActionBean {
	private List<User> users;
	
    //@Validate(required=true)
    private String name;

    //@Validate(required=true)
    private String email;

    private String pwd;
    
    private String newpwd;
    
    //@Validate(required=true)
    private int permissions;
    
    private String id;

	private String userId;

	private String newemail;

	private int newpermissions;
    
	public String getPwd() {return pwd;}
	
	public void setPwd(String pwd) { this.pwd = pwd;}
	
	public String getnNewpwd() {return newpwd;}
	
	public void setNewpwd(String newpwd) {this.newpwd = newpwd;}
	
    public List<User> getUsers() { return users; }
    
    public void setUsers(List<User> users) { this.users = users;} 
    
    public void setNewpermissions(int permissions) { this.newpermissions = permissions; }
    
    public int getNewpermissions() { return newpermissions; }
    
    public void setNewemail(String email) { this.newemail = email; }
    
    public String getNewemail() { return newemail; }
    
    public void setUserId(String id) { this.userId = id; }
    
    public String getUserId() { return userId; }
    
    public void setId(String id) { this.id = id; }
    
    public String getId() { return id; }
    
    public void setName(String name) { this.name = name; }
    
    public String getName() { return name; }
    
    public void setEmail(String email) { this.email = email; }
    
    public String getEmail() { return email; }
    
    public void setPermissions(int permissions) {this.permissions = permissions;}
    
    public int getPermissions() { return permissions;}
    
    public Resolution add() {
    	if(getContext().getUser().getPermissions() != 3){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	else{	
        Controller.addUser(name, email, pwd, permissions);
        users = Controller.retrieveUsers();
        return new ForwardResolution("mainview/Users.jsp");
    	}
    }
    
    public Resolution edit(){
    	if(getContext().getUser().getPermissions() != 3){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	Controller.editUser(userId, newemail, newpwd, newpermissions);
    	users = Controller.retrieveUsers();
    	return new ForwardResolution("mainview/Users.jsp");
    }
    
    @DefaultHandler
    public Resolution view() {
    	users = Controller.retrieveUsers();
    	return new ForwardResolution("mainview/Users.jsp");
    }
    
    public Resolution delete() {
    	if(getContext().getUser().getPermissions() != 3){
    		ValidationError error = new SimpleError("You do not have permission to do that.");
            getContext().getValidationErrors().add("username", error);
            return getContext().getSourcePageResolution();
    	}
    	else if(getContext().getUser().getUsername().equals(id))
    	{
    		ValidationError error = new SimpleError("You cannot delete a user while that user is logged in!");
    		getContext().getValidationErrors().add("username", error);
    		return getContext().getSourcePageResolution();
    	}
    	Controller.deleteUser(id);
    	users = Controller.retrieveUsers();
    	return new ForwardResolution("mainview/Users.jsp");
    }
}