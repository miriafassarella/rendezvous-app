package com.rendezvous.domain.service;

import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.UserRepository;
import com.rendezvous.dto.user.UserRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;




}
