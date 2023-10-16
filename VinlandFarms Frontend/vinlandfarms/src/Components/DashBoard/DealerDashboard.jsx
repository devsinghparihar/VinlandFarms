import React from 'react';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
function DealerDashboard() {
  const email = useSelector(state => state.auth.username);
  return (
    <div className="container">
      <h1 className="mt-4">Welcome Dealer</h1>

      <div className="row">
        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer1.jpg" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">Buy Crops</h2>
              <p className="card-text">Click to add a new crop to your inventory.</p>
              <Link to="/buyCrop" className="btn btn-primary">Buy Crop</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer2.jpg" className="card-img-top" alt="Transaction History" />
            <div className="card-body">
              <h2 className="card-title">Transaction History</h2>
              <p className="card-text">View your past transactions and earnings.</p>
              <Link to="/dealerTransactions" className="btn btn-primary">View History</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer3.jpg" className="card-img-top" alt="Update Profile" />
            <div className="card-body">
              <h2 className="card-title">Update Profile</h2>
              <p className="card-text">Edit your profile details and preferences.</p>
              <Link to="/dealerProfile" className="btn btn-primary">Update Profile</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default DealerDashboard;
