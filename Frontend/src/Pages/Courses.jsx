import { Link } from "react-router-dom"

export default function Courses(){
    {/* Veldig simpel placeholder kursstruktur for å teste siden før databasen kobles opp */}
    const courses = [{
        courseName: "Strikkekurs",
        courseDate: "30/09/2026",
        courseLocation: "Halden husflidslag",
        courseId: 12312839
    },
    {
        courseName: "Kurs2",
        courseDate: "1/10/2026",
        courseLocation: "Halden husflidslag",
        courseId: 82938283
    }

]

    return(
        <main>
            <section>
                {/* Mapper gjennom kursene, dette vil mappe gjennom en state som holder på kursene som har blitt hentet inn fra databasen når den er klar*/}  
                {courses.map((course, index) => 
                    <article key={index} style={{border: "1px solid black", width: "10rem"}}>
                        {/* Hvert enkelt kurs vil linke til sin egen kursside med å bruke kursets id. Det er mulig vi velger å bruke en type slug til linken senere*/}
                        <Link to={`/kurs/${course.courseId}`}>
                        <h3>{course.courseName}</h3>
                        <p>{course.courseDate}</p>
                        <p>{course.courseLocation}</p>
                        </Link>
                    </article>
                )}
            </section>
        </main>
    )
}