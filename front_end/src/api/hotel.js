import { axios } from '@/utils/request'
import {message} from "ant-design-vue";
const api = {
    hotelPre: '/api/hotel'
}
export function getHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'get',
    })
}
export function getHotelByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/detail`,
        method: 'GET',
    })
}

export function getFilteredHotelsAPI(data) {
    return axios({
        url: `${api.hotelPre}/getFilteredHotels`,
        method:'GET',
        params:{
            data:data,
            // name:data.data,
            // bizRegion:data.bizRegion,
            // address:data.address,
            // roomType:data.roomType,
            // lowerPrice:data.lowerPrice,
            // upperPrice:data.upperPrice,
            // lowerRate:data.lowerRate,
            // upperRate:data.upperPrice,
            // checkInData:data.checkInData,
            // checkOutDate:data.checkOutDate,
        }
    })
}