import { Outlet } from "react-router-dom";

export default function Layout() {
    return (
        <>
            <header>
                <a href="/" aria-label="Gå til forsiden">
                    Østfold Husflidslag
                </a>
            </header>

            <Outlet />

            <footer>
                <p>Østfold Husflidslag</p>
            </footer>
        </>
    );
}