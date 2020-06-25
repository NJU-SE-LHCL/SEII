<template>
    <div class="manageHotel-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addHotel"><a-icon type="plus" />添加酒店</a-button>
                </div>
                 <a-table
                    :columns="columns1"
                    :dataSource="hotelList"
                    bordered
                >
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="addRoom(record)">录入房间</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="info" size="small" @click="showCoupon(record)">优惠策略</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="info" size="small" @click="showHotel(record)">维护信息</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                            title="确定想删除该酒店吗？"
                            @confirm="deleteHotel(record)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除酒店</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="订单管理" key="2">
                <a-table
                    :columns="columns2"
                    :dataSource="orderList"
                    bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showOrderDetail(record)">订单详情</a-button>
                        <a-divider type="vertical" v-if="record.orderState=='已预订'"></a-divider>
                            <a-popconfirm
                                    title="你确定客户已入住吗？"
                                    @confirm="confirmCheckIn(record.id)"
                                    @cancel="cancelCancelOrder"
                                    okText="确定"
                                    cancelText="取消"
                                    v-if="record.orderState == '已预订'"
                            >
                            <a-button type="info" size="small">确认执行</a-button>
                        </a-popconfirm>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                            title="确定想删除该订单吗？"
                            @confirm="delOrder(record)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除订单</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
            
        </a-tabs>
        <AddHotelModal></AddHotelModal>
        <AddRoomModal></AddRoomModal>
        <Coupon></Coupon>
        <OrderDetail></OrderDetail>
        <HotelInfo></HotelInfo>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddHotelModal from './components/addHotelModal'
import AddRoomModal from './components/addRoomModal'
import Coupon from './components/coupon'
import OrderDetail from './components/orderDetail'
import HotelInfo from "./components/hotelInfo";
import {getOrderDetail} from "../../api/order";
const moment = require('moment')
const columns1 = [
    {  
        title: '酒店名',
        dataIndex: 'name',
    },
    {
        title: '商圈',
        dataIndex: 'bizRegion',
    },
    {
        title: '地址',
        dataIndex: 'address',
    },
    {
        title: '酒店星级',
        dataIndex: 'hotelStar'
    },
    {
        title: '评分',
        dataIndex: 'rate',
    },
    {
        title: '简介',
        dataIndex: 'description',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
const columns2 = [
    {  
        title: '订单号',
        dataIndex: 'id',
    },
    {  
        title: '酒店名',
        dataIndex: 'hotelName',
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        scopedSlots: { customRender: 'roomType' }
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '房价',
        dataIndex: 'price',
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
            columns1,
            columns2,
            form: this.$form.createForm(this, { name: 'manageHotel' }),
        }
    },
    components: {
        AddHotelModal,
        AddRoomModal,
        Coupon,
        OrderDetail,//
        HotelInfo,//
    },
    computed: {
        ...mapGetters([
            'orderList',
            'hotelList',
            'orderDetail',//
            'addHotelModalVisible',
            'addRoomModalVisible',
            'activeHotelId',
            'activeOrderId',//
            'orderDetailVisible',//
            'couponVisible',
        ]),
    },
    async mounted() {
        await this.setHotelId()
        //await this.getHotelList()
        await this.getAllOrders()
        //await this.checkRoomState()
    },
    methods: {
        ...mapMutations([
            'set_addHotelModalVisible',
            'set_addRoomModalVisible',
            'set_couponVisible',
            'set_activeHotelId',
            'set_orderDetailVisible',//
            'set_activeOrderId',//
            'set_hotelVisible',//

        ]),
        ...mapActions([
            //'getHotelList',
            'setHotelId',
            'getAllOrders',
            'getHotelCoupon',
            'annulOrder',
            'getOrderDetail',
            'getHotelInfo',
            'checkInOrder',
            'deleteOrder',
            'addCredit',
            'checkRoomState',

        ]),
        addHotel() {
            this.set_addHotelModalVisible(true)
        },
        addRoom(record) {
            this.set_activeHotelId(record.id)
            this.set_addRoomModalVisible(true)
        },
        showCoupon(record) {
            this.set_activeHotelId(record.id)
            this.set_couponVisible(true)
            this.getHotelCoupon()
        },
        deleteHotel(){

        },
        delOrder(record){
            this.deleteOrder(record.id)
        },
        showOrderDetail(record){
            this.set_activeOrderId(record.id)
            this.set_orderDetailVisible(true)
            this.getOrderDetail()
        },
        showHotel(record){
            this.set_activeHotelId(record.id)
            this.set_hotelVisible(true)
            this.getHotelInfo()
        },
        confirmCheckIn(id){
            this.set_activeOrderId(id)
            this.checkInOrder()
            this.addCredit()
        },
        checkRoomState(){
            this.checkRoomState()
        }
    }
}
</script>
<style scoped lang="less">
    .manageHotel-wrapper {
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
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>