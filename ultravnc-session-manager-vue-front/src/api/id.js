import http from '@/axios/api.request';

const id = {};

id.releaseSessionId = (param) => {
  return http.request({
    url: 'id',
    method: 'delete',
    params: {
      sessionId: param
    }
  });
};

export default id;