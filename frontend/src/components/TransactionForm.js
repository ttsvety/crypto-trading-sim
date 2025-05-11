import React, { useState, useEffect } from "react";
import axios from "axios";

const symbolMap = {
  BTC: "XXBTZUSD",
  ETH: "XETHZUSD",
  XRP: "XXRPZUSD",
  LTC: "XLTCZUSD",
  ETC: "XETCZUSD",
  XLM: "XXLMZUSD",
  DOGE: "XDGUSD",
  ADA: "ADAUSD",
  ALGO: "ALGOUSD",
  NEAR: "NEARUSD",
  EOS: "EOSUSD",
  FIL: "FILUSD",
  MATIC: "MATICUSD",
  AVAX: "AVAXUSD",
  SOL: "SOLUSD",
  BCH: "BCHUSD",
  TRX: "TRXUSD",
  LINK: "LINKUSD",
  DOT: "DOTUSD",
  ATOM: "ATOMUSD"
};

const TransactionForm = ({ userId, onTransactionComplete }) => {
  const [symbol, setSymbol] = useState("");
  const [quantity, setQuantity] = useState("");
  const [price, setPrice] = useState("");
  const [transactionType, setTransactionType] = useState("buy");
  const [prices, setPrices] = useState({});

  // Зареждаме цените при зареждане
  useEffect(() => {
    axios.get("http://localhost:8080/api/crypto/prices")
      .then(response => {
        const pricesMap = {};
        response.data.forEach(p => {
          pricesMap[p.symbol] = p.price;
        });
        setPrices(pricesMap);
      })
      .catch(error => console.error("Error fetching prices:", error));
  }, []);

  // Когато символът се промени, обновяваме автоматично цената
  useEffect(() => {
    const krakenSymbol = symbolMap[symbol];
    if (krakenSymbol && prices[krakenSymbol]) {
      setPrice(prices[krakenSymbol].toFixed(2));
    }
  }, [symbol, prices]);

  const handleSubmit = (e) => {
    e.preventDefault();

    const quantityNum = parseFloat(quantity);
    const priceNum = parseFloat(price);

    if (!symbol || quantityNum <= 0 || priceNum <= 0) {
      alert("Please enter valid symbol, quantity, and price.");
      return;
    }

    const krakenSymbol = symbolMap[symbol];
    if (!krakenSymbol) {
      alert("Unsupported symbol.");
      return;
    }

    const transaction = {
      userId,
      symbol: krakenSymbol,
      quantity: quantityNum,
      price: priceNum,
      type: transactionType,
      total: quantityNum * priceNum
    };

    axios.post("http://localhost:8080/api/transactions", transaction)
      .then(() => {
        alert("Transaction successful!");
        setSymbol("");
        setQuantity("");
        setPrice("");
        if (onTransactionComplete) {
          onTransactionComplete();
        }
      })
      .catch(error => alert("Error: " + error.message));
  };

  return (
    <div>
      <h2>New Transaction</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Symbol (e.g., BTC)"
          value={symbol}
          onChange={(e) => setSymbol(e.target.value.toUpperCase())}
          required
        />
        <input
          type="number"
          placeholder="Quantity"
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
          min="0.000001"
          step="0.000001"
          required
        />
        <input
          type="number"
          placeholder="Price (USD)"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          min="0.01"
          step="0.01"
          required
          disabled // забраняваме ръчно писане
        />
        <select value={transactionType} onChange={(e) => setTransactionType(e.target.value)}>
          <option value="buy">Buy</option>
          <option value="sell">Sell</option>
        </select>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default TransactionForm;
