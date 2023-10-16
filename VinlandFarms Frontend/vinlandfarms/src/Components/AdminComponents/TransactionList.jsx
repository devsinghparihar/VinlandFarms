import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './TransactionList.css'; // Import your CSS file for styling

function TransactionList() {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    // Fetch transactions from your API
    axios.get('http://localhost:5003/admin/getAllTransactions')
      .then((response) => {
        setTransactions(response.data);
      })
      .catch((error) => {
        console.error('Error fetching transactions:', error);
      });
  }, []);

  return (
    <div className="transaction-list">
      <h2>List of Transactions</h2>
      <div className="transaction-cards">
        {transactions.map((transaction) => (
          <div key={transaction.transactionId} className="transaction-card">
            <div className="transaction-header">Transaction</div>
            <div className="transaction-info">
              <p><strong>Transaction ID:</strong> {transaction.transactionId}</p>
              <p><strong>Farmer:</strong> {transaction.farmerEmail}</p>
              <p><strong>Dealer:</strong> {transaction.dealerEmail}</p>
              <p><strong>Crop Type:</strong> {transaction.cropType}</p>
              <p><strong>Quantity:</strong> {transaction.quantity}</p>
              <p><strong>Price per Kg:</strong> ${transaction.pricePerKg}</p>
              <p><strong>Total Price:</strong> ${transaction.totalPrice}</p>
              <p><strong>Booking Time:</strong> {new Date(transaction.bookingTime).toLocaleString()}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default TransactionList;
