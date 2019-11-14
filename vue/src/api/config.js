export const API_URL = process.env.VUE_APP_API_URL_BASE;

import Vue from "vue";
import VueAxios from "vue-axios";
import axios from "axios";

Vue.use(VueAxios, axios);
Vue.axios.defaults.baseURL = API_URL;

const ApiService = {
    query(resource, params) {
        return Vue.axios.get(resource, params).catch(error => {
            throw new Error(`[RWV] ApiService ${error}`);
        });
    },

    get(resource, slug = "") {
        return Vue.axios.get(`${resource}/${slug}`,{withCredentials: true}).catch(error => {
            throw new Error(`[RWV] ApiService ${error}`);
        });
    },

    post(resource, params) {
        return Vue.axios.post(`${resource}`, params,{withCredentials:true});
    },

    update(resource, slug, params) {
        return Vue.axios.put(`${resource}/${slug}`, params,{withCredentials:true});
    },

    put(resource, params) {
        return Vue.axios.put(`${resource}`, params,{withCredentials:true});
    },

    delete(resource) {
        return Vue.axios.delete(resource,{withCredentials:true}).catch(error => {
            throw new Error(`[RWV] ApiService ${error}`);
        });
    }
};

export default ApiService;
