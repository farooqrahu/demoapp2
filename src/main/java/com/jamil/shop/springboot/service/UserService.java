package com.jamil.shop.springboot.service;

import com.jamil.shop.springboot.DAO.UserDao;
import com.jamil.shop.springboot.config.SecurityUser;
import com.jamil.shop.springboot.model.Role;
import com.jamil.shop.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Iterator;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findOneByUsername(username);
        if (user == null) {
//			logger.info("User Null");
            throw new UsernameNotFoundException("UserName " + username + " Not Found");
        } else {
            for (Iterator<Role> iterator = user.getRoles().iterator(); iterator.hasNext(); ) {
                Role role = (Role) iterator.next();
//				logger.info("USERSERVICE:Role:"+role.getName());
            }
            // logger.info(""+user.getRoles().get(0));

		/*	List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
			if (username.equals("admin")) {
				auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
			}
			//String password = user.getPassword();
			//System.out.println("user " + user.getUsername());
			//System.out.println("password " + user.getPassword());
			System.out.println("auth = " + auth.get(0).getAuthority());
			//boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

			for (Role role : user.getRoles()){
				auth.add(new GrantedAuthorityImpl(role.getName()));
			}

			return new org.springframework.security.core.userdetails.User(
					user.getUsername(),
					user.getPassword(),
					user.getEnabled(),
					accountNonExpired,
					credentialsNonExpired,
					accountNonLocked,auth
					);*/

            return new SecurityUser(user);
        }

    }
}
