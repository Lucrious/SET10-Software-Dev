import { useParams } from "react-router-dom"

export default function Course(){
    
    /* bruke useParams fra kurset's ID. Denne vil brukes i en spørring for å hente inn kun dette kursets informasjon */
    const {k} = useParams()
    
    
    return(
        <main>
            <p>Dette kommer til å bli kurssiden for kursid: {k}</p>
        </main>
    )
}