package com.example.hotel.blImpl.hotel;

import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
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
public class RoomServiceImplTest {
    @Autowired
    RoomMapper roomMapper;

    @Test
    public void retrieveHotelRoomInfo() {
        int hotelId = 2;
        List<HotelRoom> hotelRoomList = roomMapper.selectRoomsByHotelId(hotelId);
        int size = hotelRoomList.size();
        HotelRoom hotelRoom = hotelRoomList.get(size - 1);
        Assert.assertEquals((int) hotelRoom.getPrice(),399);
    }

    @Test
    public void insertRoomInfo() {
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setTotal(20);
        hotelRoom.setCurNum(20);
        hotelRoom.setRoomType(RoomType.BigBed);
        hotelRoom.setHotelId(2);
        hotelRoom.setPrice(199);
        roomMapper.insertRoom(hotelRoom);
    }

    @Test
    public void updateRoomInfo() {
        int hotelId = 1;
        String roomType = "DoubleBed";
        //String roomType = "BigBed";
        int rooms = 30;
        roomMapper.updateRoomInfo(hotelId,roomType,rooms);
        int num = roomMapper.getRoomCurNum(hotelId,roomType);
        Assert.assertEquals(num,0);
    }

    @Test
    public void getRoomCurNum() {
        int hotelId = 2;
        String roomType = "Family";
        int num = roomMapper.getRoomCurNum(hotelId,roomType);
        Assert.assertEquals(num,10);
    }
}