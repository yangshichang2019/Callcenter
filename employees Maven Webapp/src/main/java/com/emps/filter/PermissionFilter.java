package com.emps.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.emps.model.Permission;

public class PermissionFilter extends AccessControlFilter {
	static final String LOGIN_URL ="";
	static final String UNAUTHORIZED_URL="/role/permerror";

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String[] arra = (String[]) mappedValue;
		Subject subject = getSubject(request, response);
		        if(null!=mappedValue){
		           for(String permission:arra){
		        	  if(subject.isPermitted(permission)){
		        		  return true;
		        	  }
		           }
		       
		        }
		        return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException{
		Subject subject = getSubject(request, response);
		if(subject.getPrincipal()==null){
			saveRequest(request);
			WebUtils.issueRedirect(request, response, LOGIN_URL);
		}else{
			if(StringUtils.hasText(UNAUTHORIZED_URL)){
			WebUtils.issueRedirect(request, response, UNAUTHORIZED_URL);
		} else{
			WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
		return false;
  }


}


