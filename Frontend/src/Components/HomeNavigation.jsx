import "./HomeNavigation.css";

export default function HomeNavigation() {
    return (
        <nav className="home-navigation" aria-label="Hovedvalg">
            <a className="home-navigation__item" href="/kurs">
                Kurs og aktiviteter
            </a>

            <a className="home-navigation__item" href="/kontakt">
                Kontakt oss
            </a>

            <a className="home-navigation__item" href="/bli-medlem">
                Bli medlem
            </a>

            <a className="home-navigation__item" href="/sporsmal-og-svar">
                Spørsmål og svar
            </a>
        </nav>
    );
}