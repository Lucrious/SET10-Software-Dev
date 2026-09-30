import HomeNavigation from "./HomeNavigation";
import "./PageLayout.css";

export default function PageLayout({ children }) {
    return (
        <main className="page-layout">
            <HomeNavigation />

            <section className="page-layout__content">
                {children}
            </section>
        </main>
    );
}