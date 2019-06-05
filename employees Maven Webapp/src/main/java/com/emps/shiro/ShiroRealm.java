package com.emps.shiro;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.emps.model.Permission;
import com.emps.model.Role;
import com.emps.model.User;
import com.emps.service.UserService;

public class ShiroRealm extends AuthorizingRealm{
	 @Resource(name=UserService.SERVICE_NAME)
	    UserService userservice;
	 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo info = null;
	    UsernamePasswordToken uptoken =  (UsernamePasswordToken) token;
	   
	    String user_name = uptoken.getUsername();
	    User user = userservice.findUserByName(user_name);
		    if(user!=null){
		    	Object principal = user_name;
		    	Object credentials = user.getPassword();
		    	String realmName = this.getName();
		    	ByteSource salt = ByteSource.Util.bytes(user_name);
		    	SimpleHash hash = new SimpleHash("MD5", credentials, salt, 1024);
		    	info = new SimpleAuthenticationInfo(principal, hash, salt, realmName);
		    	
		      }else {
		    	  throw new AuthenticationException();
		      }
		
		
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		 
	      String user_name=principals.toString();
	      List<String> rolesCollections = new ArrayList<String>();
	      List<String> permissioncollections =new ArrayList<String>();
	      List<Role> roles = userservice.findRolesByName(user_name);
          for(int i =0; i<roles.size(); i++){
        	  rolesCollections.add(roles.get(i).getRole_name());
          }	          
          List<Permission> permissions = userservice.findPermissionByRoles(rolesCollections);
	         for(int j = 0; j< permissions.size(); j++){
	        	 permissioncollections.add(permissions.get(j).getPermission_name());
	        	 System.out.println(permissions.get(j).getPermission_name());
	         }
	         SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	    //  SimpleAuthorizationInfo info1 = new SimpleAuthorizationInfo();
	         info.addRoles(rolesCollections);
	         info.addStringPermissions(permissioncollections);
	     
		return info;  
	
	}



}
