package com.emps.shiro;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.emps.model.User;
import com.emps.service.UserService;

public class SecondRealm extends AuthorizingRealm{
	 @Resource(name=UserService.SERVICE_NAME)
	    UserService userservice;
	 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo info = null;
	    UsernamePasswordToken uptoken =  (UsernamePasswordToken) token;
	   
	    String username = uptoken.getUsername();
	    User user = userservice.findUserByName(username);
		    if(user!=null){
		    	Object principal = username;
		    	Object credentials = user.getPassword();
		    	String realmName = this.getName();
		    	ByteSource salt = ByteSource.Util.bytes(username);
		    	SimpleHash hash = new SimpleHash("SHA1", credentials, salt, 1024);
		    	info = new SimpleAuthenticationInfo(principal, hash+"123", salt, realmName);
		    	
		      }else {
		    	  throw new AuthenticationException();
		      }
		
		
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
	
		return null;
	}



}
