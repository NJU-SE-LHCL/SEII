package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;


    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) throws ServiceException {

        hotelService.addHotel(hotelVO);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/all")
    public ResponseVO retrieveAllHotels(){
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        roomService.insertRoomInfo(hotelRoom);
        return ResponseVO.buildSuccess();
    }

    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }
    @PostMapping("/{hotelId}/updateHotel")
    public ResponseVO updateHotelInfo(@PathVariable Integer hotelId,@RequestBody HotelVO hotelVO){
        hotelService.updateHotelInfo(hotelId,hotelVO);
        return ResponseVO.buildSuccess();
    }
    @GetMapping("/getFilteredHotels")
    public ResponseVO getFilteredHotels(@RequestParam String data){
        System.out.println(data);
        String[] source = data.split(",");
        SearchVO searchVO = new SearchVO();
        searchVO.setAddress(source[1].split("\":\"")[1].substring(0,source[1].split("\":\"")[1].length()-1));
        searchVO.setBizRegion(source[9].split("\":\"")[1].substring(0,source[9].split("\":\"")[1].length()-1));
        searchVO.setCheckInDate(source[2].split("\"")[2].substring(1));
        searchVO.setCheckOutDate(source[3].split("\"")[2].substring(1));
        searchVO.setName(source[0].split("\":\"")[1].substring(0,source[0].split("\":\"")[1].length()-1));
        searchVO.setRoomType(source[4].split("\":\"")[1].substring(0,source[4].split("\":\"")[1].length()-1));
        searchVO.setLowerPrice(Integer.valueOf(source[5].split("\"")[2].substring(1)));
        searchVO.setUpperPrice(Integer.valueOf(source[6].split("\"")[2].substring(1)));
        searchVO.setLowerRate(Integer.valueOf(source[7].split("\"")[2].substring(1)));
        searchVO.setUpperRate(Integer.valueOf(source[8].split("\"")[2].substring(1)));
        //return ResponseVO.buildSuccess();
        return ResponseVO.buildSuccess(hotelService.getFilteredHotels(searchVO));
    }
}
