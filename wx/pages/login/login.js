// pages/login/login.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        email: '',
        password: '',
        agreeOn: false,
        eyeClose: true,
        loginObj: {
            email: '',
            password: ''
        },
        userInfo: {}
    },
    eyeChange(){
        if(this.data.eyeClose == true) {
            this.setData({eyeClose:false})
        }else {
            this.setData({eyeClose:true})
        }
    },
    agreeOn(){
        console.log(this.data.agreeOn)
        if(this.data.agreeOn == false) {
            this.setData({agreeOn:true})
        }else {
            this.setData({agreeOn:false})
        }
    },
    login(){
        var isEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
        var isPassword = /^[\0-9a-zA-Z_]+$/
        if(isEmail.test(this.data.email)){
            this.data.loginObj.email = this.data.email
        }else {
            return wx.showModal({
                cancelColor: 'cancelColor',
                title: '提示',
                content: '输入邮箱格式错误'
            })
        }
        if(isPassword.test(this.data.password)){
            this.data.loginObj.password = this.data.password
        }else{
            return wx.showModal({
                cancelColor: 'cancelColor',
                title: '提示',
                content: '密码只能包含大小写字母、数字、_、+、%'
            })
        }
        if(this.data.agreeOn == false){
            return wx.showModal({
                cancelColor: 'cancelColor',
                title: '提示',
                content: '请同意使用协议'
            })
        }
        // 请求数据
        wx.request({
          url: 'http://192.168.200.1:8201/login',
          method: 'POST',
          data: this.data.loginObj,
          success:(res) =>{
            this.data.userInfo = res.data
            console.log(this.data.userInfo)
            if(this.data.userInfo.code != 200){
                wx.showModal({
                  cancelColor: 'cancelColor',
                  title: '提示',
                  content: this.data.userInfo.data,
                })
            } else { //登录成功则添加用户缓存数据
                wx.setStorage({
                    key: 'user',
                    data: this.data.userInfo.data
                })
                wx.switchTab({
                  url: '../home/home',
                })
            }
          }
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

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