import React from 'react'
import { useAuth0 } from '@auth0/auth0-react'
function LoginLogout() {
    const {
        isLoading,
        isAuthenticated,
        error,
        user,
        loginWithRedirect,
        logout,
    } = useAuth0()

    if (isLoading) {
        return <div>Loading...</div>
    }
    if (error) {
        return <div>Oops... {error.message}</div>
    }
    // @ts-ignore
    if (user !== undefined) {
        console.log('this is the user object: ' + user.roles)
    }
    if (isAuthenticated) {
        return (
            <div>
                {/* @ts-ignore*/}
                Hello {user.name}
                {/* @ts-ignore*/}
                Hello {user.email}{' '}
                <button
                    onClick={() =>
                        logout({
                            logoutParams: { returnTo: window.location.origin },
                        })
                    }
                >
                    Log out
                </button>
            </div>
        )
    } else {
        return <button onClick={() => loginWithRedirect()}>Log in</button>
    }
}
export default LoginLogout
