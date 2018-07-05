import VueMarkdown from 'vue-markdown' //直接作为一个组件引入
export default {
    name: 'demo',
    data() {
        return {
            content: '## 这里是要展示的markdown文字，也可以通过props传递'
        }
    },
    components: {
        VueMarkdown // 声明组件
    }
}