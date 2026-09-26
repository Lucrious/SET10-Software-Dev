import './NotFound.css'
import HomeNavigation from '../Components/HomeNavigation'
import { Link } from 'react-router-dom'

export default function NotFound() {
    return (
        <main className="not-found">
            <HomeNavigation />

            <section className="not-found__content">
                <h1>Siden finnes ikke</h1>

                <p>
                    Siden du prøver å åpne finnes ikke, eller den kan ha blitt flyttet.
                </p>

                <Link className="not-found__link" to="/">
                    Tilbake til forsiden
                </Link>
            </section>
        </main>
    )
}