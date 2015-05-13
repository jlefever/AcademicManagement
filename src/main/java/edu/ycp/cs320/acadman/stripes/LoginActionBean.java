package edu.ycp.cs320.acadman.stripes;

import edu.ycp.cs320.acadman.controller.Controller;
import edu.ycp.cs320.acadman.model.User;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationError;

public class LoginActionBean extends MyActionBean {
	@Validate(required = true)
	private String username;

	@Validate(required = true)
	private String password;

	private String targetUrl;

	/** The username of the user trying to log in. */
	public void setUsername(String username) {
		this.username = username;
	}

	/** The username of the user trying to log in. */
	public String getUsername() {
		return username;
	}

	/** The password of the user trying to log in. */
	public void setPassword(String password) {
		this.password = password;
	}

	/** The password of the user trying to log in. */
	public String getPassword() {
		return password;
	}

	/**
	 * The URL the user was trying to access (null if the login page was
	 * accessed directly).
	 */
	public String getTargetUrl() {
		return targetUrl;
	}

	/**
	 * The URL the user was trying to access (null if the login page was
	 * accessed directly).
	 */
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	@DefaultHandler
	public Resolution login() {
		User user = Controller.getUser(username);

		if (user == null) {
			ValidationError error = new SimpleError("usernameDoesNotExist");
			getContext().getValidationErrors().add("username", error);
			return getContext().getSourcePageResolution();
		} else if (!user.getPassword().equals(password)) {
			ValidationError error = new SimpleError("incorrectPassword");
			getContext().getValidationErrors().add("password", error);
			return getContext().getSourcePageResolution();
		} else {
			getContext().setUser(user);
			if (this.targetUrl != null) {
				return new RedirectResolution(this.targetUrl);
			} else if (user.getPermissions() == 3) {
				return new RedirectResolution("mainview/Users.jsp");
			} else {
				return new RedirectResolution("/Programs.action");
			}
		}
	}
}