import "./Home.css";
import HomeNavigation from "../Components/HomeNavigation";
import Newsfeed from "../Components/Newsfeed";

export default function Home() {
    return (
        <main className="home">
            <section className="home__news">
                <h1>Siste nytt</h1>
                <Newsfeed />
            </section>

            <HomeNavigation />
        </main>
    );
}