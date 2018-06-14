package com.athena.services;

import com.athena.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    public UserService() { }

    public User getCurrentUser()
    {
        return new User();
    }

}
