package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.RoomVO;
import com.example.hotel.vo.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private AccountService accountService;

    //@Autowired
    //private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @Override
    public void addHotel(HotelVO hotelVO) throws ServiceException {
        User manager = accountService.getUserInfo(hotelVO.getManagerId());
        if(manager == null || !manager.getUserType().equals(UserType.HotelManager)){
            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.insertHotel(hotel);
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomService.updateRoomInfo(hotelId,roomType,rooms);
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomService.getRoomCurNum(hotelId,roomType);
    }

    @Override
    public List<HotelVO> retrieveHotels() {

        return hotelMapper.selectAllHotel();
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setCurNum(r.getCurNum());
            roomVO.setTotal(r.getTotal());
            return roomVO;
        }).collect(Collectors.toList());
        hotelVO.setRooms(roomVOS);

        return hotelVO;
    }
    @Override
    public void updateHotelInfo(Integer hotelId,HotelVO hotelVO){
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.updateHotelInfo(hotelId,hotel);
    }
    @Override
    public List<HotelVO> getFilteredHotels(SearchVO searchVO){

        List<HotelVO> temp=hotelMapper.selectAllHotel();
        List<HotelVO> res= new ArrayList<>();
        System.out.println(temp.get(0).getAddress());
        for(int i=0;i<temp.size();i++){

            List<HotelRoom> rooms=roomService.retrieveHotelRoomInfo(temp.get(i).getId());
            if(searchVO.getAddress().equals(searchVO.getName())&&searchVO.getCheckInDate().equals("0")){
                if(temp.get(i).getName().contains(searchVO.getName()) ||temp.get(i).getAddress().contains(searchVO.getAddress())||temp.get(i).getBizRegion().contains(searchVO.getBizRegion())){
                    res.add(temp.get(i));
                }
            }
            else if(temp.get(i).getName().contains(searchVO.getName()) &&temp.get(i).getAddress().contains(searchVO.getAddress())&&temp.get(i).getBizRegion().contains(searchVO.getBizRegion())
            && (searchVO.getLowerRate()<=temp.get(i).getRate()&&temp.get(i).getRate()<=searchVO.getUpperRate())){
                for (int k = 0; k < rooms.size(); k++) {
                    if ((searchVO.getLowerPrice() <= rooms.get(k).getPrice() && searchVO.getUpperPrice() >= rooms.get(k).getPrice()) && rooms.get(k).getRoomType().toString().equals(searchVO.getRoomType())) {
                        res.add(temp.get(i));
                        break;
                    }
                }

            }

        }
        return res;
    }

}
