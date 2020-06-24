<template>
    <div class="hotelList">
        <a-form :form="form">
            <a-form-item  lable="搜索酒店">
                <a-input-search placeholder="请输入酒店名称或地址和商圈" style="width: 1000px;float: left" @search="onSearch"></a-input-search>
                <a-button size="middle" type="primary" @click="detailSearch" style="float: left">详细搜索</a-button>
                <br>
                <a-row :gutter="0">
                    <a-col :span="3">
                        <a-radio-group v-model="type"  style="float: left">
                            <a-radio-button value="price">
                                价格
                            </a-radio-button>
                            <a-radio-button value="star">
                                星级
                            </a-radio-button>
                            <a-radio-button value="rate">
                                评分
                            </a-radio-button>
                        </a-radio-group>
                    </a-col>
                    <a-col :span="2">
                        <a-select default-value="从高到低" v-model="order">
                            <a-select-option value="从高到低">
                                从高到低
                            </a-select-option>
                            <a-select-option value="从低到高">
                                从低到高
                            </a-select-option>
                        </a-select>
                    </a-col>
                    <a-col :span="16"></a-col>
                </a-row>
            </a-form-item>
        </a-form>
        <a-layout>
            <a-layout-content style="min-width: 800px">
                <a-spin :spinning="hotelListLoading">
                    <div class="card-wrapper">
                        <HotelCard :hotel="item" v-for="item in hotelList" :key="item.index"
                                   @click.native="jumpToDetails(item.id)"></HotelCard>
                        <div v-for="item in emptyBox" :key="item.name"
                             class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                        </div>
                        <a-pagination showQuickJumper :total="hotelList.totalElements" :defaultCurrent="1"
                                      @change="pageChange"></a-pagination>
                    </div>
                </a-spin>
            </a-layout-content>
        </a-layout>
        <DetailSearch></DetailSearch>
    </div>
</template>
<script>
    import HotelCard from './components/hotelCard'
    import DetailSearch from './components/detailSearch'
    import {mapGetters, mapActions, mapMutations} from 'vuex'

    export default {
        name: 'home',
        components: {
            HotelCard,
            DetailSearch,
        },
        data() {
            return {
                emptyBox: [{name: 'box1'}, {name: 'box2'}, {name: 'box3'}],
                type:'',
                order:'',
            }
        },
        async mounted() {
            await this.getHotelList()
        },
        computed: {
            ...mapGetters([
                'hotelList',
                'hotelListLoading'
            ])
        },
        methods: {
            ...mapMutations([
                'set_hotelListParams',
                'set_hotelListLoading',
                'set_searchValue',
                'set_detailSearchVisible',
                'set_hotelList',
            ]),
            ...mapActions([
                'getHotelList',
                'updateHotelList',
                'reorderHotelList',
            ]),

            pageChange(page, pageSize) {
                const data = {
                    pageNo: page - 1
                }
                this.set_hotelListParams(data)
                this.set_hotelListLoading(true)
                this.getHotelList()
            },
            jumpToDetails(id) {
                this.$router.push({name: 'hotelDetail', params: {hotelId: id}})
            },
            onSearch(value){
                const v={
                    name:value,
                    address:value,
                    checkInDate:0,
                    checkOutDate: 0,
                    roomType: '',
                    lowerPrice: 0,
                    upperPrice: 10000,
                    lowerRate: 1,
                    upperRate: 5,
                    bizRegion:value,
                }

                this.set_searchValue(v)
                this.set_hotelListLoading(true)
                this.updateHotelList()
            },
            detailSearch(){
                this.set_detailSearchVisible(true)
            },
        },
        watch:{
            type(val){

                const param={
                    type:this.type,
                    order:this.order,
                }
                this.reorderHotelList(param)
            },
            order(val){
                const param={
                    type:this.type,
                    order:this.order,
                }
                this.reorderHotelList(param)
            }
        }
    }
</script>
<style scoped lang="less">
    .hotelList {
        text-align: center;
        padding: 50px 0;

        .emptyBox {
            height: 0;
            margin: 10px 10px
        }

        .card-wrapper {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            flex-grow: 3;
            min-height: 800px
        }

        .card-wrapper .card-item {
            margin: 30px;
            position: relative;
            height: 188px;
        }
    }
</style>