package com.ganpati.spring_security1.service;

import com.ganpati.spring_security1.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto addUser(UserDto userDto);
    List<UserDto> getAllUser();
    UserDto getUserById(long userId);
    UserDto updateUserById(long userId,UserDto userDto);
    String deleteUserById(long userId);
}
