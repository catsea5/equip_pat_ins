// pages/home/home.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        getPlanInfo: {
            token: '1', //用来保存用户的基本信息，包括用户ID，用户名，用户邮箱
        },
        planList: {},
        isEmpty: false //判断是否为空值
    },
    gotoPatrol(e){
        console.log(e.currentTarget.dataset.id)
        wx.navigateTo({
          url: '/pages/equiplist/equiplist?pid='+e.currentTarget.dataset.id,
        })
    },
    getPlanList(){
        wx.request({
            url: 'http://192.168.200.1:8201/plan/userplan',
            method: 'POST',
            data: this.data.getPlanInfo,
            success: (res) => {
                //如果没有数据则设置为true
                if(res.data.data.length==0){
                    this.setData({isEmpty:true})
                } 
                this.setData({planList:res.data.data})
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
                    'getPlanInfo.token' : res.data.token
                })
                this.getPlanList()
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
            planList:{},
            isEmpty: false
        })
        this.getPlanList(() => wx.stopPullDownRefresh())
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