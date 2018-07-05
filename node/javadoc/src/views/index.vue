<template>
    <div>
        <Layout>
            <Header class="header">
                <Row>
                    <Col span="18"><h1>{{project.title}}</h1></Col>
                    <Col span="6" >
                        <div style="float:right;">
                            <Select style="width:165px" placeholder="选择项目" @on-change="projectChange" v-model="projectIndex">
                                <Option v-for="(item, index) in projectList" :value="index" :key="item.name">{{ item.title }}</Option>
                            </Select>
                            <Select style="width:165px" placeholder="选择环境" @on-change="envIndexChange" v-model="envIndex">
                                <Option v-for="(item, index) in envAry" :value="index" :key="index">{{ item.title }}</Option>
                            </Select>
                            <Button type="primary" size="small" @click="envshow=true">
                                <Icon type="ios-cog-outline" size="22"></Icon>
                            </Button>
                        </div>
                    </Col>
                </Row>
            </Header>
            <Layout>
                <Sider :multiple="true" :width="300" hide-trigger class="sider" :style="{height: windowHeight - 2+'px', width: 300 + 'px'}">
                    <Tree :data="treeData" :render="renderContent"></Tree>
                </Sider>
                <Content style="padding: 10px;" v-if="node != null">
                    <Row>
                        <Col span="24">
                            <h1>{{node.notes}}</h1>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="24">
                            <p>描述：{{node.notes}}</p>
                            <p>作者：{{node.author}}</p>
                            <p>时间：{{node.date}}</p>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="20">
                            <Tag type="border" v-if="node.method === 'GET'" color="green">GET</Tag>
                            <Tag type="border" v-if="node.method === 'POST'" color="yellow">POST</Tag>
                            <Tag type="border" v-if="node.method === 'PUT'" color="blue">PUT</Tag>
                            <Tag type="border" v-if="node.method === 'DELETE'" color="red">DELETE</Tag>
                            http://{{envMap.domain}}{{node.path}}
                            <Button class="btn" size="small" type="primary" :aria-label='"http://"+envMap.domain+node.path'>复制</Button>
                        </Col>
                        <Col span="4">
                            <Button style="float:right;margin-right:5px;" type="primary" icon="android-send" @click="send">发送</Button>
                        </Col>
                    </Row>

                    <Row>
                        <Col span="24" >
                            <Tabs :animated="true" value="name1" v-model="activate">
                                <TabPane label="Headers" name="Headers">
                                    <table class="myTable">
                                        <thead>
                                            <tr>
                                                <th>必传</th>
                                                <th>参数名称</th>
                                                <th>参数值</th>
                                                <th>备注</th>
                                            </tr>
                                        </thead>
                                        <tr v-if="node.headers != null" v-for="item in node.headers">
                                            <td class="right"><Switch v-model="item.required" size="small"/></td>
                                            <td><Input v-model="item.key" /></td>
                                            <td><Input v-model="item.value"></Input></td>
                                            <td><Input v-model="item.notes" /></td>
                                        </tr>
                                    </table>
                                </TabPane>
                                <TabPane label="Body" name="Body">
                                    <table class="myTable" v-if="node.mode === 'FORM'">
                                        <thead>
                                            <tr>
                                                <th>必传</th>
                                                <th>参数名称</th>
                                                <th>参数值</th>
                                                <th>备注</th>
                                            </tr>
                                        </thead>
                                        <tr v-if="node.params != null" v-for="item in node.params">
                                            <td class="right"><Switch v-model="item.required" size="small"/></td>
                                            <td width="32%"><Input v-model="item.key" /></td>
                                            <td width="32%">
                                                <Select v-model="item.value" v-if="item.type == 'Enum'">
                                                    <Option v-for="itemA in item.enumItem" :value="itemA.value" :key="itemA.value">{{ itemA.notes }}</Option>
                                                </Select>
                                                <Input v-else v-model="item.value"></Input>
                                            </td>
                                            <td width="32%"><Input v-model="item.notes" /></td>
                                        </tr>
                                    </table>

                                    <Row v-if="node.mode === 'JSON'">
                                        <Col span="12" style="padding-left:10px;padding-right:10px;">
                                            <Input v-model="node.bodyRaw"  type="textarea" :autosize="{minRows: 20}"></Input>
                                        </Col>
                                        <Col span="12" style="padding-left:10px;padding-right:10px;">
                                            <p>格式说明：</p>
                                            <vue-json-pretty v-if="node.results" :data="node.params"></vue-json-pretty>
                                        </Col>
                                    </Row>
                                </TabPane>
                                <TabPane label="Response" name="Response">
                                    <vue-json-pretty :data="responseData"></vue-json-pretty>
                                </TabPane>
                                <TabPane label="返回描述" name="返回描述">
                                    <Row>
                                        <Col span="12">
                                            <p v-if="node.resultType">类型：{{node.resultType}}</p>
                                            <p v-if="node.resultNotes">描述：{{node.resultNotes}}</p>
                                            <!-- <vue-json-pretty v-if="node.results" :data="node.results"></vue-json-pretty> -->
                                            <table class="gridtable" >
                                                <thead>
                                                    <tr>
                                                        <th>字段名</th>
                                                        <th>类型</th>
                                                        <th>备注</th>
                                                    </tr>
                                                </thead>
                                                <template v-if="node.results != null" v-for="item in node.results">
                                                    <tr>
                                                        <td>{{item.key}}</td>
                                                        <td>
                                                            {{item.type}}
                                                            <Button v-if="item.type == 'Enum'" type="primary" size="small" @click="showEnum(item)">查看</Button>
                                                        </td>
                                                        <td>{{item.notes}}</td>
                                                    </tr>
                                                    <tr v-if="item.type == 'Object'" v-for="itemA in item.value">
                                                        <td>{{itemA.key}}</td>
                                                        <td>
                                                            {{itemA.type}}
                                                            <Button v-if="itemA.type == 'Enum'" type="primary" size="small" @click="showEnum(itemA)">查看</Button>
                                                        </td>
                                                        <td>{{itemA.notes}}</td>
                                                    </tr>
                                                    <tr v-if="item.type == 'Array'" v-for="itemA in item.value">
                                                        <td>{{itemA.key}}</td>
                                                        <td>
                                                            {{itemA.type}}
                                                            <Button v-if="itemA.type == 'Enum'" type="primary" size="small" @click="showEnum(itemA)">查看</Button>
                                                        </td>
                                                        <td>{{itemA.notes}}</td>
                                                    </tr>
                                                    
                                                </template>
                                            </table>
                                        </Col>
                                        <Col  span="12">
                                            <p v-if="node.resultType">例子：</p>
                                            <vue-json-pretty v-if="node.results" :data="resultData"></vue-json-pretty>    
                                        </Col>
                                    </Row>
                                </TabPane>
                                <TabPane label="test" name="test">
                                    <Alert>
                                        <p>说明：ENV为当前环境、RES为当前请求返回结果。</p>
                                        <p>第一行:增加当前环境变量authorization = RES.data.tokenHead + ' '+RES.data.token</p>
                                        <p>第二行:这里可以打印也可以alert</p>
                                        <p>第三行:flush()会保存当前环境变量ENV，否则刷新会丢失对当前环境变量的修改</p>
                                        <pre>
                                            ENV.authorization = RES.data.tokenHead + ' '+RES.data.token
                                            console.log("look this->",JSON.stringify(ENV))
                                            flush()
                                        </pre>
                                    </Alert>
                                    <Input v-model="testData"  type="textarea" @on-change="testChange" :autosize="{minRows: 20}"></Input>
                                </TabPane>
                            </Tabs>
                        </Col>
                    </Row>
                </Content>
            </Layout>
        </Layout>
        <Modal v-model="envshow">
            <p slot="header" style="color:#2d8cf0;text-align:center">
                <Icon type="information-circled"></Icon>
                <span>环境变量管理</span>
            </p>
            <div style="text-align:center">
                <div v-if="envAdd">
                    <Row>
                        <Col span="24">
                            <Input size="small" placeholder="环境名称" v-model="envObj.title" @on-blur="envBlurSave"></Input>
                        </Col>
                    </Row>
                    <br/>
                    <Row v-for="(item, index) in envObj.list" :key="index" style="margin-top:5px;">
                        <Col span="11">
                            <Input size="small" placeholder="KEY" v-model="item.key" @on-blur="envBlurSave"></Input>
                        </Col>
                        <Col span="11">
                            <Input size="small" placeholder="VALUE" v-model="item.value" @on-blur="envBlurSave"></Input>
                        </Col>
                        <Col span="2">
                            <Button type="error" size="small" @click="envItemDelete(index)"><Icon type="trash-a"></Icon></Button>
                        </Col>
                    </Row>
                    <br/>
                    <Row style="margin-top:5px;">
                        <Col span="12">
                            <Input size="small" placeholder="KEY" v-model="envTemp.key"></Input>
                        </Col>
                        <Col span="12">
                            <Input size="small" placeholder="VALUE" v-model="envTemp.value" @on-blur="envBlur"></Input>
                        </Col>
                    </Row>
                </div>
                <div v-else>
                    <Row class-name="myRow" v-for="(item, index) in envAry" :key="item.title">
                        <Col span="12">
                            <span class="left"><h4>{{item.title}}</h4></span>
                        </Col>
                        <Col span="12">
                            <Button class="right" type="info" size="small" @click="envDelete(index)">删除</Button>
                            <Button class="right" type="info" size="small" @click="envAdd=true; envObj=item">编辑</Button>
                        </Col>
                    </Row>
                </div>
            </div>
            <div slot="footer">
                <Button v-if="envAdd" type="primary" size="large" @click="clear">返回</Button>
                <Button v-else type="primary" size="large" @click="envAdd=true">添加</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
// import treeData from '../data.json'
import { get, post, put, del, postJSON, evil } from '../api/api'
import {addEnv, getEnvAry, delEnv, save, getIndexAry, updateIndex, updateNode, getNode, getTest} from '../bus'
import VueJsonPretty from 'vue-json-pretty'
import ClipboardJS from 'clipboard'

let clip = new ClipboardJS('.btn', {
  text: function (trigger) {
    return trigger.getAttribute('aria-label')
  }
})
console.log(clip)
export default {
  data () {
    return {
      envshow: false,
      envAdd: false,
      project: {title: 'javadoc'},
      windowHeight: 0,
      treeData: [],
      node: null,
      nodeId: {},
      responseData: {},
      testData: '',
      activate: 'Body',
      projectList: [{name: 'a', title: 'b'}],
      envAry: [],
      envObj: {
        title: '',
        list: []
      },
      envTemp: {},
      projectIndex: null,
      envIndex: null,
      env: {},
      testObj: {}
    }
  },
  mounted () {
    this.windowHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientht
  },
  created () {
    let indexAry = getIndexAry()
    this.envAry = getEnvAry()
    get(`../static/project.json`).then(res => {
      this.projectList = res.data
      this.projectIndex = indexAry[0]
      this.envIndex = indexAry[1]
      if (this.projectIndex != null) {
        this.project = this.projectList[this.projectIndex]
        this.getTreeData()
      }
      this.env = this.envAry[this.envIndex]
      this.testObj = getTest()
      this.node = getNode()
      this.set(this.node.params)
      this.set(this.node.headers)
      this.setTest()
    })
  },
  methods: {
    showEnum (item) {
      let content = ''
      for (let i = 0; i < item.enumItem.length; i++) {
        let obj = item.enumItem[i]
        content += obj.value + ':' + obj.notes + '、'
      }
      this.$Modal.info({
        title: `${item.key}-枚举类型`,
        content: content
      })
    },
    testChange () {
      debugger
      this.testObj[this.node.id] = this.testData
      save()
    },
    setTest () {
      let testD = this.testObj[this.node.id]
      if (testD != null) {
        this.testData = testD
      }
    },
    clear () {
      this.envAdd = false
      this.envObj = {title: '', list: []}
      this.envTemp = {}
    },
    envItemDelete (index) {
      this.envObj.list.splice(index, 1)
      save()
    },
    envDelete (index) {
      if (window.confirm(`您确定要删除【${this.envAry[index].title}】环境吗？`)) {
        delEnv(index)
      }
    },
    envBlur () {
      if (this.envTemp.key == null || this.envTemp.value == null) {
        return
      }
      this.envObj.list.push(this.envTemp)
      this.envTemp = {}
      addEnv(this.envObj)
    },
    envBlurSave () {
      save()
    },
    envIndexChange (index) {
      this.env = this.envAry[index]
      updateIndex(1, index)
    },
    projectChange (index) {
      this.project = this.projectList[index]
      updateIndex(0, index)
      this.getTreeData()
    },
    getTreeData () {
      get(`../static/${this.project.name}.json`).then(res => {
        this.treeData = res.data
      }).catch(e => {
        this.treeData = []
      })
    },
    set (ary) {
      let list = this.env.list
      if (list == null || list.length === 0 || ary == null || ary.length === 0) {
        return
      }
      for (let i = 0; i < ary.length; i++) {
        let itemA = ary[i]
        for (let j = 0; j < list.length; j++) {
          let itemB = list[j]
          if (itemA.key === itemB.key && itemA.value == null) {
            itemA.value = itemB.value
          }
        }
      }
    },
    send () {
      let headers = {}
      let params = {}
      if (this.node.headers != null) {
        for (let i = 0; i < this.node.headers.length; i++) {
          let item = this.node.headers[i]
          headers[item.key] = item.value
        }
      }

      if (this.node.params != null) {
        for (let i = 0; i < this.node.params.length; i++) {
          let item = this.node.params[i]
          params[item.key] = item.value
        }
      }

      let that = this
      function exec (res) {
        let {data, status, headers} = res
        that.responseData = {data, status, headers}
        window.ENV = {}
        window.RES = res
        window.flush = function () {
          for (var k in window.ENV) {
            let flag = true
            for (let i = 0; i < that.env.list.length; i++) {
              if (that.env.list[i].key === k) {
                that.env.list[i].value = window.ENV[k]
                flag = false
                break
              }
            }
            if (flag) {
              that.env.list.push({key: k, value: window.ENV[k]})
            }
          }
          save()
        }
        let str = that.testData.split('\n')
        if (str != null && str.length > 0) {
          for (let i = 0; i < str.length; i++) {
            evil(str[i])
          }
        }
      }

      if (this.node.mode === 'JSON') {
        params = this.node.bodyRaw
        postJSON(`http://${this.envMap.domain}${this.node.path}`, params, { headers: headers }).then(exec).catch(err => { exec(err.response) })
      } else if (this.node.method === 'GET') {
        get(`http://${this.envMap.domain}${this.node.path}`, params, { headers: headers }).then(exec).catch(err => { exec(err.response) })
      } else if (this.node.method === 'POST') {
        post(`http://${this.envMap.domain}${this.node.path}`, params, { headers: headers }).then(exec).catch(err => { exec(err.response) })
      } else if (this.node.method === 'PUT') {
        put(`http://${this.envMap.domain}${this.node.path}`, params, { headers: headers }).then(exec).catch(err => { exec(err.response) })
      } else if (this.node.method === 'DELETE') {
        del(`http://${this.envMap.domain}${this.node.path}`, params, { headers: headers }).then(exec).catch(err => { exec(err.response) })
      }
      this.activate = 'Response'
    },
    renderContent (h, { root, node, data }) {
      let background = '#fff'
      let colorMap = {GET: '#19be6b', POST: '#ff9900', PUT: '#2d8cf0', DELETE: '#ed3f14', null: '#f8f8f9'}
      let icon = null
      if (node.children != null) {
        icon = h('Icon', {
          props: {
            type: 'folder',
            color: '#ff9900',
            size: 18
          },
          style: {
            marginRight: '8px'
          }
        })
      }

      if (this.node.id === data.id || this.nodeId === data.id) {
        background = '#d5e8fc'
      }
      let that = this
      return h('span', {
        style: {
          display: 'inline-block',
          width: '100%'
        },
        on: {
          mouseenter () {
            that.nodeId = data.id
          },
          mouseout () {
            that.nodeId = null
          },
          click () {
            data.bodyRaw = JSON.stringify(that.jsonToJson(data.params), null, 4)
            that.node = data
            that.activate = 'Body'
            that.responseData = null
            updateNode(data)
            that.set(data.params)
            that.set(data.headers)
            that.setTest()
            // console.log(JSON.stringify(data))
          }
        }
      }, [
        h('span', [
          icon,
          h('span', {
            style: {
              borderRadius: '4px',
              padding: '4px',
              color: colorMap[data.method],
              cursor: 'pointer',
              background: background
            }
          }, data.method),
          h('span', {
            style: {
              borderRadius: '4px',
              padding: '4px',
              color: '#495060',
              cursor: 'pointer',
              background: background
            }
          }, data.title)
        ])
      ])
    },
    jsonToJson (data) {
      if (data === undefined) {
        return undefined
      }
      let json = {}
      for (var i = 0; i < data.length; i++) {
        let item = data[i]
        let key = item.key
        let value = item.type
        if (item.type === 'Array') {
          value = [this.jsonToJson(item.value)]
        } else if (item.type === 'Object') {
          value = this.jsonToJson(item.value)
        }
        json[key] = value
      }
      return json
    }
  },
  computed: {
    resultData () {
      return this.jsonToJson(this.node.results)
    },
    envMap () {
      let env = this.env
      let map = {}
      if (env != null) {
        for (let i in env.list) {
          let item = env.list[i]
          map[item.key] = item.value
        }
      }
      return map
    }
  },
  components: {
    VueJsonPretty
  }
}
</script>
<style scoped>
.header{
    background: #2d8cf0;
    color: #fff;
}
.sider {
    border-right-style: solid;
    background:#fff;
    border-color:#2d8cf0;
    height: 100%;
}
.myTable {
    width : 100%;
}

.myTable td, th{
    padding: 2px;
}

.right {
    float: right;
}

.left {
    float: left;
}

.myRow {
    border: 1px #AAB5B0;
    border-bottom-style: solid;
    padding: 10px;
}

 /* gridtable */
table.gridtable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
    width: 100%;
    margin-reigh: 5px;
}
table.gridtable th {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #dedede;
}
table.gridtable td {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #ffffff;
}
/* /gridtable */
</style>