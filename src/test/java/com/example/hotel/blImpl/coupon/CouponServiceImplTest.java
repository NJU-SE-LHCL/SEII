package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponServiceImplTest {
    @Autowired
    private CouponService couponService;

    @Test
    @Transactional
    public void getMatchOrderCouponTest(){
        OrderVO orderVO = new OrderVO();
        orderVO.setHotelId(3); //在这家酒店设置一张大于500的满减优惠
        orderVO.setPrice(500.0);
        List<Coupon> availableCoupons= couponService.getMatchOrderCoupon(orderVO);
        assertEquals(3,availableCoupons.get(0).getId());//填预期的那张优惠券的id
    }
}
