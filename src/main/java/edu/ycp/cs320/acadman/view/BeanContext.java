package edu.ycp.cs320.acadman.view;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.examples.bugzooky.biz.Person;

public class BeanContext extends ActionBeanContext {

    /** Gets the currently logged in user, or null if no-one is logged in. */
    public Person getUser() {
        return (Person) getRequest().getSession().getAttribute("user");
    }

    /** Sets the currently logged in user. */
    public void setUser(Person currentUser) {
        getRequest().getSession().setAttribute("user", currentUser);
    }

    /** Logs the user out by invalidating the session. */
    public void logout() {
        getRequest().getSession().invalidate();
    }
}