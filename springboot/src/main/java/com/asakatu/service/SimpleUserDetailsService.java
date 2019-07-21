package com.asakatu.service;

import com.asakatu.repository.UserRepository;
import com.asakatu.entity.SimpleLoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("simpleUserDetailsService")
public class SimpleUserDetailsService implements UserDetailsService {

    private final
    UserRepository userRepository;

    public SimpleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(SimpleLoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}


