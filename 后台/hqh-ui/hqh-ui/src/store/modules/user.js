import { login, logout, } from '@/api/user.js'
import storage from "@/util/storage";
const user = {
    state: {
        username: '',
        nickname: '',
        token: '',
        ip: '',
        time: '',
        avatar: '',
    },
    getters: {

    },
    mutations: {
        SAVE_USERNAME(state, username) {
            state.username = username;
        },
        SAVE_NICKNAME(state, nickname) {
            state.nickname = nickname;
        },
        SAVE_TOKEN(state, token) {
            state.token = token;
        },
        SAVE_IP(state, ipaddr) {
            state.ipaddr = ipaddr;
        },
        SAVE_TIME(state, time) {
            state.time = time;
        },
        SAVE_AVATAR(state, avatar) {
            state.avatar = avatar;

        }

    },
    actions: {
        LOGIN({ commit }, user) {
            return new Promise(function(resolve) {
                login(user).then(res => {
                    // 需要将获取的数据，保存起来
                    commit("SAVE_USERNAME", res.data.hqhUser.userName);
                    commit("SAVE_NICKNAME", res.data.hqhUser.nickName);
                    commit("SAVE_AVATAR", res.data.hqhUser.avatar);
                    commit("SAVE_TOKEN", res.data.token);
                    commit("SAVE_IP", res.data.ipaddr);
                    commit("SAVE_TIME", res.data.loginTime);
                    storage.saveSessionObject("loginUser", res.data);
                    resolve(res);
                })
            })
        },
        LOGOUT({ commit }) {
            return new Promise(function(resolve) {
                logout().then(res => {
                    commit("SAVE_USERNAME", '');
                    commit("SAVE_NICKNAME", '');
                    commit("SAVE_AVATAR", '');
                    commit("SAVE_TOKEN", '');
                    commit("SAVE_IP", '');
                    commit("SAVE_TIME", '');
                    storage.remove("loginUser");
                    resolve(res);
                })
            })
        },
        RECOVERY_USER({ commit }) {
            // 从storage中获取数据
            let loginUser = storage.getSessionObject("loginUser");
            if (loginUser) {
                commit("SAVE_USERNAME", loginUser.hqhUser.userName);
                commit("SAVE_NICKNAME", loginUser.hqhUser.nickName);
                commit("SAVE_TOKEN", loginUser.token);
                commit("SAVE_IP", loginUser.ipaddr);
                commit("SAVE_TIME", loginUser.loginTime);
                commit("SAVE_AVATAR", loginUser.hqhUser.avatar);
            }

        }
    }
}

export default user