// pages/myhistory/myhistory.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        getHistoryInfo: {
            token: ''
        },
        historyList: {},
        show: -1
    },
    hideInfo(){
        this.setData({show:-1})
    },
    showInfo(e){
        const index = e.currentTarget.dataset['index']
        this.setData({show:index})
    },
    getHistoryList(){ //先去缓存中获取数据，如果缓存没有就调用接口获取
        wx.request({
            url: 'http://192.168.200.1:8201/result/myhistory',
            method: "POST",
            data: this.data.getHistoryInfo,
            success: (res) => {
                this.setData({
                    historyList:res.data.data
                })
                wx.setStorage({
                    key: 'history',
                    data: this.data.historyList
                })
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
                    'getHistoryInfo.token' : res.data.token
                })
                this.getHistoryList()
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
            historyList:{},
        })
        this.getHistoryList(() => wx.stopPullDownRefresh())
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