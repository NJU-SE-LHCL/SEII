const getters = {
  //user
  detail:state=>state.user.detail,
  token: state => state.user.token,
  userId: state => state.user.userId,
  userInfo: state => state.user.userInfo,
  userOrderList: state => state.user.userOrderList,
  hotelListLoading: state => state.hotel.hotelListLoading,
  hotelList: state => state.hotel.hotelList,
  currentHotelInfo: state => state.hotel.currentHotelInfo,
  currentHotelId: state => state.hotel.currentHotelId,
  orderModalVisible: state => state.hotel.orderModalVisible,
  currentOrderRoom: state => state.hotel.currentOrderRoom,
  orderMatchCouponList: state => state.hotel.orderMatchCouponList,
  orderDetailForUser:state=>state.user.orderDetailForUser,
  haveCredit:state=>state.user.haveCredit,
  //admin
  updateUserModalVisible:state=>state.admin.updateUserModalVisible,
  managerList: state => state.admin.managerList,
  addManagerModalVisible: state => state.admin.addManagerModalVisible,
  addManagerParams: state => state.admin.addManagerParams,
  //hotelManager
  orderList: state => state.hotelManager.orderList,
  addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
  addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
  couponVisible: state => state.hotelManager.couponVisible,
  addCouponVisible: state => state.hotelManager.addCouponVisible,
  activeHotelId: state => state.hotelManager.activeHotelId,
  couponList: state => state.hotelManager.couponList,
  activeOrderId:state=>state.hotelManager.activeHotelId,//
  orderDetailVisible:state=>state.hotelManager.orderDetailVisible,//
  orderDetail:state=>state.hotelManager.orderDetail,//
  hotelInfo:state=>state.hotelManager.hotelInfo,//
  hotelVisible:state=>state.hotelManager.hotelVisible,//
  //hotel
  userOrdersForHotel: state=>state.hotel.userOrdersForHotel,
  tag:state=>state.hotel.tag,
  detailSearchVisible:state=>state.hotel.detailSearchVisible,
  }
  
  export default getters