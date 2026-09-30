import { useState } from "react";
import { Link, Outlet } from "react-router-dom";
import logo from "../assets/logo/nh-logo-standard-1.png";
import "./Layout.css";
import HomeNavigation from "./HomeNavigation";

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

            {/* HomeNavigation her*/}
                
                 
                <Outlet />
                
            

           

            <footer className="site-footer">
                <div className="site-footer__content">

                    <div className="site-footer__columns">
                        <section className="site-footer__section">
                            <h3>
                                <Link className="site-footer__heading-link" to="/kontakt">
                                    Kontakt
                                </Link>
                            </h3>

                            <p>
                                Sentralbord:<br />
                                <a href="tel:+4722008700">22 00 87 00</a>
                            </p>

                            <p>
                                <a href="mailto:post@husflid.no">post@husflid.no</a>
                            </p>

                            <address>
                                Øvre Slottsgate 2b<br />
                                0157 Oslo
                            </address>
                        </section>

                        <section className="site-footer__section site-footer__hours">
                            <h3>Åpningstider</h3>

                            <div className="site-footer__hours-list">
                                <p>
                                    <span>Mandag–tirsdag</span>
                                    <span>10–13</span>
                                </p>
                                <p>
                                    <span>Onsdag</span>
                                    <span>Stengt</span>
                                </p>
                                <p>
                                    <span>Torsdag–fredag</span>
                                    <span>10–13</span>
                                </p>
                                <p>
                                    <span>Stengt</span>
                                    <span>11.30–12.00</span>
                                </p>
                            </div>
                        </section>

                        <section className="site-footer__section">
                            <h3>Sosiale medier</h3>

                            <ul className="site-footer__links">
                                <li>
                                    <a href="https://husflid.no/nyhetsbrev/">
                                        Nyhetsbrev
                                    </a>
                                </li>
                                <li>
                                    <a href="https://www.instagram.com/norgeshusflidslag/">
                                        Instagram
                                    </a>
                                </li>
                                <li>
                                    <a href="https://www.facebook.com/NorgesHusflidslag/">
                                        Facebook
                                    </a>
                                </li>
                                <li>
                                    <a href="https://www.youtube.com/user/NorgesHusflidslag">
                                        YouTube
                                    </a>
                                </li>
                            </ul>
                        </section>
                    </div>

                    <div className="site-footer__bottom">
                        <p>© Østfold Husflidslag</p>
                    </div>
                </div>
            </footer>
        </>
    );
}