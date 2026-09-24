import "./Home.css";
import HomeNavigation from "../Components/HomeNavigation";

export default function Home() {
    return (
        <main className="home">
            <section className="home__news">
                <h1>Siste nytt</h1>
                {/* Nyhetskomponenten legges inn her */}
            </section>

            <HomeNavigation />
        </main>
    );
}