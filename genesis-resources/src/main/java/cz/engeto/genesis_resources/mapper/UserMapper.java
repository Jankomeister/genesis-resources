package cz.engeto.genesis_resources.mapper;

import cz.engeto.genesis_resources.dto.UserDetailDto;
import cz.engeto.genesis_resources.dto.UserDto;
import cz.engeto.genesis_resources.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }

    public static UserDetailDto toDetailDto(User user) {
        return UserDetailDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .personID(user.getPersonID())
                .uuid(user.getUuid())
                .build();
    }
}
