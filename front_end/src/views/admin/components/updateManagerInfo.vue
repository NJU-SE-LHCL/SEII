<template>
    <a-modal
             :visible="updateUserModalVisible"
             title="修改个人信息"
             ok-text="确认"
             cancel-text="取消"
             @ok="handleSubmit"
             @cancel="cancel"
             :maskStyle="{'opacity':'0.2','animation':'none'}"
    >
        <a-Form :form="form" :label-col="{ span: 5}" :wrapper-col="{ span: 16 }" >
            <a-form-item label="邮箱">
                <a-input
                        v-decorator="[
                                    'email',
                                    {initialValue:$store.state.admin.userInfo.email, rules: [{ required: true,
                                    message: '请输入邮箱' }] },]"
                        />
            </a-form-item>

            <a-form-item label="用户名">
                <a-input
                        v-decorator="[
                                    'username',
                                    {initialValue:$store.state.admin.userInfo.userName,rules: [{ required: true, message: '请输入用户名' }] },]"
                        />
            </a-form-item>


            <a-form-item label="密码">
                <a-input
                        v-decorator="[
                                    'password',
                                    {initialValue:$store.state.admin.userInfo.password,rules: [{ required: true,
                                    message:
                                    '请输入用户密码' }] },]"
                        />
            </a-form-item>

            <a-form-item label="电话号码">
                <a-input
                        v-decorator="[
                                    'phoneNumber',
                                    {initialValue:$store.state.admin.userInfo.phoneNumber,rules: [{ required: true,
                                    message:
                                    '请输入电话号码' }] },]"
                        />
            </a-form-item>

            <a-form-item label="信用值">
                <a-input
                        v-decorator="[
                                    'credit',
                                    {initialValue:$store.state.admin.userInfo.credit, rules: [{ required: true,
                                     message: 'Please input your credit' }] },]"
                        />
            </a-form-item>

            <a-form-item label="用户权限">
                <a-input
                        v-decorator="[
                                    'userType',
                                    {initialValue:$store.state.admin.userInfo.userType, rules: [{ required: true,
                                    message: 'Please input your usertype' }] },]"
                        />
            </a-form-item>
        </a-Form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import { message } from 'ant-design-vue'
    export default {
        name: "updateManagerInfo",
        data() {
            return{}
        },

        computed: {
            ...mapGetters([
                'updateUserModalVisible',
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'changeInfo' });
        },
        mounted() {

        },

        methods: {
            ...mapMutations([
                'set_updateUserModalVisible',
                'set_UserInfo',
            ]),
            ...mapActions([
                'changeInfo',
            ]),
            cancel() {
                this.set_updateUserModalVisible(false)
            },
            handleSubmit() {
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            email: this.form.getFieldValue('email'),
                            userName: this.form.getFieldValue('username'),
                            password: this.form.getFieldValue('password'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                            credit:this.form.getFieldValue('credit'),
                            userType:this.form.getFieldValue('userType'),
                        }
                        this.set_UserInfo(data)
                        this.changeInfo().then(()=>{
                            this.set_updateUserModalVisible(false)
                        })

                    }
                });
            },
        }
    }
</script>

<style scoped>

</style>