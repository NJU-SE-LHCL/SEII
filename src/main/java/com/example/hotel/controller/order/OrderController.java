package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */


@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{userid}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable int userid){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }

    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid){

        return orderService.annulOrder(orderid);
    }
    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }
    @GetMapping("/{orderid}/getOrderDetail")
    public ResponseVO getOrderDetail(@PathVariable Integer orderid){
        return ResponseVO.buildSuccess(orderService.getOrderDetail(orderid));
    }
    @GetMapping("/{orderid}/checkInOrder")
    public ResponseVO checkInOrder(@PathVariable Integer orderid){
        return ResponseVO.buildSuccess(orderService.checkInOrder(orderid));
    }
    @GetMapping("/{orderid}/deleteOrder")
    public ResponseVO deleteOrder(@PathVariable Integer orderid){
        return ResponseVO.buildSuccess(orderService.deleteOrder(orderid));
    }
    @GetMapping("/{orderid}/setAbnormalOrder")
    public ResponseVO setAbnormalOrder(@PathVariable Integer orderid){
        return ResponseVO.buildSuccess(orderService.setAbnormalOrder(orderid));
    }
    @GetMapping("/getUserOrdersForHotel")
    public ResponseVO getUserOrdersForHotel(@RequestParam Integer userid,@RequestParam Integer hotelid){
        return ResponseVO.buildSuccess(orderService.getUserOrdersForHotel(userid,hotelid));
    }
}
