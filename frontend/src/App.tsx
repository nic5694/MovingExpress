import React from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
         <h1>Hello this is the accessible to the public, React!</h1>
        <a href="http://localhost:8080/oauth2/authorization/okta">Login</a>
        <br/>
        <a href="http://localhost:8080/logout">Logout</a>
    </div>
  );
}

export default App;
