import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import HelloWorld from './HelloWorld'
import ListTodoComponent from './components/ListTodoComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import { BrowserRouter,Routes,Route} from 'react-router-dom'
import TodoComponent from './components/TodoComponent'

function App() {
  const [count, setCount] = useState(0)

  /*return (
    <>
     <HelloWorld/>
     <HelloWorld/>
     <HelloWorld/>
     <HelloWorld/>
     <HelloWorld/>
    
     </>
  )*/
  return (
    <>
    <BrowserRouter>
    <HeaderComponent/>
    <Routes>
      {/* http://localhost:8090 */}
      <Route path='/' element = {<ListTodoComponent/>}>
              </Route>

      {/* http://localhost:8090/todos */}
      <Route path='/todos' element = {<ListTodoComponent/>}>
              </Route>

    {/* http://localhost:8090/add-todo */}
      <Route path='/add-todo' element = {<TodoComponent/>}>
              </Route>

    {/* http://localhost:8090/update-todo/id */}
    <Route path='/update-todo/:id' element = {<TodoComponent/>}>
              </Route>
              


    </Routes>
    <FooterComponent/>
    </BrowserRouter>
    </>


  )

}

export default App
