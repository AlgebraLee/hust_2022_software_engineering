import axios from "axios";

const service  = axios.create({
    baseURL: '/api',
    timeout: 5000,
    withCredentials: true
})

service.interceptors.request.use(
    config => {
        return config
    },
    error => {
        console.log('err: ' + error)
        return Promise.reject(error)
    }
)

service.interceptors.response.use(
    response => {
        return response.data
    },
    error => {
        console.log('err:' + error)
        return Promise.reject(error)
    }
)

export default service