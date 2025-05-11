  import React, { useState } from "react";
  import axios from "axios"; // ← правилното място за импорта
  import Balance from "./Balance";
  import HoldingsTable from "./HoldingsTable";
  import TransactionForm from "./TransactionForm";
  import CryptoPrices from "./CryptoPrices";
  import TransactionHistory from "./TransactionHistory";

  const Dashboard = ({ userId }) => {
    const [refreshCounter, setRefreshCounter] = useState(0);

    const refreshData = () => {
      setRefreshCounter(prev => prev + 1);
    };
    
    const resetBalance = () => {
      axios.put(`http://localhost:8080/api/user/${userId}/reset`)
        .then(() => {
          alert("Balance is 10000 USD");
          refreshData(); // презареждане на данните
        })
        .catch(err => {
          console.error("Error during balance reset:", err);
          alert("Error with reset");
        });
  };


    return (
      <div>
        <Balance userId={userId} refreshKey={refreshCounter} />

        <button onClick={resetBalance}>
          Reset
        </button>
        <CryptoPrices />
        <TransactionForm userId={userId} onTransactionComplete={refreshData} />
        <HoldingsTable userId={userId} refreshKey={refreshCounter} />
        <TransactionHistory userId={userId} refreshKey={refreshCounter} />
      </div>
    );
  };

  export default Dashboard;
