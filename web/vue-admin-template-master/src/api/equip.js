import request from '@/utils/request'

export default {
  getPageList(searchObj) {
    return request({
      url: `/equip/list`,
      method: 'post',
      data: searchObj
    })
  },
  getEquipById(id) {
    return request({
      url: `/equip/query/${id}`,
      method: 'get'
    })
  },
  lock(id, state) {
    return request({
      url: `/equip/change/${id}/${state}`,
      method: 'get'
    })
  },
  findType() {
    return request({
      url: `/equip/typelist`,
      method: 'get'
    })
  },
  add(equipInfor) {
    return request({
      url: `/equip/add`,
      method: 'post',
      data: equipInfor
    })
  },
  update(equipUpdateInfor) {
    return request({
      url: `/equip/update`,
      method: 'post',
      data: equipUpdateInfor
    })
  },
  addRepairInfo(uid,eid,commit){
    return request({
      url: `/repair/${uid}/${eid}`,
      method: 'get',
      params: {
        commit
      }
    })
  }
}
