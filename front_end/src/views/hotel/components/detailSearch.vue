<template>
    <a-modal
            :visible="detailSearchVisible"
            title="详细搜索"
            cancelText="取消"
            okText="搜索"
            @cancel="cancelSearch"
            @ok="handleSubmit"
    >
        <a-form :form="form">
            <a-form-item v-bind="formItemLayout" label="输入酒店地址信息（可不填）">
                <a-input
                        v-decorator="[
                        'address',
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="输入酒店商圈信息（可不填）">
                <a-input
                        v-decorator="[
                        'bizRegion',
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="房间类型">
                <a-select style="width: 120px" v-model="type" >
                    <a-select-option value="大床房">
                        大床房
                    </a-select-option>
                    <a-select-option value="双床房">
                        双床房
                    </a-select-option>
                    <a-select-option value="家庭房">
                        家庭房
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="价格区间">
                <a-input-number v-model="lowerPrice" @change="changeLowerPrice" :min="0" :max="100000"/>
                -
                <a-input-number v-model="upperPrice" @change="changeUpperPrice" :min="0" :max="100000"/>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="有空房期间">
                <a-range-picker
                        format="YYYY-MM-DD"
                        @change="changeDate"
                        v-decorator="[
                        'date',
                    ]"
                        :placeholder="['入住日期','退房日期']"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="评分区间">
                <a-input-number v-model="lowerRate" @change="changeLowerRate" :min="1" :max="5"/>
                -
                <a-input-number v-model="upperRate" @change="changeUpperRate" :min="1" :max="5"/>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    const moment = require('moment')

    export default {
        name: 'detailSearch',
        inject:['reload'],
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
                lowerPrice:100,
                upperPrice:500,
                lowerRate:1,
                upperRate:5,
                type:'',
            }
        },
        computed: {
            ...mapGetters([
                'detailSearchVisible',
            ]),

        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'detailSearch' });
        },
        methods: {
            ...mapMutations([
                'set_detailSearchVisible',
                'set_searchValue',
            ]),
            ...mapActions([
                'updateHotelList',
            ]),
            cancelSearch() {
                this.set_detailSearchVisible(false)
            },


            handleSubmit(e) {
                this.$message.success('刚开始')

                //e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        this.$message.success(moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'))
                        const data = {
                            name:'',
                            address:this.form.getFieldValue('address')?this.form.getFieldValue('address'):'',
                            checkInDate: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                            checkOutDate: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
                            roomType: this.type,
                            lowerPrice: this.lowerPrice,
                            upperPrice: this.upperPrice,
                            lowerRate: this.lowerRate,
                            upperRate: this.upperRate,
                            bizRegion:this.form.getFieldValue('bizRegion')?this.form.getFieldValue('bizRegion'):'',
                        }
                        this.$message.success(data)
                        this.set_searchValue(data)
                        this.updateHotelList()
                        this.set_detailSearchVisible(false)
                        //parent.location.reload()
                    }

                });
            },
        },

    }
</script>