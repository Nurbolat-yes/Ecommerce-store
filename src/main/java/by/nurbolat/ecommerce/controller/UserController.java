package by.nurbolat.ecommerce.controller;

import by.nurbolat.ecommerce.db.entity.UserRoles;
import by.nurbolat.ecommerce.dto.UserCreateDto;
import by.nurbolat.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String register(UserCreateDto userCreateDto){

        userService.createUser(userCreateDto);
        forceAutoLogin(userCreateDto.email(),userCreateDto.password());
        return "redirect:/products";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }


    private void forceAutoLogin(String email,String password){
        Set<SimpleGrantedAuthority> roles = Collections.singleton(UserRoles.USER.toAuthority());
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password,roles);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


}
