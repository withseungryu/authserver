import axios, {AxiosInstance} from 'axios';
//만약 api 서버가 바뀌었을 때, 이 주소를 사용한 부분을 다 바꿔야하기 때문에,, 이렇게 지정을 먼저 해준다. -> 프로젝트 전역에서 사용 할 수 있다.
export default class AxiosService{
  static readonly instance: AxiosInstance = axios.create({
    baseURL: 'https://localhost:8000/',
    timeout: 10000,

  });
}
