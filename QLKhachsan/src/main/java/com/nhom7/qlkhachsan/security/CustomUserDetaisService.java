package com.nhom7.qlkhachsan.security;

import com.nhom7.qlkhachsan.entity.user.Role;
import com.nhom7.qlkhachsan.entity.user.User;
import com.nhom7.qlkhachsan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetaisService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user!=null){
            UserDetails userDetails;

            HashSet grantedAuthorities = new HashSet();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//            if(user.getRoles().size()>1) {
//                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
//            }
            userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
            return userDetails;
        }else{
            new UsernameNotFoundException("login fail");
        }
        return null;
    }
}
