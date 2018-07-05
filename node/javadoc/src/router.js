const routers = [{
    path: '/doclet',
    meta: {
        title: ''
    },
    component: (resolve) => require(['./views/index.vue'], resolve)
}];
export default routers;