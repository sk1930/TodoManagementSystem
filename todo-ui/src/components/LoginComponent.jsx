import React, { useState } from 'react'
import { loginAPICall, saveLoggedInUser, storeToken } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';

const LoginComponent = () => {

const [username,setUserName] = useState('');
const [password,setPassword] = useState('');
const navigator = useNavigate();



function handleLoginForm(e){
//161. Display the links as per user authentication in the header

    e.preventDefault();
    //So this will prevent the default activities for this form


    const loginObj = {username, password}

    console.log(loginObj);
    loginAPICall(username,password).then((response)=>{
        console.log("seems i didnt print first I waited after loginAPICall is called back from axios ");
        console.log(response.data);
        /* const token = 'Basic '+window.btoa(username+":"+password);
        this is for Basic authentication
        For Jwt token based authentication we have to use Bearer + token from the response
        */
       const token = 'Bearer '+response.data.accessToken;
    
       const role = response.data.role;
        saveLoggedInUser(username,role);

        storeToken(token);
        navigator("/todos")

        window.location.reload();

    }).catch((error)=>console.error(error))

    console.log("am after loginApiCall which is after and I execute first if this is asynchronous call")
}
/*
// actually async and await are not needed here.
async function handleLoginForm(e){

    e.preventDefault();
    //So this will prevent the default activities for this form


    const loginObj = {username, password}

    console.log(loginObj);
    await loginAPICall(username,password).then((response)=>{
        console.log("this time loginAPICall is not executed asynchronously  and control waits here untill callback is made for loginAPICall ");
        console.log(response.data);
        const token = 'Basic '+window.btoa(username+":"+password);
        saveLoggedInUser(username);

        storeToken(token);
        navigator("/todos")

        window.location.reload();
        // reload is necessary to reload the headerComponent to hide register and login and display Todos

    }).catch((error)=>console.error(error))

    console.log("am after loginApiCall which is after and I execute last as if this is asynchronous call")
}
*/


return (
<div className='container'>
    <div className='row'>
        <div className='col-md-6 offset-md-3'>
            <div className='card'>
                <div className='card-header'>
                    <h2 className='text-center'> Login Form</h2>
                </div>

                <div className='card-body'>
                    <form >
                       
                        <div className='row mb-3'>
                            <label className='col-md-3 control-label'> Username  or Email</label>
                            <div className='col-md-9'>
                                <input
                                    type='text'
                                    name='username'
                                    className='form-control'
                                    placeholder='Enter username'
                                    value={username}
                                    onChange={ (e) => setUserName(e.target.value)}
                                >
                                </input>
                            </div>
                        </div>



                        <div className='row mb-3'>
                            <label className='col-md-3 control-label'> Password </label>
                            <div className='col-md-9'>
                                <input
                                    type='password'
                                    name='password'
                                    className='form-control'
                                    placeholder='Enter password'
                                    value={password}
                                    onChange={ (e) => setPassword(e.target.value)}
                                >
                                </input>
                            </div>
                        </div>

                        <div className='form-group mb-3'>
                            <button className='btn btn-primary' onClick={ (e) => handleLoginForm(e)}>Submit</button>

                        </div>



                    </form>


                </div>
            </div>
        </div>
    </div>


    
</div>
)
}


export default LoginComponent
