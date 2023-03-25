// pages/equiplist/equiplist.js
Page({
    /**
     * 页面的初始数据
     */
    data: {
       queryInfo: {
           pid: 0,
           token: '' //用户token信息
       }, //查询信息
       infoList: {}, //后端传来的列表信息
       indexLeft: 0, //表示左侧选择的aid
       eid: 0 // 通过scan扫描出来的设备id，用于查询和上传设备信息
    },
    scan(){
        wx.scanCode({
            success: (res) => {
              console.log("扫码结果");
            //   console.log(res);
              this.setData({
                eid: res.result.substr(4, res.result.length)
              })
              wx.navigateTo({
                url: '/pages/uploadequip/uploadequip?eid='+this.data.eid+'&pid='+this.data.queryInfo.pid,
              })
              
            },
            fail: (res) => {
              console.log(res);
            },
        })
    },
    changeIndexLeft(e){
        this.setData({
            indexLeft:e.currentTarget.dataset.index
        })
    },
    getInfoList(){
        wx.request({
          url: 'http://192.168.200.1:8201/schedule/wxinfolist',
          method: 'POST',
            data: this.data.queryInfo,
            success: (res) => {
                this.setData({infoList:res.data.data})
                console.log(this.data.infoList)
            }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        // console.log(options.pid)
        this.setData({
            'queryInfo.pid' : options.pid
        })
        wx.getStorage({
            key: 'user',
            success: (res) => {
                this.setData({
                    'queryInfo.token' : res.data.token
                })
                this.getInfoList()
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
        this.setData({
            infoList: {}, //后端传来的列表信息
            indexLeft: 0 //表示左侧选择的aid
        })
        this.getInfoList(() => wx.stopPullDownRefresh())
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