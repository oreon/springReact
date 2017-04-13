package com.td.bbwp.course.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.td.bbwp.users.AppUser;
import com.td.bbwp.web.action.users.AppUserRepository;


/**
 * This class is used by spring security to authenticate and authorize user
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final AppUserRepository repository;

	@Autowired
	public UserDetailServiceImpl(AppUserRepository userRepository) {
		this.repository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	AppUser curruser = repository.findByUserName(username)
    			.orElseThrow(() -> new UsernameNotFoundException(username));
    	
    	List<String> roleNames =  curruser.getAppRoles().stream().
    			map(role -> "ROLE_" + role.getName().toUpperCase()  ).collect(Collectors.toList())
    			;
    	
    	String[] roles = new String[roleNames.size()];
    	roles = roleNames.toArray(roles);
    			
        return new org.springframework.security.core.userdetails.User(username, curruser.getPassword(), 
        		AuthorityUtils.createAuthorityList(roles));
        
    }   
    
    
    
} 