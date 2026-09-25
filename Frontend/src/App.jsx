import { Routes, Route } from 'react-router-dom'
import './App.css'
import Layout from './Components/Layout'
import Home from './Pages/Home'
import Newsfeed from './Components/Newsfeed'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="/testnewsfeed" element={<Newsfeed />} />
        <Route
          path="/aktuelt/:a"
          element={<h1>Fremtidig dynamisk side for aktuelt/nyheter</h1>}
        />
        <Route
          path="/kurs/:k"
          element={<h1>Fremtidig dynamisk side for kurs</h1>}
        />
      </Route>
    </Routes>
  )
}

export default App