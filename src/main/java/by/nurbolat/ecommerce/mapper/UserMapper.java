package by.nurbolat.ecommerce.mapper;

import by.nurbolat.ecommerce.db.entity.User;
import by.nurbolat.ecommerce.db.entity.UserRoles;
import by.nurbolat.ecommerce.dto.UserCreateDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserCreateDto, User> {

    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .name(object.name())
                .email(object.email())
                .password(object.password())
                .roles(UserRoles.USER)
                .build();
    }
}
