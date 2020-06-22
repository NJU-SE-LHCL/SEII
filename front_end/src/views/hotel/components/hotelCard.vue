<template>

  <a-card hoverable class="hotelCard ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
    <img
      alt="example"
      src="@/assets/cover.jpeg"
      slot="cover"
      referrerPolicy="no-referrer"
    />

    <a-tooltip :title="hotel.title" placement="top">
      <a-card-meta :title="hotel.name">
      <template slot="description">
        <a-rate style="font-size: 15px" :value="hotel.rate" disabled allowHalf/> {{hotel.rate}}分
      </template>

    </a-card-meta>
    </a-tooltip>
    <a-tag style="margin-left: 8rem;margin-top: 1rem" :color="this.color">{{getTag(hotel.id)}}</a-tag>
  </a-card>
</template>
<script>
  import {mapActions,mapMutations,mapGetters}from 'vuex';
export default {
  name:'',
  props: {
    hotel: {}
  },
  data() {
    return{
      color:'',
    }
  },
  computed:{
    ...mapGetters([
            'userId',
            'tag',
    ]),
  },
  methods:{
    ...mapMutations([
            'set_currentHotelId',
            'set_userId',
    ]),
    ...mapActions([
            'getTagByHotelId',
    ]

  ),
    getTag(hotelId){
      this.set_userId(Number(this.userId))
      this.set_currentHotelId(hotelId)
      this.getTagByHotelId()
      switch (this.tag) {
        case "完成订单":
          this.color="blue"
          break
        case "撤销订单":
          this.color="red"
              break
      }
      return this.tag
    },

  }
}
</script>
<style scoped lang="less">
    .hotelCard {
        margin: 10px 10px;
        min-width: 180px;
        max-height: 350px;
        img {
          height: 250px;
        }
    }
</style>

<style lang="less">
.hotelCard{
  .ant-card-body{
    padding: 12px
  }
}

.ant-card-hoverable:hover {
  box-shadow: 0 2px 8px rgba(0,0,0,0.4)
}

</style>