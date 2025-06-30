package com.ganpati.spring_security1.dto;

import com.ganpati.spring_security1.entity.Role;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
