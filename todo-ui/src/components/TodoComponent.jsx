import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { getTodo, saveTodo, updateTodo } from '../services/TodoService';


const TodoComponent = () => {



  const { id } = useParams()
  // Returns an object of key/value pairs of the dynamic params from the current URL that were matched by the route path.
  // So in order to get the todo id from the url we can use useParams hook.



  const navigate = useNavigate();

  // for id we use useParams


    const [title,setTitle] = useState();
    const [description,setDescription] = useState();
    const [completed,setCompleted] = useState(false);
    // here we are setting false as initial value
    function saveOrUpdateTodo(e){
      e.preventDefault()
      // So this function will prevent if there is anything default actions that happening while submitting this form.

      const todo = {title, description, completed}
      console.log(todo);

      if(id){

        
          updateTodo(id, todo).then((response) => {
              navigate('/todos')
          }).catch(error => {
              console.error(error);
          })

      }else{
          saveTodo(todo).then((response) => {
              console.log(response.data)
              navigate('/todos')
          }).catch(error => {
              console.error(error);
          })
      }
  }

  function pageTitle(){
    if(id) {
        return <h2 className='text-center'>Update Todo</h2>
    }else {
        return <h2 className='text-center'>Add Todo</h2>
    }
  }
/*Here we are going to use useEffect hook to populate the Todo form so 
that user can go ahead and update the Todo page.
*/
  useEffect(()=>{

    if(id){
      getTodo(id).then((response)=>{
        console.log(response.data)
        setTitle(response.data.title)
        setDescription(response.data.description)
        setCompleted(response.data.completed)
      }).catch(error=>{
        console.error(error)
      })
    }
  },[id])





  return (
    <div className='container'>
      <br/><br/>
      <div className='row'>
        <div className='card col-md-6 offset-md-3 offset-md-3'>
          {pageTitle()}
          <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Todo Title:</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Enter Todo Title'
                                name='title'
                                value={title}
                                onChange={(e) => setTitle(e.target.value)}
                            >
                            </input>
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Todo Description:</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Enter Todo Description'
                                name='description'
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                            >
                            </input>
                        </div>
                        {/* Here we use select box with options  */}

                        <div className='form-group mb-2'>
                            <label className='form-label'>Todo Completed:</label>
                            <select
                                className='form-control'
                                value={completed}
                                onChange={(e) => setCompleted(e.target.value)}
                            >
                                <option value="false">No</option>
                                <option value="true">Yes</option>

                            </select>
                        </div>

                        {/* 
                        
                        we can write this as <button className='btn btn-success' onClick={ (e) => saveOrUpdateTodo(e)}>Submit</button> */}
                       <button className='btn btn-success' onClick={saveOrUpdateTodo}>Submit</button>


                    </form>

                </div>
            </div>

        </div>
    </div>
  )
}

export default TodoComponent