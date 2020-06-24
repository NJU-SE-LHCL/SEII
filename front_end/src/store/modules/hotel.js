import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI,
    getFilteredHotelsAPI,
} from '@/api/hotel'
import {
    reserveHotelAPI,
    getUserOrdersAPI,
    getUserOrdersForHotelAPI,

} from '@/api/order'
import {
    orderMatchCouponsAPI,
} from '@/api/coupon'


const hotel = {
    state: {
        userId:0,
        hotelList: [
            
        ],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: true,
        currentHotelId: '',
        currentHotelInfo: {

        },
        orderModalVisible: false,
        currentOrderRoom: {

        },
        orderMatchCouponList: [

        ],
        tag:'',
        userOrdersForHotel:{},
        searchValue:{},
        detailSearchVisible:false,
    },
    mutations: {
        set_userOrdersForHotel:function(state,data){
          state.userOrdersForHotel=data
        },
        set_tag:function(state,data){
          state.tag=data
        },
        set_userId:function(state,data){
            state.userId=data
        },
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },
        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_orderMatchCouponList: function(state, data) {
            state.orderMatchCouponList = data
        },
        set_searchValue:function (state,data) {
            state.searchValue=data
        },
        set_detailSearchVisible:function (state,data) {
            state.detailSearchVisible=data
        }
    },

    actions: {
        getHotelList: async({commit, state}) => {
            const res = await getHotelsAPI()
            if(res){
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
            }
        },
        getHotelById: async({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId
            })
            if(res){
                commit('set_currentHotelInfo', res)
            }
        },
        addOrder: async({ state, commit }, data) => {
            const res = await reserveHotelAPI(data)
            console.log(res)
            if(res){
                message.success('预定成功')
                commit('set_orderModalVisible', false)
            }
        },
        getOrderMatchCoupons: async({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI(data)
            if(res){
                commit('set_orderMatchCouponList', res)
            }
        },
        getTagByHotelId:async ({state,commit})=>{
            const param={
                userId:state.userId,
            }
            const orderDetail=await getUserOrdersAPI(param)

            for(let i =0;i<orderDetail.length;i++){
                if(orderDetail[i].orderState==="已执行"){
                    commit('set_tag',"完成订单")
                }
                else if(orderDetail[i].orderState==="已撤销"){
                    commit('set_tag',"撤销订单")
                    break
                }
            }

        },
        getUserOrdersForHotel:async ({commit,state})=>{
            const param={
                userId:state.userId,
                hotelId:state.currentHotelId,
            }
          const res= await getUserOrdersForHotelAPI(param)

            if(res){
                message.success(res)
                commit('set_userOrdersForHotel',res)
            }
        },
        updateHotelList:async ({commit,state})=> {

            const res = await getFilteredHotelsAPI(state.searchValue)
            if(res){
                commit('set_hotelList',res)
                commit('set_hotelListLoading',false)
            }

        },
        reorderHotelList:async ({commit,state},data)=>{
          const list = state.hotelList

            switch (data.type) {
                case "price":
                    list.sort(sortPrice)
                    break
                case "star":
                    list.sort(sortStar)
                    break
                case "rate":
                    list.sort(sortRate)
                    break
            }
            if (data.order==="从低到高"){
                list.reverse();
            }
            commit('set_hotelList',list)
        },

    }

}
function sortPrice(a,b){
    return minPrice(a.rooms)-minPrice(b.price);
}
function sortRate(a,b){
    return a.rate-b.rate;
}
function sortStar(a,b){
    return a.hotelStar-b.hotelStar;
}
function minPrice(a) {
    let res = 10000;
    for(let i =0; i<a.length; i++){
        if(a.price<res){
            res=a.price
        }
    }
    return res
}
export default hotel