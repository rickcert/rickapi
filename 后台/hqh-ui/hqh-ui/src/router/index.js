// 导入用来创建路由和确定路由模式的两个方法
import {
    createRouter,
    createWebHistory
} from 'vue-router'
import store from '@/store'
import storage from '@/util/storage'
/**
 * 定义路由信息
 * 
 */
const routes = [{
        name: 'login',
        path: '/login',
        component: () =>
            import ('@/components/login'),

    }, {
        name: 'main',
        path: '/main',
        component: () =>
            import ('@/components/main'),
        children: [{
                name: 'anime',
                path: '/anime',
                component: () =>
                    import ('@/components/anime'),
            }, {
                name: 'wallpaper',
                path: '/wallpaper',
                component: () =>
                    import ('@/components/wallpaper'),
            },
            {
                name: 'pixiv',
                path: '/pixiv',
                component: () =>
                    import ('@/components/pixiv'),
            },
            {
                name: 'oneword',
                path: '/oneword',
                component: () =>
                    import ('@/components/oneword/index.vue'),
            }, {
                name: 'dog',
                path: '/dog',
                component: () =>
                    import ('@/components/dog/dog.vue'),
            },
            {
                name: 'systemstatus',
                path: '/systemstatus',
                component: () =>
                    import ('@/components/systemstatus'),
            },

            {
                name: 'user',
                path: '/user',
                component: () =>
                    import ('@/components/user'),
            },
            {
                name: 'inter',
                path: '/inter',
                component: () =>
                    import ('@/components/inter'),
            },
            {
                name: 'systemlog',
                path: '/systemlog',
                component: () =>
                    import ('@/components/systemlog'),
            },
            {
                name: 'loginlog',
                path: '/loginlog',
                component: () =>
                    import ('@/components/loginlog'),
            },

        ]

    }]
    // 创建路由实例并传递 `routes` 配置
    // 我们在这里使用 html5 的路由模式，url中不带有#，部署项目的时候需要注意。
const router = createRouter({
    history: createWebHistory(),
    routes,
})


// 全局的路由守卫,会在每次路由进行跳转的时候执行
router.beforeEach((to) => {
    console.log(to)
        //1、如果去的是登陆页面，就放行
    if (to.name === 'login') {
        return true
    }

    // 2、检查是否登陆，如果已经登陆则放行
    console.log(store.getters.isLogin)
    if (!store.getters.isLogin) {
        // 去storage中查看,如果也没有就去登陆页面
        if (!storage.getSessionObject("loginUser")) {
            router.push({ name: 'login' })
        } else {
            store.dispatch("RECOVERY_USER");
            store.dispatch("GET_INFO");
        }

    }


    // 3、没有登陆就跳转到登陆页面

    return true
})

// 讲路由实例导出
export default router