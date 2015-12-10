package com.alindus.iss.utils;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public class SpringUtils {

	public static String getUserName(){
		User user = SpringUtils.getPrinciple();
		if(user != null){
			return user.getName();
		}
		return "";
	}
	
	public static User getPrinciple() {
        User user = null;
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            Object principal = auth.getPrincipal();
//            if (principal instanceof User) {
//                user = (User) principal;
//            }
//        }
        return user;
    }
}
