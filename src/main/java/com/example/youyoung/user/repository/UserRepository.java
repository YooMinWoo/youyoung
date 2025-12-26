package com.example.youyoung.user.repository;

import com.example.youyoung.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserJpaRepository userJpaRepository;

    public User getUserProxy(Long id){
        return userJpaRepository.getReferenceById(id);
    }
}
