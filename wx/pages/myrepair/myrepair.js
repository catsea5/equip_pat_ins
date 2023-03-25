// pages/myrepair/myrepair.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        token: null,
        repairList: {},
        repairPid: 11,
        eid: null
    },
    scan(){
        wx.scanCode({
            success: (res) => {
              this.setData({
                eid: res.result.substr(4, res.result.length)
              })
              wx.navigateTo({
                url: '/pages/uploadequip/uploadequip?eid='+this.data.eid+'&pid='+this.data.repairPid,
              })
              
            },
            fail: (res) => {
              console.log(res);
            },
        })
    },
    getRepairList(){
        wx.request({
          url: 'http://192.168.200.1:8201/myrepair/'+this.data.token,
          method: "GET",
          success: (res) => {
              console.log(res)
              this.setData({repairList:res.data.data})
          }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        wx.getStorage({
            key: 'user',
            success: (res) => {
                this.setData({
                    token : res.data.token
                })
                this.getRepairList()
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
            repairList: {}, 
            eid: null 
        })
        this.getRepairList(() => wx.stopPullDownRefresh())
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