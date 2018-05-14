package com.cafe24.bitmall.service;

import com.cafe24.bitmall.repository.UserRepository;
import com.cafe24.bitmall.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserVo getUser(UserVo vo){
        return userRepository.getUser(vo);
    }

    public UserVo getUserByNo(Long no){return userRepository.getUserByNo(no);}

    public boolean join(UserVo vo){
        vo.setRole("user");
        return userRepository.insert(vo) > 0;
    }

    public boolean modify(UserVo vo){
        return userRepository.update(vo) > 0;
    }
}
