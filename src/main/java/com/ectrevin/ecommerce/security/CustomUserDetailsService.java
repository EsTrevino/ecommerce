package com.ectrevin.ecommerce.security;

import com.ectrevin.ecommerce.model.User;
import com.ectrevin.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//this class loads a user's data given its username


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    //is used by Spring security

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException{
        //let people login with either username or email
        User user = userRepository.findByUsernameOrEmail(usernameoremail, usernameoremail).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email: " + usernameoremail)
        );
        return UserPrincipal.create(user);
    }

    //this method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id: " + id)
        );
        return UserPrincipal.create(user);
    }

}
