<template>
    <a-modal
            :visible="addUserModalVisible"
            title="添加用户"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
            :maskStyle="{'opacity':'0.2','animation':'none'}"
    >
        <a-Form :form="form">
            <a-form-item v-bind="formItemLayout" label="用户名">
                <a-input
                        placeholder="请填写用户名"
                        v-decorator="[
                        'userName',
                        { rules: [{required: true, message: '请输入用户名', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="密码">
                <a-input
                        placeholder="请填写用户密码"
                        v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码', }] }
                    ]"
                />
            </a-form-item >
            <a-form-item v-bind="formItemLayout" label="邮箱">
                <a-input
                        placeholder="请填写用户邮箱"
                        v-decorator="[
                        'email',
                        { rules: [{required: true, message: '请输入邮箱', }] }
                    ]"
                />
            </a-form-item >
            <a-form-item v-bind="formItemLayout" label="电话">
                <a-input
                        placeholder="请填写用户电话"
                        v-decorator="[
                        'phoneNumber',
                        { rules: [{required: true, message: '请输入电话', }] }
                    ]"
                />
            </a-form-item >

            <a-form-item v-bind="formItemLayout" label="信用分">
                <a-input
                        placeholder="请填写用户信用分"
                        v-decorator="[
                        'credit',
                        { rules: [{required: false}] }
                    ]"
                />
            </a-form-item >
        </a-Form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'addUserModal',
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
            }
        },
        computed: {
            ...mapGetters([
                'addUserModalVisible',
                'managerList',
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'addManagerModal' });/////////////////////
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addUserModalVisible',
                'set_addUserParams',
            ]),
            ...mapActions([
                'getManagerList',
                'addUser',
            ]),
            cancel() {
                this.set_addUserModalVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            email: this.form.getFieldValue('email'),
                            password: this.form.getFieldValue('password'),
                            userName:this.form.getFieldValue('userName'),
                            phoneNumber:this.form.getFieldValue('phoneNumber'),
                            credit:this.form.getFieldValue('credit')
                        }
                        this.set_addUserParams(data)
                        this.addUser().then(()=>{
                            this.form.resetFields()
                        })
                    }
                });
            },
        }
    }
</script>