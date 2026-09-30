import { Routes, Route } from 'react-router-dom'
import './App.css'
import Layout from './Components/Layout'
import Home from './Pages/Home'
import Newsfeed from './Components/Newsfeed'
import NotFound from './Pages/NotFound'
import Courses from './Pages/Courses'
import Course from './Pages/Course'
import Newspost from './Pages/Newspost'
import Contact from './Pages/Contact'
import Membership from './Pages/Membership'
import FAQ from './Pages/FAQ'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />

        {/* Midlertidig side for testing av Newsfeed */}
        <Route path="/testnewsfeed" element={<Newsfeed />} />

        {/* Hovedsider */}
        <Route path="/kurs" element={<Courses />} />
        <Route path="/aktuelt" element={<h1>Aktuelt</h1>} />
        <Route path="/kontakt" element={<Contact />} />
        <Route path="/bli-medlem" element={<Membership />} />
        <Route path="/sporsmal-og-svar" element={<FAQ />} />

        {/* Dynamiske sider */}
        <Route
          path="/kurs/:k"
          element={<Course />}
        />
        <Route
          path="/aktuelt/:a"
          element={<Newspost />} />

          {/* Side ikke funnet */}
          <Route path="*" element={<NotFound />} />

      </Route>

      

    </Routes>
  )
}

export default App