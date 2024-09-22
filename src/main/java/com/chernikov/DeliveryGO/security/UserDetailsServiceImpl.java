package com.chernikov.DeliveryGO.security;

import com.chernikov.DeliveryGO.entities.User;
import com.chernikov.DeliveryGO.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> searchUser = userRepository.findByUsername(username);
            if (searchUser.isPresent()) {
                User user = searchUser.get();
                return new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().toString())
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            }
        } catch (Exception e){
            throw new UsernameNotFoundException("User with this login not found");
        }
    }

    public User getUserByLogin(String login){
        return login != null && !login.isEmpty() ? userRepository.findByUsername(login).orElse(null) : null;
    }
}
