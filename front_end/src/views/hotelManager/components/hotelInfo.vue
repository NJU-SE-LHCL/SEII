<template>
    <div>
        <a-modal
                :visible="hotelVisible"
                title="维护酒店信息"
                width="900px"
                :footer="null"
                cancelText="取消"

                @cancel="cancel"
        >
            <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
                <a-form-item label="酒店名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                    <a-input placeholder="请输入酒店名称"
                              v-decorator="['name']"
                              v-if="modify"
                />
                    <span v-else>{{ hotelInfo.name }}</span>
                </a-form-item>
                <a-form-item label="地址" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                    <a-input placeholder="请输入地址"
                    v-decorator="['address']"
                             v-if="modify"
                    />
                    <span v-else>{{hotelInfo.address}}</span>
                </a-form-item>
                <a-form-item label="所属商圈" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                    <a-input placeholder="请输入所属商圈"
                             v-decorator="['bizRegion']"
                             v-if="modify"
                    />
                    <span v-else>{{hotelInfo.bizRegion}}</span>
                </a-form-item>
                <a-form-item label="简介" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                    <a-input placeholder="请输入简介"
                             v-decorator="['hotelDescription']" v-if="modify"
                    />
                    <span v-else>{{hotelInfo.description}}</span>
                </a-form-item>
                <a-form-item label="星级" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                    <a-input placeholder="请输入星级"
                             v-decorator="['hotelStar']"
                             v-if="modify"
                    />
                    <span v-else>{{hotelInfo.hotelStar}}</span>
                </a-form-item>
                <a-form-item label="电话" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                    <a-input placeholder="请输入电话"
                             v-decorator="['phoneNumber']"
                             v-if="modify"
                    />
                    <span v-else>{{hotelInfo.phoneNum}}</span>
                </a-form-item>
                <a-form-item label="评分" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                    <a-input placeholder="请输入评分"
                             v-decorator="['rate']"
                             v-if="modify"
                    />
                    <span v-else>{{hotelInfo.rate}}</span>
                </a-form-item>
                <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                    <a-button type="primary" @click="saveModify">
                        保存
                    </a-button>
                    <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                        取消
                    </a-button>
                </a-form-item>
                <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                    <a-button type="primary" @click="modifyInfo">
                        修改信息
                    </a-button>
                </a-form-item>
            </a-form>


        </a-modal>

    </div>

</template>

<script>
    import {mapGetters,mapActions, mapMutations} from "vuex";

    export default {
        name: "hotelInfo",
        inject:['reload'],
        data(){
            return{
                modify:false,
            }
        },
        computed:{
            ...mapGetters([
                'hotelInfo',
                'hotelVisible',
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'hotelInfo' });
        },
        async mounted() {
            // await this.getHotelInfo()
        },
        methods: {
            ...mapMutations([
                'set_hotelVisible'
            ]),
            ...mapActions([
                'setHotelInfo',
                'getHotelInfo',
            ]),
            cancel() {
                this.set_hotelVisible(false)
                parent.location.reload()
            },

            saveModify() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            hotelId:this.hotelInfo.id,
                            name:this.form.getFieldValue('name'),
                            address: this.form.getFieldValue('address'),
                            bizRegion: this.form.getFieldValue('bizRegion'),
                            hotelStar: this.form.getFieldValue('hotelStar'),
                            rate:this.form.getFieldValue('rate'),
                            description: this.form.getFieldValue('hotelDescription'),
                            phoneNum:this.form.getFieldValue('phoneNumber'),
                            managerId:this.hotelInfo.managerId,
                        }
                        this.setHotelInfo(data).then(()=>{
                            this.modify = false
                            this.getHotelInfo()
                        })
                    }
                });
            },
            modifyInfo() {
                setTimeout(() => {
                    this.form.setFieldsValue({
                        'name': this.hotelInfo.name,
                        'phoneNumber': this.hotelInfo.phoneNum,
                        'address':this.hotelInfo.address,
                        'bizRegion':this.hotelInfo.bizRegion,
                        'hotelDescription':this.hotelInfo.description,
                        'hotelStar':this.hotelInfo.hotelStar,
                        'rate':this.hotelInfo.rate,
                    })
                }, 0)
                this.modify = true
            },
            cancelModify() {
                this.modify = false
            },


        }
    }

</script>

<style scoped>

</style>