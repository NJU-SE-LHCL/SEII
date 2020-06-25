import {
    addRoomAPI,
    addHotelAPI,
    updateHotelAPI,
} from '@/api/hotelManager'
import {
    getAllOrdersAPI,
    cancelOrderAPI,
    getOrderDetailAPI,//+
    checkInOrderAPI,
    deleteOrderAPI,
    setAbnormalOrderAPI,
} from '@/api/order'
import {
    hotelAllCouponsAPI,
    hotelTargetMoneyAPI,
} from '@/api/coupon'
import {
    getHotelByIdAPI,

} from "@/api/hotel";

import { message } from 'ant-design-vue'
import {getUserInfoAPI, subCreditAPI} from "@/api/user";
import user from "./user";
import hotelList from "../../views/hotel/hotelList";
//import {cancelOrderAPI} from "../../api/order";

const hotelManager = {
    state: {
        orderList: [],
        addHotelParams: {
            name: '',
            address: '',
            bizRegion:'XiDan',
            hotelStar:'',
            rate: 0,
            description:'',
            phoneNum:'',
            managerId:'',
        },
        addHotelModalVisible: false,
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },

        addRoomModalVisible: false,

        couponVisible: false,
        addCouponVisible: false,
        orderDetailVisible:false,//
        orderDetail:{},//
        activeHotelId: 0,
        activeOrderId:0,//
        couponList: [],
        hotelVisible:false,//
        hotelInfo:{},//

    },
    mutations: {
        set_orderList: function(state, data) {
            state.orderList = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },
        set_addHotelParams: function(state, data) {
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_activeHotelId: function(state, data) {
            state.activeHotelId = data
        },
        set_couponList: function(state, data) {
            state.couponList = data
        },

        set_addCouponVisible: function(state, data) {
            state.addCouponVisible =data
        },
        set_activeOrderId:function(state,data){//+
            state.activeOrderId=data
        },
        set_orderDetailVisible:function (state,data) {//+
            state.orderDetailVisible=data
        },
        set_orderDetail:function (state,data) {//
            state.orderDetail=data
        },
        set_hotelVisible:function(state,data){//
            state.hotelVisible=data
        },
        set_hotelInfo:function (state,data) {//
            state.hotelInfo=data
        }
    },
    actions: {
        getAllOrders: async({ state, commit }) => {
            const res = await getAllOrdersAPI()
            const trueRes=[]

            for(let i=0;i<res.length;i++){
                if(res[i].hotelId===state.activeHotelId){
                    trueRes.push(res[i])
                }
            }
            if(trueRes){
                commit('set_orderList', trueRes)
            }
        },
        addHotel: async({ state, dispatch, commit }) => {
            const res = await addHotelAPI(state.addHotelParams)
            if(res){
                dispatch('getHotelList')
                commit('set_addHotelParams', {
                    name: '',
                    address: '',
                    bizRegion:'XiDan',
                    hotelStar:'',
                    rate: 0,
                    description:'',
                    phoneNum:'',
                    managerId:'',
                })
                commit('set_addHotelModalVisible', false)
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        addRoom: async({ state, dispatch, commit }) => {
            const res = await addRoomAPI(state.addRoomParams)
            if(res){
                commit('set_addRoomModalVisible', false)
                commit('set_addRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                })
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        getHotelCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(state.activeHotelId)
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_couponList',res)
            }
        },
        addHotelCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTargetMoneyAPI(data)
            if(res){
                dispatch('getHotelCoupon')
                commit('set_addCouponVisible',false)
                commit('set_couponVisible',true)
                message.success('添加策略成功')
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        annulOrder: async({ state, commit },data) => {
            await cancelOrderAPI(data)

            const res = await getAllOrdersAPI()
            if(res){
                commit('set_orderList', res)
            }
        },
        getOrderDetail: async({state,commit})=>{
            const res= await getOrderDetailAPI(state.activeOrderId)
                if(res){
                    commit('set_orderDetail',res)
                }
        },
        getHotelInfo:async ({state,commit})=>{
            const param={
                hotelId:state.activeHotelId
            }
            const res= await getHotelByIdAPI(param)
                if(res){
                    commit('set_hotelInfo',res)
                }

        },
        setHotelInfo:async ({state,commit},data)=>{
            const res = await updateHotelAPI(data)
             if(res) {
                 commit('set_hotelInfo',data)
             }
        },
        checkInOrder:async ({commit,dispatch,state})=>{
            await checkInOrderAPI(state.activeOrderId)
            const res = await getAllOrdersAPI()
            if(res){
                commit('set_orderList', res)
            }
        },
        deleteOrder:async ({commit,dispatch,state},data)=>{
            await deleteOrderAPI(data)
            const res = await getAllOrdersAPI()
            if(res){
                commit('set_orderList', res)
            }
        },
        addCredit:async ({commit,state})=>{
            const res = await getOrderDetailAPI(state.activeOrderId)
            if(res){
                commit('set_orderDetail',res)
            }
            const user = await getUserInfoAPI(state.orderDetail.userId)
            const param ={
                id:user.id,
                credit:user.credit+state.orderDetail.price
            }
            await subCreditAPI(param)
        },
        checkRoomState:async ({commit,state})=>{
            for(var i =0;i<state.orderList.length;i++){
                var orderDate=state.orderList[i].checkOutDate
                const pendix="00:00:00 UTC"
                if(Date.parse(orderDate+pendix)>=new Date()){
                    await setAbnormalOrderAPI(state.orderList[i].id)
                    const user =await getUserInfoAPI(state.orderList[i].userId)
                    const param={
                        id:state.orderList[i].id,
                        credit:user.credit-state.orderList[i].price
                    }
                    await subCreditAPI(param)
                }
            }
            const res = await getAllOrdersAPI()
            if(res){
                commit('set_orderList',res)
            }

        },


    }
}
export default hotelManager