import { Outlet } from "react-router-dom";
import logo from "../assets/logo/nh-logo-standard-1.png";
import "./Layout.css";

export default function Layout() {
    return (
        <>
            <header className="site-header">
                <a href="/" aria-label="Gå til forsiden">
                    <img
                        className="site-header__logo"
                        src={logo}
                        alt="Norges Husflidslag"
                    />
                </a>
            </header>

            <Outlet />

            <footer className="site-footer">
                <div className="site-footer__content">
                    <p>Østfold Husflidslag</p>
                </div>
            </footer>
        </>
    );
}