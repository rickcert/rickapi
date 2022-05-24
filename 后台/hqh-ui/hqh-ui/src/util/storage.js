export default {
    saveSessionString(key, value) {
        window.sessionStorage.setItem(key, value);
    },
    getSessionString(key) {
        return window.sessionStorage.getItem(key);
    },
    saveSessionObject(key, value) {
        window.sessionStorage.setItem(key, JSON.stringify(value));
    },
    getSessionObject(key) {
        return JSON.parse(window.sessionStorage.getItem(key));
    },
    remove(key) {
        return window.sessionStorage.removeItem(key);
    },
}