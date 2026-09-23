import { useState } from 'react'
import { Routes, Route } from 'react-router-dom'
import './App.css'
import Layout from './Components/Layout'
import Home from './Pages/Home'

function App() {

  return (
    <Routes>
      <Route path='' element={<Layout/>}>
        <Route path='/side' element={<Home/>}></Route>
        <Route></Route>
      </Route>
    </Routes>
  )
}

export default App
