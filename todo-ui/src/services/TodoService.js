/*import axios from "axios";

const BASE_REST_API_URL = 'http://localhost:8090/api/todos';


// adding a request interceptor to add auth token in the requests
axios.interceptors.request.use(function (config) {
    // Do something before request is sent
    config.headers['Authorization'] = getToken();


    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });


// export function getAllTodos(){
//     return axios.get(BASE_REST_API_URL);
// }

/*
function getAllTodos() {
 return axios.get(BASE_REST_API_URL);
}

export default getAllTodos

WE can write this code simple by using arrow functions.
/*
export const getAllTodos = () => axios.get(BASE_REST_API_URL)

export const saveTodo = (todo) => axios.post(BASE_REST_API_URL, todo)

export const getTodo = (id) => axios.get(BASE_REST_API_URL + '/' + id)

export const updateTodo = (id, todo) => axios.put(BASE_REST_API_URL + '/' + id, todo)

export const deleteTodo = (id) => axios.delete(BASE_REST_API_URL + '/' + id)

export const completeTodo = (id) => axios.patch(BASE_REST_API_URL + '/' + id + '/complete')

export const inCompleteTodo = (id) => axios.patch(BASE_REST_API_URL + '/' + id + '/in-complete')
*/


// in below using a different axios instance as i got errors when i use the same instance 
// i got error related to authorization when i try to login 
import axios from "axios";
import { getToken } from "./AuthService";

const BASE_REST_API_URL = 'http://localhost:8090/api/todos';

const instance = axios.create();



// adding a request interceptor to add auth token in the requests
instance.interceptors.request.use(function (config) {
    // Do something before request is sent
    config.headers['Authorization'] = getToken();
    console.log(getToken());


    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });


// export function getAllTodos(){
//     return axios.get(BASE_REST_API_URL);
// }

/*
function getAllTodos() {
 return axios.get(BASE_REST_API_URL);
}

export default getAllTodos

WE can write this code simple by using arrow functions.
*/
export const getAllTodos = () => instance.get(BASE_REST_API_URL)

export const saveTodo = (todo) => instance.post(BASE_REST_API_URL, todo)

export const getTodo = (id) => instance.get(BASE_REST_API_URL + '/' + id)

export const updateTodo = (id, todo) => instance.put(BASE_REST_API_URL + '/' + id, todo)

export const deleteTodo = (id) => instance.delete(BASE_REST_API_URL + '/' + id)

export const completeTodo = (id) => instance.patch(BASE_REST_API_URL + '/' + id + '/complete')

export const inCompleteTodo = (id) => instance.patch(BASE_REST_API_URL + '/' + id + '/in-complete')



