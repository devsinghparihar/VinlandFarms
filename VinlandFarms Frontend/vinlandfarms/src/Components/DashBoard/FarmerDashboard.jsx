import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
import Carousel from '../Carousel/carousel';
import Home from '../StaticPages/Home';

function FarmerDashboard() {
  const email = useSelector((state) => state.auth.username);
  const id = useSelector((state) => state.auth.id);
  const farmerTagline = 'Farmers Unite for Prosperity.';
  const farmerPara = 'Bringing farmers together in a digital farming ecosystem. Empower yourself with technology and unlock the potential of your farm.';
  const imgSrc = '/images/background.jpg';

  const [transactionData, setTransactionData] = useState([]);
  const [showAlert, setShowAlert] = useState(false);

  useEffect(() => {
    // Fetch farmer transaction history here using API
    fetchFarmerTransactionHistory();
  }, []);

  const fetchFarmerTransactionHistory = () => {
    // Replace the API URL with the correct one
    fetch(`http://localhost:5001/farmer/farmerTransactionHistory/${id}`)
      .then((response) => response.json())
      .then((data) => {
        setTransactionData(data);

        // Calculate the number of transactions in the last hour
        const oneHourAgo = new Date();
        oneHourAgo.setHours(oneHourAgo.getHours() - 1);
        const transactionsInLastHour = data.filter(
          (transaction) => new Date(transaction.bookingTime) > oneHourAgo
        );

        if (transactionsInLastHour.length > 1) {
          setShowAlert(true);
        }
      })
      .catch((error) => {
        console.error('Error fetching farmer transaction history:', error);
      });
  };

  return (
    <>
      <h1 className="text-center">Welcome, Farmer</h1>
      <Carousel></Carousel>
      <div className="container">
        <Home tagline={farmerTagline} para={farmerPara} imgSrc={imgSrc} />
      </div>
      <div className="container">
        {showAlert && (
          <div className="alert alert-warning alert-dismissible" role="alert">
            {transactionData.length} transactions have been done. Check transaction history for more details.
            <button type="button" className="close" onClick={() => setShowAlert(false)}>
                <span aria-hidden="true">&times;</span>
              </button>
          </div>
        )}

        <div className="row">
          <div className="col-md-4 mb-4">
            <div className="card">
              <img src="/images/farmer4.jpg" className="card-img-top" alt="Add Crop" />
              <div className="card-body">
                <h2 className="card-title">Add Crop</h2>
                <p className="card-text">Click to add a new crop to your inventory.</p>
                <Link to="/addCrop" className="btn btn-primary">
                  Add Crop
                </Link>
              </div>
            </div>
          </div>

          <div className="col-md-4 mb-4">
            <div className="card">
              <img src="/images/farmer2.jpg" className="card-img-top" alt="Transaction History" />
              <div className="card-body">
                <h2 className="card-title">Transaction History</h2>
                <p className="card-text">View your past transactions and earnings.</p>
                <Link to="/transactions" className="btn btn-primary">
                  View History
                </Link>
              </div>
            </div>
          </div>

          <div className="col-md-4 mb-4">
            <div className="card">
              <img src="/images/farmer3.jpg" className="card-img-top" alt="Update Profile" />
              <div className="card-body">
                <h2 className="card-title">Update Profile</h2>
                <p className="card-text">Edit your profile details and preferences.</p>
                <Link to="/farmerProfile" className="btn btn-primary">
                  Update Profile
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default FarmerDashboard;
