package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.SearchVO;
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
public class HotelServiceImplTest {
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    HotelService hotelService;
    @Test
    public void addHotel() {
        Hotel hotel = new Hotel();
        hotel.setHotelName("南京酒店");
        hotel.setDescription("欢迎您入住");
        hotel.setAddress("南京市鼓楼区珠江路265号");
        hotel.setBizRegion(BizRegion.XiDan);
        hotel.setHotelStar(HotelStar.Four);
        hotel.setPhoneNum("1234567890");
        hotel.setRate(4.8);
        hotel.setManagerId(1);
        hotelMapper.insertHotel(hotel);
        //Assert.assertEquals(hotel.);
    }

    @Test
    public void updateRoomInfo() {
        int hotelId = 2;
        String roomType = "BigBed";
        int rooms = 5;
        roomService.updateRoomInfo(hotelId, roomType, rooms);

        Assert.assertEquals(roomMapper.getRoomCurNum(hotelId, roomType), 15);
    }

    @Test
    public void retrieveHotels() {
        List<HotelVO> hotelList = hotelMapper.selectAllHotel();
        int size = hotelList.size();
        HotelVO hotelVO = hotelList.get(size - 1);
        Assert.assertEquals((int) hotelVO.getId(),3);
    }

    @Test
    public void retrieveHotelDetails() {
        int hotelId = 1;
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        //Hotel hotel = hotelMapper.selectById(hotelId);
        Assert.assertEquals((int) hotelVO.getManagerId(),1);
    }

    @Test
    public void getRoomCurNum() {
        int hotelId = 2;
        String roomType = "BigBed";
        int num = roomMapper.getRoomCurNum(hotelId,roomType);
        Assert.assertEquals(num,15);
    }

    @Test
    public void updateHotelInfo() {
        int hotelId = 1;
        Hotel hotel= new Hotel();
        //HotelVO hotelVO = new HotelVO();
        //hotelVO.setHotelStar("Five");
        hotel.setHotelName("汉庭酒店");
        hotel.setDescription("欢迎您入住");
        hotel.setAddress("仙林大道164号");
        hotel.setBizRegion(BizRegion.XiDan);
        hotel.setPhoneNum("1829373819");
        hotel.setRate(4.8);
        hotel.setHotelStar(HotelStar.Five);
        hotelMapper.updateHotelInfo(hotelId,hotel);
    }

    @Test
    public void getFilteredHotels() {
        SearchVO simpleSearch = new SearchVO();
        SearchVO detailSearch = new SearchVO();
        simpleSearch.setName("汉庭");
        simpleSearch.setBizRegion("汉庭");
        simpleSearch.setAddress("汉庭");
        simpleSearch.setCheckInDate("0");
        detailSearch.setCheckInDate("2020-7-11");
        detailSearch.setCheckOutDate("2020-7-12");
        detailSearch.setName("");
        detailSearch.setAddress("");
        detailSearch.setBizRegion("XiDan");
        detailSearch.setUpperPrice(500);
        detailSearch.setLowerPrice(100);
        detailSearch.setLowerRate(1);
        detailSearch.setUpperRate(5);
        detailSearch.setRoomType("双床房");
        List<HotelVO> simpleRes= hotelService.getFilteredHotels(simpleSearch);
        assertEquals(simpleRes.get(0).getId(),(Integer)1);
        List<HotelVO> detailRes= hotelService.getFilteredHotels(detailSearch);
        assertEquals(detailRes.size(),1);
    }
}