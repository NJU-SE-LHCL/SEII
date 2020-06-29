package com.example.hotel.bl.admin;

import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface AdminService {

    /**
     * 添加账号
     * @param uservo
     * @return
     */
    ResponseVO addUser(UserVO uservo);

    /**
     * 获得所有酒店管理人员信息
     * @return
     */
    List<User> getAllManagers();


    /**
     * 获得所有客户人员信息
     * @return
     */
    List<User> getAllClients();

    /**
     * 删除用户账号
     * @return
     */
    ResponseVO deleteUser(Integer userid);

    ResponseVO updateInfo(int id, String email,String password,String username,double credit,String phonenumber);

}
