import axios from "axios";


const AUTH_REST_API_BASE_URL = "http://localhost:8090/api/auth"

/*
this cam also be written using arrow function
export function registerAPICall(registerObj)
{
    return axios.post(AUTH_REST_API_BASE_URL+'/register',registerObj);

}

*/
/*
as it has only one statement we cvan remove the return keyword and braces
export const registerAPICall = (registerObj) => {
    return axios.post(AUTH_REST_API_BASE_URL+'/register',registerObj);
}
*/
export const registerAPICall = (registerObj) => axios.post(AUTH_REST_API_BASE_URL+'/register',registerObj);



/* in loginDto.java we have 
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}


*/


export const loginAPICall = (usernameOrEmail,password) => {

    console.log("am here in loginApiCall");

    console.log(usernameOrEmail); /* this is showing undefined if     loginAPICall(loginObj).then((response)=>{ 
        and even if we try to unpack the object like this export const loginAPICall = ({usernameOrEmail,password}) => {
*/
    return axios.post(AUTH_REST_API_BASE_URL+'/login',{usernameOrEmail,password});
}
/*
So this is the browser local storage object.
It will store a token in a browser local storage.

localStorage.setItem() takes a key and value

*/

export const storeToken = (token) => localStorage.setItem("token",token);

export const getToken = () =>localStorage.getItem("token");




export const saveLoggedInUser = (username) => {
    console.warn(username);
    sessionStorage.setItem("authenticatedUser", username);

}

export const isUserLoggedIn = () => {

    const username = sessionStorage.getItem("authenticatedUser");

    if(username == null) {
        return false;
    }    
    else {
        return true;
    }   
} 

export const getLoggedInUser = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username;
}


export const logout = ()=>{
    localStorage.clear();
    sessionStorage.clear();
}
