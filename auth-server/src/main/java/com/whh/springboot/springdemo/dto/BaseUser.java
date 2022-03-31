package com.whh.springboot.springdemo.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class BaseUser {
        private Long id;
        private String name;
        private String loginName;
        private String password;
        private String salt;
        private String createBy;
        private Date createTime;
        private String updateBy;
        private Date updateTime;
        private Boolean active;
}
