// App.js
import './App.css';


import React from "react";
import Dashboard from "./components/Dashboard";

function App() {
  const userId = 1; // или каквото ID искаш да тестваш

  return (
    <div className="App">
      <h1>Crypto Trading Simulator</h1> 
      <Dashboard userId={userId} />
    </div>
  );
}

export default App;
