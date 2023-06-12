package com.programming.rajeev.Employee.security;


import com.programming.rajeev.Employee.entity.User;
import com.programming.rajeev.Employee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig  {

    @Autowired
    private UserRepository userRepository;

    @Bean
   public UserDetailsService userDetailsService()
   {
       return (String userName)->{
           Optional<User> userDb = userRepository.findByUserName(userName);
           if(userDb.isEmpty()){
               throw new ResponseStatusException(HttpStatus.NOT_FOUND,"userNotFound");
           }
           return userDb.map(UserDetailsUserService::new).orElseThrow(RuntimeException::new);

       };
   }


   @Bean
   public PasswordEncoder passwordEncoder()
   {return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
      authenticationProvider.setPasswordEncoder(passwordEncoder());
      authenticationProvider.setUserDetailsService(userDetailsService());
      return authenticationProvider;
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
   {
       return config.getAuthenticationManager();

   }



}
