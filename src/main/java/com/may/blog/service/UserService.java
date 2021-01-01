package com.may.blog.service;

import com.may.blog.model.User;
import com.may.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
// 스프링이 컴포넌트 스캔을 통해 Bean에 등록 해줌, IoC
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public int join(User user) throws  Exception{
        try {
            userRepository.save(user);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
