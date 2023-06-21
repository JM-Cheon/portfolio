package com.jm.portfolio.domain.users.service;

import com.jm.portfolio.domain.users.domain.UserRole;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.users.dao.UserRepository;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.domain.users.dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user = userRepository.findByEmail(Email.of(email));

        if(user == null) {
            return null;
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for(UserRole userRole : user.getUserRole()) {
            authorities.add(new SimpleGrantedAuthority(userRole.getAuthorityCode()));
        }

        return new UserDetailsImpl(user, authorities);
    }
}
