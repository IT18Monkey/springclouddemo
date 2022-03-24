package com.whh.springboot.springdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whh.springboot.springdemo.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
