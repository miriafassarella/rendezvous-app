package com.rendezvous.domain.service;

import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User searchId(Long id){
        Optional<User> userId = userRepository.findById(id);
        return userId.get();
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        Optional<User> userCourrent = userRepository.findById(id);
        BeanUtils.copyProperties(user, userCourrent.get(), "id");

        return userRepository.save(userCourrent.get());
    }

    public void removeUser(Long id){
        Optional<User> userId= userRepository.findById(id);
        if (userId.isEmpty()) {
            //criar exception
        }
        userRepository.delete(userId.get());
    }
}
