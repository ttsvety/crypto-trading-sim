import React, { useEffect, useState } from "react";
import axios from "axios";

const Balance = ({ userId, refreshKey }) => {

  const [balance, setBalance] = useState(0);

  useEffect(() => {
  axios.get(`http://localhost:8080/api/user/${userId}`)
    .then(response => {
      if (response.data && typeof response.data.balance === "number") {
        setBalance(response.data.balance);
      }
    })
    .catch(error => console.error("Error fetching balance:", error));
}, [userId, refreshKey]); // добавено refreshKey


  return (
    <div>
      <h2>Balance</h2>
      <p>{balance.toFixed(2)} USD</p>
    </div>
  );
};

export default Balance;
