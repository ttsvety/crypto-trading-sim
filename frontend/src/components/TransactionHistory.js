import React, { useEffect, useState } from "react";
import axios from "axios";

const TransactionHistory = ({ userId, refreshKey }) => {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/transactions/user/${userId}`)
      .then(res => {
        setTransactions(res.data);
        setLoading(false);
      })
      .catch(err => {
        console.error("Failed to fetch transactions", err);
        setLoading(false);
      });
  }, [userId, refreshKey]);

  return (
    <div>
      <h2>Transaction History</h2>
      {loading ? (
        <p>Loading transactions...</p>
      ) : transactions.length === 0 ? (
        <p>No transactions found.</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Type</th>
              <th>Symbol</th>
              <th>Quantity</th>
              <th>Price (USD)</th>
              <th>Total</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((t, index) => (
              <tr key={index}>
                <td>{t.type}</td>
                <td>{t.symbol}</td>
                <td>{t.quantity}</td>
                <td>{t.price.toFixed(2)}</td>
                <td>{(t.quantity * t.price).toFixed(2)}</td>
                <td>{new Date(t.timestamp).toLocaleString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default TransactionHistory;
