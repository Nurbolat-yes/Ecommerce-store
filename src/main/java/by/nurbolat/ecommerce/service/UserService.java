package by.nurbolat.ecommerce.service;

import by.nurbolat.ecommerce.db.entity.User;
import by.nurbolat.ecommerce.dto.UserCreateDto;
import by.nurbolat.ecommerce.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserReadDto createUser(UserCreateDto userCreateDto);

    String getCurrentUsername();
}
