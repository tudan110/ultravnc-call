import http from '@/axios/api.request';

const outline = {};

outline.getOutlineInfo = () => {
  return http.request({
    url: 'outline',
    method: 'get'
  });
};

export default outline;