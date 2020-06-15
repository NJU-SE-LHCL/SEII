<template>
    <a-modal
            :visible="orderDetailVisible"
            title="订单详情"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="cancel"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->

        <a-form :form="form" style="margin-top: 30px" >

            <a-form-item label="酒店名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.hotelName }}</span>
            </a-form-item>
            <a-form-item label="入住时间" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.checkInDate }}</span>
            </a-form-item>
            <a-form-item label="离店时间" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.checkOutDate}}</span>
            </a-form-item>
            <a-form-item label="房间类型" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.roomType }}</span>
            </a-form-item><a-form-item label="房间数量" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.roomNum }}</span>
            </a-form-item>
            <a-form-item label="入住人数" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.peopleNum }}</span>
            </a-form-item>
            <a-form-item label="孩童人数" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.haveChild }}</span>
            </a-form-item>
            <a-form-item label="订单创建时间" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.createDate }}</span>
            </a-form-item>
            <a-form-item label="用户姓名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.clientName }}</span>
            </a-form-item>
            <a-form-item label="用户电话" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.phoneNumber }}</span>
            </a-form-item>
            <a-form-item label="订单中状态" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetail.orderState }}</span>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'orderDetail',
        data() {
            return{

            }
        },
        computed: {
            ...mapGetters([
                'userInfo',
                'orderDetail',
                'orderDetailVisible',
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'orderDetail' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_orderDetailVisible'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'getOrderDetail',
                //'set_addCouponVisible'
            ]),
            cancel() {
                this.set_orderDetailVisible(false)
            },
            changeType(v){
                if( v == '3') {

                }else{
                    this.$message.warning('请实现')
                }
            },


        }
    }
</script>