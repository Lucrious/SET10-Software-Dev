import { Routes, Route } from 'react-router-dom'
import './App.css'
import Layout from './Components/Layout'
import Home from './Pages/Home'
import Newsfeed from './Components/Newsfeed'
import NotFound from './Pages/NotFound'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />

        {/* Midlertidig side for testing av Newsfeed */}
        <Route path="/testnewsfeed" element={<Newsfeed />} />

        {/* Hovedsider */}
        <Route path="/kurs" element={<h1>Kurs og aktiviteter</h1>} />
        <Route path="/aktuelt" element={<h1>Aktuelt</h1>} />
        <Route path="/kontakt" element={<h1>Kontakt oss</h1>} />

        {/* Dynamiske sider */}
        <Route
          path="/kurs/:k"
          element={<h1>Fremtidig dynamisk side for kurs</h1>}
        />
        <Route
          path="/aktuelt/:a"
          element={<h1>Fremtidig dynamisk side for aktuelt/nyheter</h1>} />

          {/* Side ikke funnet */}
          <Route path="*" element={<NotFound />} />

      </Route>

      

    </Routes>
  )
}

export default App