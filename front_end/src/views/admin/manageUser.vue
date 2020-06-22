<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="账户管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                     <a-button type="primary" @click="addManager"><a-icon type="plus" />添加用户</a-button>
                </div>

                <a-table
                    :columns="columns"
                    :dataSource="managerList"
                    bordered
                >
                    <span slot="action" slot-scope="record">
                         <a-button  @click="changeManagerInfo(record)">修改信息</a-button>
                         <a-popconfirm
                                 title="确定删除账户吗?"
                                 ok-text="是"
                                 cancel-text="否"
                                 @confirm="confirmDeleteUser(record.id)"
                    >
                             <a-button type="danger" >删除用户</a-button>
                         </a-popconfirm>
                    </span>




                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddManagerModal></AddManagerModal>
        <UpdateManagerInfo></UpdateManagerInfo>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddManagerModal from './components/addManagerModal'
import UpdateManagerInfo from './components/updateManagerInfo'
import{message} from 'ant-design-vue'
const columns = [
    {  
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {  
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
    {
        title: '信用值',
        dataIndex: 'credit',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
export default {
    name: 'manageHotel',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns,
            data: [],
            form: this.$form.createForm(this, { name: 'manageUser' }),
            form2:this.$form.createForm(this, { name: 'changeInfo' })
        }
    },
    components: {
        AddManagerModal,
        UpdateManagerInfo
    },
    computed: {
        ...mapGetters([
            'addManagerModalVisible',
            'managerList',
            'updateUserModalVisible'
        ])
    },
    mounted() {
      this.getManagerList()
    },
    methods: {
        ...mapActions([
            'getManagerList',
            'deleteUser',
            'changeInfo',
        ]),
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_updateUserModalVisible',
            'set_activeManagerId',
            'set_UserInfo'
        ]),

        addManager(){
            this.set_addManagerModalVisible(true)
        },
        changeManagerInfo(data){
            this.set_UserInfo(data)
            this.set_updateUserModalVisible(true)
        },
        confirmDeleteUser(userId){
            this.deleteUser(userId)
        },
    }
}
</script>
<style scoped lang="less">
    .manageUser-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>