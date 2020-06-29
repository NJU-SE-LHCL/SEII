package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

@Service
public class MultiRoomStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足多房间优惠策略
     * @param orderVO
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {

        return  orderVO.getRoomNum() > coupon.getRoomNumber() && coupon.getCouponType() == 2;
    }
}
