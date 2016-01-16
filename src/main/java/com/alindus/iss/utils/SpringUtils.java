package com.alindus.iss.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SpringUtils {

	public static String getUserName(){
		UserDetails user = SpringUtils.getPrinciple();
		if(user != null){
			return user.getUsername();
		}
		return "";
	}
	
	public static UserDetails getPrinciple() {
		UserDetails user = null;
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (auth != null && auth instanceof UserDetails) {
                user = (UserDetails) auth;
        }
        return user;
    }
}
