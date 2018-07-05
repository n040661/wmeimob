// var envAry = []
// var indexAry = [null, null]
// var nodeData = {}
var cache = {
  envAry: [],
  indexAry: [null, null],
  nodeData: {},
  testObj: {}
}
const KEY_CACHE_DATA = 'KEY_CACHE_DATA'

/**
 * 添加环境配置
 */
export const addEnv = item => {
  if (cache.envAry.indexOf(item) === -1) {
    cache.envAry.push(item)
  }
  save()
}

/**
 * 获取环境配置
 */
export const getEnvAry = () => { return cache.envAry }

/**
 * 根据下标删除环境配置
 */
export const delEnv = index => {
  cache.envAry.splice(index, 1)
  save()
}

export const getIndexAry = () => { return cache.indexAry }

export const updateIndex = (i, j) => {
  cache.indexAry[i] = j
  save()
}

export const updateNode = node => {
  cache.nodeData = node
  save()
}

export const getNode = () => {
  return cache.nodeData
}

/**
 * 初始化加载环境配置
 */
export const initEnv = () => {
  let temp = getObj(KEY_CACHE_DATA)
  if (temp != null) {
    cache = temp
  }
}

export const getTest = () => {
  if (cache.testObj == null) {
    cache.testObj = {}
  }
  return cache.testObj
}

export const save = () => {
  window.localStorage.setItem(KEY_CACHE_DATA, JSON.stringify(cache))
}

function getObj (key) {
  let str = window.localStorage.getItem(key)
  if (str != null) {
    return JSON.parse(str)
  }
}

initEnv()
