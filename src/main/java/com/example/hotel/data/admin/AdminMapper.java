package com.example.hotel.data.admin;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface AdminMapper {

    int addManager(User user);

    List<User> getAllManagers();

    int deleteUser(Integer userid);

    int judge_exist(String email);

    int updateAccount(@Param("id") int id,
                      @Param("email") String email,
                      @Param("password") String password,
                      @Param("userName") String username,
                      @Param("credit") double credit,
                      @Param("phoneNumber") String phonenumber);
}
