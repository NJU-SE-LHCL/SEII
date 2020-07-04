package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

        @Autowired
        OrderService orderService;
        @Autowired
        OrderMapper orderMapper;
        @Autowired
        HotelService hotelService;

        @Test
        public void addOrder() {
            Order order = new Order();
            order.setUserId(4);
            order.setHotelId(2);
            order.setHotelName("儒家酒店");
            order.setCheckInDate("2020-06-20");
            order.setCheckOutDate("2020-06-21");
            order.setRoomType("Family");
            order.setRoomNum(1);
            order.setPeopleNum(1);
            order.setHaveChild(false);
            order.setPrice(399.0);
            order.setOrderState("已预订");
            orderMapper.addOrder(order);
            hotelService.updateRoomInfo(order.getHotelId(), order.getRoomType(), order.getRoomNum());
        }

        @Test
        public void getAllOrders() {
            List<Order> orderList = orderService.getAllOrders();
            int size = orderList.size();
            Order order = orderList.get(size - 1);
            Assert.assertEquals((int) order.getId(), 15);
        }

        @Test
        public void getUserOrders() {
            int userid = 4;
            List<Order> orderList = orderService.getUserOrders(userid);
            int size = orderList.size();
            Order order = orderList.get(size - 1);
            Assert.assertEquals((int) order.getId(), 15);
        }

        @Test
        public void annulOrder() {
            int orderId = 15;
            orderMapper.annulOrder(orderId);
            Order order = orderMapper.getOrderById(orderId);
            Assert.assertEquals(order.getOrderState(), "已撤销");
        }

        @Test
        public void getHotelOrders() {
            int hotelId = 2;
            List<Order> orderList = orderService.getHotelOrders(hotelId);
            int size = orderList.size();
            Order order = orderList.get(size - 1);
            Assert.assertEquals((int) order.getId(), 15);
        }

        @Test
        public void getOrderDetail() {
            int orderId = 15;
            Order order = orderMapper.getOrderById(orderId);
            Assert.assertEquals((int) order.getUserId(), 4);
        }

        @Test
        public void checkInOrder() {
            int orderId = 15;
            orderService.checkInOrder(orderId);
            Order order = orderMapper.getOrderById(orderId);
            Assert.assertEquals(order.getOrderState(), "已入住");
        }

        @Test
        public void deleteOrder() {
            int orderId = 14;
            orderService.deleteOrder(orderId);
            List<Order> orderList = orderService.getAllOrders();
            int size = orderList.size();
            Assert.assertEquals(size, 1);
        }

        @Test
        public void setAbnormalOrder() {
            Order order1 = new Order();
            order1.setUserId(5);
            order1.setHotelId(2);
            order1.setHotelName("儒家酒店");
            order1.setCheckInDate("2020-06-20");
            order1.setCheckOutDate("2020-06-21");
            order1.setRoomType("Family");
            order1.setRoomNum(1);
            order1.setPeopleNum(1);
            order1.setHaveChild(false);
            order1.setPrice(399.0);
            order1.setOrderState("已预订");
            orderMapper.addOrder(order1);
            hotelService.updateRoomInfo(order1.getHotelId(), order1.getRoomType(), order1.getRoomNum());

            int orderId = 14;
            orderMapper.setAbnormalOrder(orderId);
            Order order = orderMapper.getOrderById(orderId);
            Assert.assertEquals(order.getOrderState(), "异常");
        }

        @Test
        public void getUserOrdersForHotel() {
            int userid = 4;
            int hotelId = 2;
            List<Order> orderList = orderService.getUserOrdersForHotel(userid, hotelId);
            int size = orderList.size();
            Order order = orderList.get(size - 1);
            Assert.assertEquals((int) order.getId(), 15);
        }
    }