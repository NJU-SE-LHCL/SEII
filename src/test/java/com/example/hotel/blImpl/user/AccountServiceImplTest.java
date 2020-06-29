package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;

    @Test
    public void registerAccount() {
        User user = new User();
        user.setEmail("222@qq.com");
        user.setPassword("123456");
        user.setUserName("640");
        user.setPhoneNumber("12345678920");
        //BeanUtils.copyProperties(userVO,user);
        accountMapper.createNewAccount(user);
        Assert.assertEquals(user.getEmail(), "222@qq.com");
    }

    @Test
    public void login() {
        UserForm userForm = new UserForm();
        //User user = accountMapper.getAccountByName(userForm.getEmail());
        userForm.setEmail("123@qq.com");

        User user = accountMapper.getAccountByName(userForm.getEmail());
        Assert.assertEquals(user.getPassword(),"123123");
    }

    @Test
    public void getUserInfo() {
        int id = 5;
        User user = accountMapper.getAccountById(id);
        Assert.assertEquals(user.getEmail(),"123@qq.com");
    }

    @Test
    public void updateUserInfo() {
        int id = 5;
        String password = "123123";
        String username = "陈先生";
        String phonenumber = "12345678910";
        accountMapper.updateAccount(id, password, username, phonenumber);
        User user = accountMapper.getAccountById(id);
        Assert.assertEquals(user.getPhoneNumber(),"12345678910");
    }

    @Test
    public void subCredit() {
        int id = 5;
        double credit = 95;
        accountMapper.subCredit(id, credit);
        User user = accountService.getUserInfo(id);
        Assert.assertEquals(String.valueOf(user.getCredit()),"95.0");
    }
}