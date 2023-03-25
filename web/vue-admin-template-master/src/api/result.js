import request from '@/utils/request'

export default {
  getPageList(serchObj) {
    return request({
      url: `/result/list`,
      method: 'Post',
      data: serchObj
    })
  },
  download(){
    return request({
      url: `/result/exportData`,
      method: 'Get'
    })
  }
}
