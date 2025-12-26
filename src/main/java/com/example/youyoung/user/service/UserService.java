package com.example.youyoung.user.service;

import com.example.youyoung.user.domain.User;
import com.example.youyoung.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserProxy(Long userId){
        return userRepository.getUserProxy(userId);
    }
}
