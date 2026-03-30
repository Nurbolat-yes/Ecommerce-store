package by.nurbolat.ecommerce.service.impl;

import by.nurbolat.ecommerce.db.entity.User;
import by.nurbolat.ecommerce.db.repository.UserRepository;
import by.nurbolat.ecommerce.dto.UserCreateDto;
import by.nurbolat.ecommerce.dto.UserReadDto;
import by.nurbolat.ecommerce.mapper.UserMapper;
import by.nurbolat.ecommerce.service.UserService;
import com.sun.nio.sctp.IllegalReceiveException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserReadDto createUser(UserCreateDto userCreateDto) {

        String encoded = passwordEncoder.encode(userCreateDto.password());
        UserCreateDto userCreateDtoEncoded = new UserCreateDto(userCreateDto.name(), userCreateDto.email(), encoded);
        User user = userRepository.save(userMapper.mapFrom(userCreateDtoEncoded));

        return new UserReadDto(user.getName(),user.getEmail());
    }

    @Override
    public String getCurrentUsername() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (userEmail.equals("anonymousUser"))
            return null;

        User user = userRepository.findUserByEmailIgnoreCase(userEmail).orElseThrow(()->new IllegalReceiveException("User by email "+userEmail+" not found!"));

        return user.getName();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmailIgnoreCase(username)
                .orElseThrow(
                () -> new UsernameNotFoundException("User with email: "+username+" not found!"));

        Set<SimpleGrantedAuthority> roles = Collections.singleton(user.getRoles().toAuthority());

        return new org.springframework.security
                .core.userdetails
                .User(user.getEmail(),user.getPassword(),roles);
    }
}
