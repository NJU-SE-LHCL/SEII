package com.example.hotel.blImpl.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.enums.UserType;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String DELETE_FAILURE = "删除失败";
    private final static String DELETE_SUCCESS = "删除成功";
    private final static String UPDATE_FAILURE = "更新失败";
    private final static String UPDATE_SUCCESS = "更新成功";
    @Autowired
    private AdminService adminService;

    @Test
    @Transactional
    public void addManagerTest() {
        //新增管理员,这个没用到数据库里的
        UserVO uservo = new UserVO();
        uservo.setEmail("111111@qq.com");
        uservo.setPassword("123456");
        uservo.setUserType(UserType.Manager);
        ResponseVO res = adminService.addUser(uservo);
        assertEquals(true, (boolean) res.getContent());
        res = adminService.addUser(uservo);
        assertEquals(ACCOUNT_EXIST, res.getMessage());
    }

    @Test
    @Transactional
    public void addUser() {
        UserVO uservo = new UserVO();
        uservo.setEmail("111121@qq.com");
        uservo.setPassword("123456");
        uservo.setUserType(UserType.Client);
        ResponseVO res = adminService.addUser(uservo);
        assertEquals(true, (boolean) res.getContent());
        res = adminService.addUser(uservo);
        assertEquals(ACCOUNT_EXIST, res.getMessage());
    }

    @Test
    @Transactional
    public void deleteUserTest() {
        ResponseVO res = adminService.deleteUser(7);
        assertEquals(DELETE_SUCCESS, (String) res.getContent());
    }

    @Test
    @Transactional
    public void updateInfo(){
        int id=4;
        String email ="123@qq.com";
        String password ="123456";
        String username ="abc";
        double credit =100;
        String phonenumber = "123456";
        ResponseVO res = adminService.updateInfo(id,email,password,username,credit,phonenumber);
        assertEquals(UPDATE_SUCCESS, (String) res.getContent());
        int id2=1;
        ResponseVO res1 = adminService.updateInfo(id2,email,password,username,credit,phonenumber);
        assertEquals(UPDATE_FAILURE, (String) res1.getMessage());
    }
}
