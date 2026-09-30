import { useParams } from "react-router-dom";
import PageLayout from "../Components/PageLayout";

export default function Course(){
    
    /* bruke useParams fra kurset's ID. Denne vil brukes i en spørring for å hente inn kun dette kursets informasjon */
    const {k} = useParams();
    
    return(
        <PageLayout>
            <p>Dette kommer til å bli kurssiden for kurs: {k}</p>
        </PageLayout>
    );
}