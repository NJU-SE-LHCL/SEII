import Vue from 'vue'
import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { message } from 'ant-design-vue'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,//
    subCreditAPI
} from '@/api/user'

import {
    getUserOrdersAPI,
    cancelOrderAPI,
    getOrderDetailAPI,

} from '@/api/order'

const getDefaultState = () => {
    return {
        userId: '',
        userInfo: {

        },
        haveCredit:true,
        userOrderList: [],
        detail:false,
        activeOrderID:0,
        orderDetailForUser:{},
        hotelInfo:{},
    }
}

const user = {
    state : getDefaultState(),
    mutations: {
        reset_state: function(state) {
            state.token = '',
                state.userId = '',
                state.userInfo = {

                },
                state.userOrderList = []
        },
        set_detailVisible:function(state,data){
            state.detail=data
        },
        set_orderId:function(state,data){
            state.activeOrderId=data
        },
        set_orderDetail:function(state,data){
            state.orderDetailForUser=data
        },

        set_token: function(state, token){
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },
        sub_credit:(state,data)=>{
            state.userInfo.credit-=data
        },
        set_haveCredit:(state,data)=>{
            state.haveCredit=data
        },

    },

    actions: {
        login: async ({ dispatch, commit }, userData) => {
            const res = await loginAPI(userData)
            if(res){
                setToken(res.id)
                commit('set_userId', res.id)
                dispatch('getUserInfo')
                router.push('/hotel/hotelList')
            }
        },
        register: async({ commit }, data) => {
            const res = await registerAPI(data)
            if(res){
                message.success('注册成功')
            }
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
              getUserInfoAPI(state.userId).then(response => {
                const data = response
                if (!data) {
                  reject('登录已过期，请重新登录')
                }
                commit('set_userInfo', data)
                commit('set_userId', data.id)
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
        },
        updateUserInfo: async({ state, dispatch }, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        getUserOrders: async({ state, commit }) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            if(res){
                commit('set_userOrderList', res)
                console.log(state.userOrderList)
            }
        },
        cancelOrder: async({ state, dispatch }, orderId) => {
            const res = await cancelOrderAPI(orderId)
            if(res) {
                dispatch('getUserOrders')
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        getOrderDetail: async({ state,commit }) => {
            const res = await getOrderDetailAPI(state.activeOrderId)
            if(res) {
                commit('set_orderDetail', res)
            }

        },
        logout: async({ commit }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
          // remove token
        resetToken({ commit }) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },
        subCredit:async ({commit,state},data)=>{
            const mon = await getOrderDetailAPI(data)
            const params={
                id:state.userId,
                credit:state.userInfo.credit-(mon.price)/2
            }
            const res = await subCreditAPI(params)
            if(res){
                commit('sub_credit',(mon.price)/2)
            }
        },
        routeToHotel:async ({commit,state},data)=>{
            const res=await getOrderDetailAPI(data)
            if (res){
                commit("set_orderDetail",res)
                router.push('/hotel/hotelDetail/'+state.orderDetailForUser.hotelId)
            }

        },
        judgeCredit:async ({commit,state})=>{
           commit('set_haveCredit',state.userInfo.credit >= 0)
        },

    }
}

export default user