<template>
    <a-modal
            :visible="detail"
            title="订单详情"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="cancel"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->

        <a-form :form="form" style="margin-top: 30px" >
            <a-form-item label="酒店名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.hotelName }}</span>
            </a-form-item>
            <a-form-item label="入住时间" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.checkInDate }}</span>
            </a-form-item>
            <a-form-item label="离店时间" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.checkOutDate}}</span>
            </a-form-item>
            <a-form-item label="房间类型" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.roomType }}</span>
            </a-form-item><a-form-item label="房间数量" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.roomNum }}</span>
            </a-form-item>
            <a-form-item label="入住人数" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.peopleNum }}</span>
            </a-form-item>
            <a-form-item label="孩童人数" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.haveChild }}</span>
            </a-form-item>
            <a-form-item label="订单创建时间" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.createDate }}</span>
            </a-form-item>
            <a-form-item label="用户姓名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.clientName }}</span>
            </a-form-item>
            <a-form-item label="用户电话" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.phoneNumber }}</span>
            </a-form-item>
            <a-form-item label="金额" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.price }}</span>
            </a-form-item>
            <a-form-item label="订单中状态" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <span>{{ orderDetailForUser.orderState }}</span>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'orderDetailForUser',
        data() {
            return{

            }
        },
        computed: {
            ...mapGetters([
                'userInfo',
                'orderDetailForUser',
                'detail',
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'orderDetailForUser' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_detailVisible'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'getOrderDetail',
                //'set_addCouponVisible'
            ]),
            cancel() {
                this.set_detailVisible(false)
            },



        }
    }
</script>