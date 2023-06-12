package com.programming.rajeev.Employee.service;

import com.programming.rajeev.Employee.dto.LoginDto;
import com.programming.rajeev.Employee.dto.UserRegisterLoginResponse;
import com.programming.rajeev.Employee.entity.User;
import com.programming.rajeev.Employee.repository.UserRepository;
import com.programming.rajeev.Employee.security.jwtAuth.JwtService;
import com.programming.rajeev.Employee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserRegisterLoginResponse registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
       userRepository.save(user);
        UserRegisterLoginResponse token = new UserRegisterLoginResponse();
        System.out.println(user.getUserName());
        token.setToken(jwtService.createToken(user.getUserName()));
       return token;
    }

    @Override
    public UserRegisterLoginResponse loginUser(LoginDto loginDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getPassword()));
        if(authentication.isAuthenticated())
        {
            UserRegisterLoginResponse token = new UserRegisterLoginResponse();
            token.setToken(jwtService.createToken(loginDto.getUserName()));
            return token;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"userName or IncorrectPassword");
    }
}
