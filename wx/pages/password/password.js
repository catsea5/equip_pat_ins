// pages/password/password.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        second: 60,
        isSend: false,
        email: '',
        password: '',
        code: '',
        repasswordObj: {
            email: '',
            password: '',
            code: ''
        }
    },
    repassword() {
        // 检查输入数据是否正确
        if(this.data.email == '') {
            return wx.showModal({
                cancelColor: 'cancelColor',
                title: '提示',
                content: '请输入注册邮箱'
            })
        } else if(this.data.password == '') {
            return wx.showModal({
                cancelColor: 'cancelColor',
                title: '提示',
                content: '请输入密码'
            })
        } else if(this.data.code == '') {
            return wx.showModal({
                cancelColor: 'cancelColor',
                title: '提示',
                content: '请输入验证码'
            })
        } else {
            // 验证输入的邮箱和密码格式是否正确
            var isEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
            var isPassword = /^[\0-9a-zA-Z_]+$/
            if(isEmail.test(this.data.email)){
                this.data.repasswordObj.email = this.data.email
            }else {
                return wx.showModal({
                    cancelColor: 'cancelColor',
                    title: '提示',
                    content: '输入邮箱格式错误'
                })
            }
            if(isPassword.test(this.data.password)){
                this.data.repasswordObj.password = this.data.password
            }else {
                return wx.showModal({
                    cancelColor: 'cancelColor',
                    title: '提示',
                    content: '密码只能包含大小写字母、数字、_、+、%'
                })
            }
            this.data.repasswordObj.code = this.data.code
            wx.request({
              url: 'http://192.168.200.1:8201/change',
              method: 'POST',
              data: this.data.repasswordObj,
              success: (res) => {
                  if(res.data.code != 200) {
                    wx.showModal({
                        cancelColor: 'cancelColor',
                        title: '提示',
                        content: res.data.data,
                      })
                  } else {
                    wx.showModal({
                        cancelColor: 'cancelColor',
                        title: '提示',
                        content: '密码修改成功，跳转至登录页',
                      }).then(() => {
                        wx.redirectTo({
                            url: '../login/login',
                          }) 
                      })
                  }
              }
            })
        }
    },
    sendCode() {
        //发送邮箱验证码
        var isEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/
        if(this.data.email == ''||!isEmail.test(this.data.email)) {
            return wx.showModal({
                cancelColor: 'cancelColor',
                title: '提示',
                content: '请输入正确的邮箱后再获取验证码'
            })
        } else {
            this.setData({isSend:true})
            wx.request({
              url: 'http://192.168.200.1:8201/sendEmail/'+this.data.email,
              method: 'GET'
            })
            this.timer()
        }      
    },
    timer: function () {
        let promise = new Promise((resolve, reject) => {
          let setTimer = setInterval(
            () => {
              this.setData({
                second: this.data.second - 1
              })
              if (this.data.second <= 0) {
                this.setData({
                  second: 60,
                  isSend: false
                })
                resolve(setTimer)
              }
            }
            , 1000)
        })
        promise.then((setTimer) => {
          clearInterval(setTimer)
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