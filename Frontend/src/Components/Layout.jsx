import { useState } from "react";
import { Link, Outlet } from "react-router-dom";
import logo from "../assets/logo/nh-logo-standard-1.png";
import "./Layout.css";

export default function Layout() {
    // Holder styr på om mobilmenyen er åpen eller lukket
    const [menuOpen, setMenuOpen] = useState(false);

    return (
        <>
            <header className="site-header">
                <Link
                    className="site-header__logo-link"
                    to="/"
                    aria-label="Gå til forsiden"
                >
                    <img
                        className="site-header__logo"
                        src={logo}
                        alt="Norges Husflidslag"
                    />
                </Link>

                <button
                    className="menu-button"
                    type="button"
                    aria-label="Åpne hovedmeny"
                    aria-expanded={menuOpen}
                    aria-controls="main-navigation"
                    onClick={() => setMenuOpen(!menuOpen)}
                >
                    <span>Meny</span>
                    <span className="menu-button__icon" aria-hidden="true">☰</span>
                </button>

                {/* Legger til open-klassen når mobilmenyen er åpnet */}
                <nav
                    id="main-navigation"
                    className={`site-navigation ${menuOpen ? "site-navigation--open" : ""}`}
                    aria-label="Hovedmeny"
                >
                    <Link to="/">Forside</Link>
                    <Link to="/kurs">Kurs og aktiviteter</Link>
                    <Link to="/bli-medlem">Bli medlem</Link>
                </nav>
            </header>

            <Outlet />

            <footer className="site-footer">
                <div className="site-footer__content">
                    <nav
                        className="site-footer__navigation"
                        aria-label="Bunnmeny"
                    >
                        <Link to="/kontakt">Kontakt oss</Link>
                        <Link to="/sporsmal-og-svar">Spørsmål og svar</Link>
                    </nav>
                </div>
            </footer>
        </>
    );
}