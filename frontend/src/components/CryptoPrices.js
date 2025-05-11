import React, { useEffect, useState } from "react";
import axios from "axios";

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

const CryptoPrices = () => {
  const [prices, setPrices] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios.get("/api/crypto/prices")
      .then(response => {
        setPrices(response.data);
        setLoading(false);
      })
      .catch(error => {
        console.error("Error fetching prices:", error);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading prices...</p>;

  return (
    <div>
      <h2>Market Prices</h2>
      <table>
        <thead>
          <tr>
            <th>Symbol</th>
            <th>Price (USD)</th>
          </tr>
        </thead>
        <tbody>
          {prices.map((priceObj, index) => (
            <tr key={index}>
              <td>{symbolMap[priceObj.symbol] || priceObj.symbol}</td>
              <td>{priceObj.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CryptoPrices;
