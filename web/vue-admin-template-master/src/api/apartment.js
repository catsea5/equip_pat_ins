import request from '@/utils/request'

export default {
  findAnumber() {
    return request({
      url: `/apartment/anumberlist`,
      method: 'get'
    })
  },
  addApartment(anumber) {
    return request({
      url: `/apartment/add/${anumber}`,
      method: 'get'
    })
  },
  updateApartment(apaetObj) {
    return request({
      url: `/apartment/update`,
      method: 'post',
      data: apaetObj
    })
  },
  getAidAndAnumber(id) {
    return request({
      url: `/apartment/list/${id}`,
      method: 'get'
    })
  }
}
