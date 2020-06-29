package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获得指定用户的所有订单信息
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);

    /**
     * 撤销订单
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 查看酒店的所有订单
     * @param hotelId
     * @return
     */
    List<Order> getHotelOrders(Integer hotelId);
    /**
     * 获得对应订单信息
     * @param orderid
     * @return
     */
    Order getOrderDetail(Integer orderid);
    /**
     * 标记订单状态已入住
     * @param orderid
     * @return
     */
    ResponseVO checkInOrder(Integer orderid);

    /**
     * 标记订单状态异常
     * @param orderid
     * @return
     */
    ResponseVO setAbnormalOrder(Integer orderid);
    /**
     * 返回某用户在某酒店的全部订单
     * @param userid,hotelid
     * @return
     */
    List<Order> getUserOrdersForHotel(Integer userid,Integer hotelid);
    /**
     * 删除对应订单
     * @param orderid
     * @return
     */
    ResponseVO deleteOrder(Integer orderid);
}
