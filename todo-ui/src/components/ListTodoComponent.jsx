import React, { useEffect, useState } from 'react'
import { getAllTodos,deleteTodo, completeTodo,inCompleteTodo } from '../services/TodoService'
import { useNavigate } from 'react-router-dom'
import { isAdminUser } from '../services/AuthService';


const ListTodoComponent = () => {
    
    /*const dummyData = [
        {
            "id": "fdffc000-944b-4912-ab1c-33739fdb2223",
            "title": "Learn spring boot",
            "description": "learn with example",
            "completed": false
        },
        {
            "id": "fdffc000-944b-4912-ab1c-33739fdb2225",
            "title": "dghLearn spring boot1",
            "description": "fhlearn with example",
            "completed": true
        }
    ]


    const [todos,setTodos] = useState(dummyData)
    */


    const navigate = useNavigate();
    const isAdmin = isAdminUser();

    const [todos,setTodos] = useState([])

    function listTodos()
    {
        getAllTodos().then((response)=>{
            setTodos(response.data);
        }).catch(error=>{console.error(error);
        })
    }

    useEffect(()=>{
        listTodos();
    },[])

    function addNewTodo(){
        navigate('/add-todo')


    }
    function updateTodo(id){
        console.log(id)
        // backTick symbol
        navigate(`/update-todo/${id}`)


    }

        
    function removeTodo(id){
        deleteTodo(id).then((response) => {
            listTodos();
        }).catch(error => {
            console.error(error)
        })
    }
    function markCompleteTodo(id){
        completeTodo(id).then((response) => {
            listTodos()
        }).catch(error => {
            console.error(error)
        })
    }

    

    function markInCompleteTodo(id){
        inCompleteTodo(id).then((response) => {
            listTodos();
        }).catch(error => {
            console.error(error)
        })
    }

    


  return (
<div className='container'>
        <h2 className='text-center'>List of Todos</h2>
        { // this button will be visible only to admin user.
        // same for update and delete buttons
        isAdmin &&
        <button className='btn btn-primary mb-2' onClick={addNewTodo}>Add Todo</button>
}
        <div>
            <table className='table table-bordered table-striped'>
                <thead>
                    <tr>
                        <th>Todo Title</th>
                        <th>Todo Description</th>
                        <th>Todo Completed</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {/* So within a JSX, we can write the JavaScript code plus HTML code.
                    So in order to write the JavaScript code, we have to provide open and close curly braces.
                    */}
                    {
                        todos.map(todo => 
                            <tr key={todo.id}>
                                <td>{todo.title}</td>
                                <td>{todo.description}</td>
                                <td>{todo.completed ? 'YES': 'NO'}</td>
                                <td>
                                    {isAdmin &&
                                    <button className='btn btn-info' onClick={() => updateTodo(todo.id)}>Update</button> 
                                        }
                                        {
                                            isAdmin &&
                                    <button className='btn btn-danger' onClick={() => removeTodo(todo.id)} style={ { marginLeft: "10px" }} >Delete</button> 
                                        }
                                    <button className='btn btn-success' onClick={() => markCompleteTodo(todo.id)} style={ { marginLeft: "10px" }} >Complete</button>
                                    <button className='btn btn-info' onClick={() => markInCompleteTodo(todo.id)} style={ { marginLeft: "10px" }} >In Complete</button>
                                </td>
                            </tr>
                        )
                    }




                </tbody>
            </table>
        </div>

    </div>  )
}

export default ListTodoComponent

