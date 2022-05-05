package com.example.masteryourself.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.masteryourself.model.User;
import com.example.masteryourself.model.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private final UserRepository urepository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository urepository) {
		this.urepository = urepository;
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	User curruser = urepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
    
}
