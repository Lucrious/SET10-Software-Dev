import "../Style/Contact.css";
import PageLayout from "../Components/PageLayout";

export default function Contact(){
    const people = [
        {
            name: "Geir Geirson",
            email: "Geir@gmail.com",
            phone: 91839381,
            img: "https://img.magnific.com/free-photo/handsome-smiling-man-taking-selfie_176420-18045.jpg?semt=ais_hybrid&w=740&q=80"            
        }, 
        {
            name: "Geir2 Geirson2",
            email: "Geir2@gmail.com",
            phone: 92839382,
            img: "https://img.magnific.com/free-photo/handsome-smiling-man-taking-selfie_176420-18045.jpg?semt=ais_hybrid&w=740&q=80"            
        }, 
        {
            name: "Trine Trineson",
            email: "Trine@gmail.com",
            phone: 91483381,
            img: "https://img.magnific.com/free-photo/handsome-smiling-man-taking-selfie_176420-18045.jpg?semt=ais_hybrid&w=740&q=80"            
        }, 
    ];

    return (
        <PageLayout>
            <section id="staff-section">
                {people.map((person, index) => 
                    <article className="person-card" key={"person" + index}>
                        <img src={person.img} alt={`Bilde av ${person.name}`} />
                        <h3>{person.name}</h3>
                        <p>{person.email} (fiks)</p>
                        <p>{person.phone}</p>
                    </article>
                )}
            </section>
        </PageLayout>
    );
}