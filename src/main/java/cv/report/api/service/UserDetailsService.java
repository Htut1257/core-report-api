/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 *
 * @author myoht
 */
@Service
public class UserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        //User user = usersRepository.findByEmail(username);
        //UserDetails userDetails = User.withUsername(user.getEmail())
        //        .password(passwordEncoder.encode(user.getPassword())).roles("admin").build();
        //Mono<UserDetails> just = Mono.just(userDetails);
        //return just;
        return userService.getUserByPhEmail(username, "-")
                .flatMap(user -> {
                    UserDetails userDetails = User.withUsername(user.getPhoneNo())
                            .password(passwordEncoder.encode(user.getPassword())).roles("admin")
                            .build();
                    return Mono.just(userDetails);
                });
    }

}
