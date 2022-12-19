/**
 * 
 */
package com.shop.springboot.config;

import com.shop.springboot.model.Role;
import com.shop.springboot.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Farooq
 *
 */
public class SecurityUser extends User implements UserDetails
{
	private static final Logger logger = Logger.getLogger("SecurityUser");

	private static final long serialVersionUID = 1L;
	public SecurityUser(User user) {

		logger.info("******* Start:SecurityUser *******");

		
		if(user != null)
		{
			this.setUsername(user.getUsername());
			this.setName(user.getName());
			this.setId(user.getId());
			this.setPassword(user.getPassword());
			this.setRoles(user.getRoles());
		}

		logger.info("******* End:SecurityUser *******");

	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Set<Role> userRoles = this.getRoles();
		if(userRoles != null)
		{
			for (Role role : userRoles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
				authorities.add(authority);
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}

