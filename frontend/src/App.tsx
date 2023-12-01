import React from 'react'
import logo from './logo.svg'
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import LandingPage from './Page/LandingPage'

function App() {
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<LandingPage />} />
                </Routes>
            </BrowserRouter>
        </>
    )
}

export default App
{
    /*//*/
}
{
    /*// <div className="App">*/
}
{
    /*//      <h1>Hello this is the accessible to the public, React!</h1>*/
}
{
    /*//     <a href="http://localhost:8080/oauth2/authorization/okta">Login</a>*/
}
{
    /*//     <br/>*/
}
{
    /*//     <a href="http://localhost:8080/logout">Logout</a>*/
}
{
    /*// </div>*/
}
