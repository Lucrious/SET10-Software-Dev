import { Link } from 'react-router-dom'
import '../Style/Stylesheet.css'

export default function Newsfeed(){

    //Placeholder array med objekter for å teste nyheter/aktuelt komponentet før databasen blir koblet sammen med frontend
    const placeHolderNews = [{title: "Nytt strikkekurs", date:"28-sep-2026", img: "https://images.garnstudio.com/img/school/lessons/4-th.jpg", type: ["Aktuelt"], price: 2000},
                             {title: "Ny medlemsklubb", date: "29-sep-2026", img: "https://barshopen.no/images/list/hammar-club-soda-1-41203.png", type: ["Nyhet"]}
    ]

    

    return(
        <section id='news-section'>
            {/* Mapper gjennom arrayet placeHolderNews og lager egne artikler for hvert innlegg. Byttes med en useState som holder på innleggene hentet fra databasen når dette er klart.
            
            Skal fikse dynamisk side så det blir mulig å trykke på en nyhet/aktuelt artikkel i feeden og få en bedre/mer nøye beskrivelse på denne.
            */}
            {placeHolderNews?.map((post, index) => 
                                            
                                            <article className="news-article"  key={'news'+index}>
                                            <Link to={"/"}>
                                            <img src={post.img}></img>
                                            <p>{post.date} | {post.type}</p>
                                            <h2>{post.title}</h2>
                                            {post.price ? <p>{post.price} NOK</p> : null}
                                            </Link>  
                                            </article>
                                            )}
        </section>
    )
}