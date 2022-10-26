import request from '@/utils/request'

export default {
    
    getTrack(id) {
        return request({
            url: '/song/url',
            method: 'get',
            params: {
                id,
            }
        })
    },

    getTrackDetail(id) {
        return request({
            url: '/song/detail',
            method: 'get',
            params: {
                id
            }
        })
    }
}