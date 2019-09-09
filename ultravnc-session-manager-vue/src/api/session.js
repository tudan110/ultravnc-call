import http from '@/axios/api.request';

const session = {};

session.listSessions = (param) => {
  // return http.request({
  //   url: 'session/',
  //   method: 'get',
  //   params: param
  // });
  return http.get('session', {
    params: param
  });
};

session.releaseAllSessions = () => {
  return http.request({
    url: 'session',
    method: 'delete'
  });
};

export default session;