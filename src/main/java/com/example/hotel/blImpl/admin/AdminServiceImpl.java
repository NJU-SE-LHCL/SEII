package com.example.hotel.blImpl.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_FAILURE = "更新失败";
    @Autowired
    AdminMapper adminMapper;
    @Override
    public ResponseVO addManager(UserVO uservo) {
        User user = new User();
        BeanUtils.copyProperties(uservo,user);
        String email=user.getEmail();
        int count=adminMapper.judge_exist(email);//判断个数，如果已存在，添加失败
        if(count==1){
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        else {
            adminMapper.addUser(user);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<User> getAllManagers() {
        return adminMapper.getAllManagers();
    }

    @Override
    public List<User> getAllClients() {
        return adminMapper.getAllClients();
    }

    @Override
    public ResponseVO deleteUser(Integer userid) {
        try {
            adminMapper.deleteUser(userid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseVO.buildSuccess(true);
    }
    @Override
    public ResponseVO updateInfo(int id, String email,String password,String username,double credit,String phonenumber){
        try {
            adminMapper.updateAccount(id,email, password, username,credit, phonenumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_FAILURE);
        }
        return ResponseVO.buildSuccess(true);
    }

}

