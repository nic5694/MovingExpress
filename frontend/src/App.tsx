import './App.css';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import React from 'react'

import LandingPage from './Pages/LandingPage';
import ShipmentQuotePage from './Pages/ShipmentQuotePage';
import SignUpPage from './Pages/SignUpPage';
import LoginPage from './Pages/LoginPage';
import Footer from './Components/Footer';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" Component={LandingPage} />
        <Route path='/ShipmentQuote' Component={ShipmentQuotePage} />
        <Route path='/SignUp' Component={SignUpPage} />
        <Route path='/Login' Component={LoginPage} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
