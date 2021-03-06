import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
}

export function getManagerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllManagers`,
        method: 'POST'
    })
}

export function getClientListAPI(){
    return axios({
        url: `${api.adminPre}/getAllClients`,
        method: 'POST'
    })
}

export function addManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/addUser`,
        method: 'POST',
        data
    })
}

export function deleteUserAPI(userId) {
    return axios({
        url:`${api.adminPre}/${userId}/deleteUser`,
        method:'GET',
    })
}

export function changeUserInfoAPI(data) {
    return axios({
        url: `${api.adminPre}/${data.id}/userInfo/update`,
        method: 'POST',
        data
    })
}