import './App.css'
import { BrowserRouter as Router, Route, Routes, BrowserRouter } from 'react-router-dom'
import React from 'react'

import LandingPage from './Pages/LandingPage'
import ShipmentQuotePage from './Pages/ShipmentQuotePage'
import SignUpPage from './Pages/SignUpPage'
import LoginPage from './Pages/LoginPage'
import Footer from './Components/Footer'
import UserHomePage from './Pages/UserHomePage'
import ShipmentsPage from './Pages/ShipmentsPage'
import EstimatorPage from './Pages/ShipmentEstimatorPage'
import ReviewerPage from './Pages/ShipmentReviewerPage'
import LogoutRedirectHandler from './auth/LogoutRedirectHandler'
import { AuthProvider } from './auth/components/AuthService'
import ShipmentDetailsPage from './Pages/ShipmentDetailsPage'

function App() {
    return (
        <>
            <AuthProvider>
                <BrowserRouter>
                    <Routes>
                        <Route path="/" Component={LandingPage} />
                        <Route path="/ShipmentQuote" Component={ShipmentQuotePage} />
                        <Route path="/SignUp" Component={SignUpPage} />
                        <Route path="/Login" Component={LoginPage} />
                        <Route path="/Home" Component={UserHomePage} />
                        <Route path="/Shipments" Component={ShipmentsPage} />
                        <Route path="/Estimator" Component={EstimatorPage} />
                        <Route path="/Reviewer" Component={ReviewerPage} />
                        <Route path="/logout" Component={LogoutRedirectHandler} />
                        <Route path="/ShipmentDetails" Component={ShipmentDetailsPage} />
                    </Routes>
                    <Footer />
                </BrowserRouter>
            </AuthProvider>
        </>
    )
}

export default App
