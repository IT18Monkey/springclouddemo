package com.whh.springboot.springdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.whh.springboot.springdemo.domain.BaseUserDetail;
import com.whh.springboot.springdemo.dto.BaseUser;
import com.whh.springboot.springdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //调用数据库查询用户信息
        BaseUser baseUser = userMapper.selectOne(new QueryWrapper<BaseUser>().lambda().eq(BaseUser::getLoginName, userName));
//        // 调用FeignClient查询角色
//        ResponseData<List<BaseRole>> baseRoleListResponseData = baseRoleService.getRoleByUserId(baseUser.getId());
//        List<BaseRole> roles;
//        if(baseRoleListResponseData.getData() == null ||  !ResponseCode.SUCCESS.getCode().equals(baseRoleListResponseData.getCode())){
//            logger.error("查询角色失败！");
//            roles = new ArrayList<>();
//        }else {
//            roles = baseRoleListResponseData.getData();
//        }

//        // 获取用户权限列表
        List<GrantedAuthority> authorities = new ArrayList();
//        roles.forEach(e -> {
//            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
//            GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleCode());
//            authorities.add(authority);
//
//        });

        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(baseUser.getLoginName(),
                baseUser.getPassword(), baseUser.getActive(), true, true, true, authorities);
        return new BaseUserDetail(baseUser, user);
    }
}
