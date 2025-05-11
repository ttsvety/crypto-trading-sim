import React, { useEffect, useState } from "react";
import axios from "axios";

const HoldingsTable = ({ userId, refreshKey }) => {

  const [holdings, setHoldings] = useState([]);
  
const symbolMap = {
  XXBTZUSD: "BTC/USD",
  XETHZUSD: "ETH/USD",
  XXRPZUSD: "XRP/USD",
  XLTCZUSD: "LTC/USD",
  XETCZUSD: "ETC/USD",
  XXLMZUSD: "XLM/USD",
  XDGUSD: "DOGE/USD",
  ADAUSD: "ADA/USD",
  ALGOUSD: "ALGO/USD",
  NEARUSD: "NEAR/USD",
  EOSUSD: "EOS/USD",
  FILUSD: "FIL/USD",
  MATICUSD: "MATIC/USD",
  AVAXUSD: "AVAX/USD",
  SOLUSD: "SOL/USD",
  BCHUSD: "BCH/USD",
  TRXUSD: "TRX/USD",
  LINKUSD: "LINK/USD",
  DOTUSD: "DOT/USD",
  ATOMUSD: "ATOM/USD"
};

  useEffect(() => {
  axios.get(`http://localhost:8080/api/holdings/user/${userId}`)
    .then(response => setHoldings(response.data))
    .catch(error => console.error("Error fetching holdings:", error));
}, [userId, refreshKey]);


  return (  
    <div>
      <h2>Your Holdings</h2>
      <table>
        <thead>
          <tr>
            <th>Symbol</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {holdings.map((h, i) => (
            <tr key={i}>
              <td>{symbolMap[h.symbol] || h.symbol}</td>
              <td>{h.amount.toFixed(6)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default HoldingsTable;
