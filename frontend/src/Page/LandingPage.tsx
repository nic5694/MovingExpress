import React from 'react'
import LoginLogout from '../Components/LoginLogout'
import Profile from './Profile'
import axios from 'axios'
function LandingPage() {
    const retreiveDataTestPrivate = () => {
        axios
            .get('http://localhost:8080/api/private')
            .then((response) => {
                console.log(response)
            })
            .catch((error) => {
                console.log(error)
            })
    }
    const retreiveDataTestPrivateScoped = () => {
        axios
            .get('http://localhost:8080/api/private-scoped')
            .then((response) => {
                console.log(response)
            })
            .catch((error) => {
                console.log(error)
            })
    }
    const retreiveDetials = () => {
        axios
            .get('http://localhost:8080/api/info')
            .then((response) => {
                console.log(response)
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return (
        <>
            <h1>Hello this is the accessible to the public, React!</h1>
            <button onClick={retreiveDataTestPrivate}>Test Private</button>
            <br />
            <button onClick={retreiveDataTestPrivateScoped}>
                Test Private Scoped
            </button>
            <br />
            <button onClick={retreiveDetials}>Test Details</button>
            <br />
            <LoginLogout />
            {/*@ts-ignore*/}
            <Profile />
            {/*<a href="http://localhost:8080/oauth2/authorization/okta">Login</a>*/}
            <br />
        </>
    )
}
export default LandingPage
