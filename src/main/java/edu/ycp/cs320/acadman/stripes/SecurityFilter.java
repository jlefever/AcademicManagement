package edu.ycp.cs320.acadman.stripes;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.acadman.model.User;
import net.sourceforge.stripes.util.StringUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class SecurityFilter implements Filter {
    private static Set<String> publicUrls = new HashSet<String>();

    static {
        publicUrls.add("/mainview/Login.jsp");
        publicUrls.add("/Login.action");
        publicUrls.add("/mainview/NoPermission.jsp");
        publicUrls.add("/NoPermission.action");
        publicUrls.add("/mainview/Test.jsp");
    }

    private static Set<String> adminUrls = new HashSet<String>();

    static {
        adminUrls.add("/mainview/Users.jsp");
        adminUrls.add("/Users.action");
    }
    
    private static Set<String> userUrls = new HashSet<String>();

    static {
        userUrls.add("/mainview/Programs.jsp");
        userUrls.add("/Programs.action");
        userUrls.add("/mainview/Outcomes.jsp");
        userUrls.add("/Outcomes.action");
        userUrls.add("/mainview/Indicators.jsp");
        userUrls.add("/Indicators.action");
        userUrls.add("/mainview/Measurements.jsp");
        userUrls.add("/Measurements.action");
    }
    
    /** Does nothing. */
    public void init(FilterConfig filterConfig) throws ServletException { }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if ( isPublicResource(request) ) {
            filterChain.doFilter(request, response);
        }
        else if (request.getSession().getAttribute("user") != null) {
        	User user = (User)request.getSession().getAttribute("user"); 
        	if(user.getPermissions() <=2 && isUserResource(request))
        	{
        		filterChain.doFilter(request, response);
        	}
        	else if(user.getPermissions() == 3 && isAdminResource(request))
        	{
        		filterChain.doFilter(request, response);
        	}
        	else
        	{
        		response.sendRedirect("/mainview/NoPermission.jsp");
        	}
        }
        else {
            // Redirect the user to the login page, noting where they were coming from
            String targetUrl = StringUtil.urlEncode(request.getServletPath());

            response.sendRedirect(
                    request.getContextPath() + "/mainview/Login.jsp?targetUrl=" + targetUrl);
        }
    }

    /**
     * Method that checks the request to see if it is for a publicly accessible resource
     */
    protected boolean isPublicResource(HttpServletRequest request) {
        String resource = request.getServletPath();

        return publicUrls.contains(request.getServletPath())
                || (!resource.endsWith(".jsp") && !resource.endsWith(".action"));
    }

    protected boolean isAdminResource(HttpServletRequest request) {
        String resource = request.getServletPath();

        return adminUrls.contains(request.getServletPath())
                || (!resource.endsWith(".jsp") && !resource.endsWith(".action"));
    }
    
    protected boolean isUserResource(HttpServletRequest request) {
        String resource = request.getServletPath();

        return userUrls.contains(request.getServletPath())
                || (!resource.endsWith(".jsp") && !resource.endsWith(".action"));
    }
    
    /** Does nothing. */
    public void destroy() { }
}