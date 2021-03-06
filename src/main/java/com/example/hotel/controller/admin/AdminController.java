package com.example.hotel.controller.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.blImpl.admin.AdminServiceImpl;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@RestController()
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/addUser")
    public ResponseVO addUser(@RequestBody UserVO uservo) {

        return adminService.addUser(uservo);
    }

    @PostMapping("/getAllManagers")
    public ResponseVO getAllManagers() {
        return ResponseVO.buildSuccess(adminService.getAllManagers());
    }

    @PostMapping("/getAllClients")
    public ResponseVO getAllClients() {
        return ResponseVO.buildSuccess(adminService.getAllClients());
    }

    @GetMapping("/{userId}/deleteUser")
    public ResponseVO deleteUser(@PathVariable Integer userId) {
        return adminService.deleteUser(userId);
    }

    @PostMapping("/{id}/userInfo/update")
    public ResponseVO updateUserInfo(@RequestBody UserVO uservo){
        return adminService.updateInfo(uservo.getId(),uservo.getEmail(),uservo.getPassword(),uservo.getUserName(),
                uservo.getCredit(), uservo.getPhoneNumber());
    }
}