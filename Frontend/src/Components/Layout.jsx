import { Outlet } from "react-router-dom";

export default function Layout(){
    return(
        <>
            <header><p>Layout Header</p></header>
            <main>
                <Outlet/>
            </main>
            <footer></footer>
        </>
    )
}