package com.ganpati.spring_security1.service.impl;

import com.ganpati.spring_security1.dto.UserDto;
import com.ganpati.spring_security1.entity.User;
import com.ganpati.spring_security1.exception.ResourceNotFoundException;
import com.ganpati.spring_security1.repository.UserRepository;
import com.ganpati.spring_security1.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return mapper.map(savedUser,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with given id: " + userId)
        );
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUserById(long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with given id: " + userId)
        );
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = userRepository.save(user);
        return mapper.map(updatedUser, UserDto.class);
    }

    @Override
    public String deleteUserById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with given id: " + userId)
        );
        userRepository.deleteById(userId);
        return "User deleted successfully..";
    }
}
