package org.sid.web;

import lombok.Data;

import java.util.Collection;

import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;
    
	
	  @PostMapping("/register") 
	  public AppUser register(@RequestBody UserForm userForm)
	  { return accountService.saveUser(
	  userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword()
	  ); }

	  @GetMapping("/appUsers")
	   public Collection<AppUser> getAllUsers() {
		return accountService.getAllUsers();
	}
	 
    
	 
	 
	/*
	 * @PostMapping("/register") public AppUser register(@RequestBody AppUser user)
	 * { return accountService.saveUser(user); }
	 */
    
    
}
@Data
class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
}
