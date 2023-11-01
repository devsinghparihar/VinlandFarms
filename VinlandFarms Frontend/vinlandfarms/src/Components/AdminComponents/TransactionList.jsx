// TransactionList.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './TransactionList.css';
import Receipt from '../StaticPages/Recipt';
import generateConfig from '../AuthConfig/AuthHeader';
import { useSelector } from 'react-redux';

function TransactionList() {
  const [transactions, setTransactions] = useState([]);
  const [selectedTransaction, setSelectedTransaction] = useState(null);
  const [loading, setLoading] = useState(true);
  const token = useSelector(state => state.auth.token);

  useEffect(() => {
    axios
      .get('http://localhost:4865/admin/getAllTransactions',generateConfig(token))
      .then((response) => {
        setTransactions(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching transactions:', error);
        setLoading(false);
      });
  }, []);

  const viewReceipt = (transaction) => {
    setSelectedTransaction(transaction);
  };

  return (
    <div className="transaction-list">
      <div className="left-section">
        <h2>Transaction List</h2>
        {loading ? (
          <div className="loading-spinner">
            <div className="spinner"></div>
          </div>
        ) : (
          <table className="transaction-table">
            <thead>
              <tr>
                <th>Farmer</th>
                <th>Dealer</th>
                <th>Crop Type</th>
                <th>Quantity</th>
                <th>Price per Kg</th>
              </tr>
            </thead>
            <tbody>
              {transactions.map((transaction) => (
                <tr
                  key={transaction.transactionId}
                  onClick={() => viewReceipt(transaction)}
                >
                  <td>{transaction.farmerEmail}</td>
                  <td>{transaction.dealerEmail}</td>
                  <td>{transaction.quantity}</td>
                  <td>{transaction.cropType}</td>    
                  <td>${transaction.pricePerKg}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
      <div className="right-section">
        {selectedTransaction ? (
          <Receipt
            transactionDetails={selectedTransaction}
            onClose={() => setSelectedTransaction(null)}
          />
        ) : (
          <div className="no-transaction-selected">
            <p>Click on a transaction in the table to view the receipt.</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default TransactionList;
