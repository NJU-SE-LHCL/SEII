import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}
export function getOrderDetailAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/getOrderDetail`,
        method:'GET',
    })
}
export function checkInOrderAPI(orderid) {
    return axios({
        url: `${api.orderPre}/${orderid}/checkInOrder`,
        method: 'GET',
    })
}
export function deleteOrderAPI(orderid) {
    return axios({
        url: `${api.orderPre}/${orderid}/deleteOrder`,
        method: 'GET',
    })
}
export  function setAbnormalOrderAPI(orderid) {
    return axios({
        url: `${api.orderPre}/${orderid}/setAbnormalOrder`,
        method: 'GET',
    })
}
export function getUserOrdersForHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/getUserOrdersForHotel`,
        method:'GET',
        params:{
            hotelid:data.hotelId,
            userid:data.userId,
        }

    })
}