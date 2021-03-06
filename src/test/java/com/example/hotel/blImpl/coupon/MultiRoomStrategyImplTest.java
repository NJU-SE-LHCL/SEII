package com.example.hotel.blImpl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiRoomStrategyImplTest {
    @Autowired
    private MultiRoomStrategyImpl multiRoomsStrategyimpl;

    @Test
    @Transactional
    public void isMatchTest(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon1 = new Coupon(); //匹配
        Coupon coupon2 = new Coupon(); //不匹配
        orderVO.setHotelId(1);
        orderVO.setRoomNum(3);
        coupon1.setHotelId(1);
        coupon2.setHotelId(1);
        coupon1.setCouponType(2);
        coupon2.setCouponType(2);
        coupon1.setRoomNumber(2);
        coupon2.setRoomNumber(4);
        assertEquals(true,multiRoomsStrategyimpl.isMatch(orderVO,coupon1));
        assertEquals(false,multiRoomsStrategyimpl.isMatch(orderVO,coupon2));
    }
}
