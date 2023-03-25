// pages/uploadequip/uploadequip.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        equipInfo: {}, //设备信息
        commit: '',
        uploadInfo:{
            eid: 0, // 设备id
            pid: 0, // 巡检计划id
            token: '', // 用户token
            state: 1, //设备状态，1为正常，2为异常
            commit: ''
        } //上传信息
    },
    //上传数据
    upload(){
        this.setData({
            "uploadInfo.commit":this.data.commit
        })
        wx.request({
          url: 'http://192.168.200.1:8201/result/upload',
          method: "POST",
          data: this.data.uploadInfo,
          success: (res) => {
            wx.showModal({
                cancelColor: 'cancelColor',
                title: '提示',
                content: '上传成功',
              }).then(() => {
                wx.navigateBack(-1)
              })
          }
        })
    },
    //改变设备状态2-》1
    changeStateTweToOne(){
        this.setData({"uploadInfo.state": 1})
 
    },
    //改变设备状态1-》2
    changeStateOneToTwe(){
        this.setData({"uploadInfo.state": 2})
 
    },
    //根据eid获取设备信息
    getEquipInfo(){
        wx.request({
          url: 'http://192.168.200.1:8201/equip/scan',
          method: 'Post',
          data: this.data.uploadInfo,
          success: (res) => {
              if(res.data.code != 200) {
                wx.showModal({
                    cancelColor: 'cancelColor',
                    title: '提示',
                    content: res.data.data,
                  }).then(() => {
                    wx.navigateBack(-1)
                  })
              } 
              this.setData({equipInfo:res.data.data})
          }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.setData({"uploadInfo.eid":options.eid})
        this.setData({"uploadInfo.pid":options.pid})
        wx.getStorage({
            key: 'user',
            success: (res) => {
                this.setData({
                    'uploadInfo.token' : res.data.token
                })
                this.getEquipInfo()
            }
        })
            
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})