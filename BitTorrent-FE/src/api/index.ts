import axios from 'axios';
import { notification } from 'antd';

const baseURL = 'http://localhost:8080/bit-torrent';

const axiosInstance = axios.create({ baseURL });

function errorHandler(err: any) {
  if (err.response) {
    if (err.response?.status >= 400 && err.response?.status <= 499) {
      notification.error({
        message: `Błąd ${err.response.status}`,
        description: 'Przepraszamy, wystąpił problem z danym żądaniem.',
      });
    } else if (err.response?.status >= 500 && err.response?.status <= 599) {
      notification.error({
        message: `Błąd ${err.response.status}`,
        description: 'Przepraszam, wystąpił wewnętrzny błąd systemu.',
      });
    } else {
      throw err;
    }
  } else {
    notification.error({
      message: 'Przesyłanie nie powiodło się!',
      description: 'Przepraszamy, wystąpił nieoczekiwany błąd.',
    });
  }

  return Promise.reject(err);
}

axiosInstance.interceptors.request.use(
  (request) => request,
  (error) => errorHandler(error),
);

axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => errorHandler(error),
);

export default axiosInstance;
