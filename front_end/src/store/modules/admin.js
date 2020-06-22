import {
    getManagerListAPI,
    addManagerAPI,
    deleteUserAPI
} from '@/api/admin'
import { message } from 'ant-design-vue'
import {changeUserInfoAPI} from "../../api/admin";


const admin = {
    state: {
        managerList: [],
        addManagerModalVisible: false,
        updateUserModalVisible:false,
        addManagerParams: {
            email:'',
            password:''
        },
        userInfo:{
            id:0,
            email:'',
            password:'',
            userName:'',
            userType:'',
            phoneNumber:'',
            credit:0,
        },
    },
    mutations: {
        set_managerList: function (state, data) {
            state.managerList = data
        },
        set_addManagerModalVisible: function (state, data) {
            state.addManagerModalVisible = data
        },
        set_addManagerParams: function (state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_updateUserModalVisible: function (state, data) {
            state.updateUserModalVisible = data
        },
        set_UserInfo: function (state, data) {
            state.userInfo = {
                ...state.userInfo,
                ...data,
            }
        },
        set_activeManagerId:function (state,data) {
            state.userInfo.id=data
        }
    },
    actions: {
        getManagerList: async ({commit}) => {
            const res = await getManagerListAPI()
            if (res) {
                commit('set_managerList', res)
            }
        },
        addManager: async ({state, commit, dispatch}) => {
            const res = await addManagerAPI(state.addManagerParams)
            if (res) {
                commit('set_addManagerParams', {
                    email: '',
                    password: ''
                })
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
            } else {
                message.error('添加失败')
            }
        },

        deleteUser: async ({state, dispatch}, userId) => {
            const res = await deleteUserAPI(userId)
            if (res) {
                dispatch('getManagerList')
                message.success('修改成功')
                dispatch('getManagerList')
            } else {
                message.error('修改失败')
            }
        },

        changeInfo: async ({state, commit, dispatch}) => {
            const res = await changeUserInfoAPI(state.userInfo)
            if (res) {
                commit('set_userInfo', {
                    id:0,
                    email:'',
                    password:'',
                    username:'',
                    type:'',
                    phone:''
                })
                commit('set_updateUserModalVisible', false)
                message.success('修改成功')
                dispatch('getManagerList')
            } else {
                message.error('修改失败')
            }
        },
    }
}
export default admin