import React from 'react'
import axios from 'axios'
axios.defaults.withCredentials = true
function LandingPage() {
    const retreiveDataTestPrivate = () => {
        axios
            .get('http://localhost:8080/api/v1/movingexpress/public')
            .then((response) => {
                console.log(response)
            })
            .catch((error) => {
                console.log(error)
            })
    }
    const retreiveDataTestPrivateScoped = () => {
        axios
            .get('http://localhost:8080/api/v1/movingexpress/private')
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
            <button onClick={retreiveDataTestPrivate}>Test Public</button>
            <br />
            <button onClick={retreiveDataTestPrivateScoped}>
                Test Private Scoped
            </button>
            <br />
            <a href="http://localhost:8080/oauth2/authorization/okta">Login</a>

            <br />
            <form
                method={'post'}
                action={'http://localhost:8080/api/v2/movingexpress/logout'}
            >
                <button type={'submit'}>Logout</button>
            </form>

            {/*@ts-ignore*/}

            {/*<a href="http://localhost:8080/oauth2/authorization/okta">Login</a>*/}
            <br />
        </>
    )
}
export default LandingPage
