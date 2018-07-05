import axios from 'axios'
import querystring from 'querystring'

export const get = (url, data, conf) => axios.get(`${url}?${querystring.stringify(filterNull(data))}`, conf)

export const post = (url, data, conf) => axios.post(url, querystring.stringify(filterNull(data)), conf)

export const put = (url, data, conf) => axios.put(url, querystring.stringify(filterNull(data)), conf)

export const del = (url, data, conf) => axios.delete(`${url}?${querystring.stringify(filterNull(data))}`, conf)

export const postJSON = (url, data, conf) => axios.post(url, data, conf)

/**
 * 替代eval
 * @param {*} fn
 */
export const evil = fn => {
  var Fn = Function
  return new Fn('return ' + fn)()
}

function filterNull (data) {
  if (data == null) {
    return null
  }
  let str = JSON.stringify(data, (k, v) => {
    console.log(k, v)
    if (v === null || v === undefined || v === '') {
      return undefined
    }
    return v
  })
  return JSON.parse(str)
}
