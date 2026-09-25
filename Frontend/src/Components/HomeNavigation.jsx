import { Link } from "react-router-dom";
import "./HomeNavigation.css";

export default function HomeNavigation() {
    return (
        <nav className="home-navigation" aria-label="Hovedvalg">
            <Link className="home-navigation__item" to="/kurs">
                Kurs og aktiviteter
            </Link>

            <Link className="home-navigation__item" to="/kontakt">
                Kontakt oss
            </Link>

            <Link className="home-navigation__item" to="/bli-medlem">
                Bli medlem
            </Link>

            <Link className="home-navigation__item" to="/sporsmal-og-svar">
                Spørsmål og svar
            </Link>
        </nav>
    );
}