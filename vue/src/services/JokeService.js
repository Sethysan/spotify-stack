import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  getJoke() {
    return http.get('/joke');
  },
  saveJoke(joke){
    return http.post("/joke", joke);
  }

 

}
