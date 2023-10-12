import React from 'react';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
// import { update } from '../assets/download.jpg'

function FarmerDashboard() {
  const email = useSelector(state => state.auth.username);
  return (
    <div className="container">
      <h1 className="mt-4">Welcome, Farmer John {email}</h1>

      <div className="row">
        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/download.jpg" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">Add Crop</h2>
              <p className="card-text">Click to add a new crop to your inventory.</p>
              <Link to="/addCrop" className="btn btn-primary">Add Crop</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/path-to-transaction-history-image.jpg" className="card-img-top" alt="Transaction History" />
            <div className="card-body">
              <h2 className="card-title">Transaction History</h2>
              <p className="card-text">View your past transactions and earnings.</p>
              <Link to="/transaction-history" className="btn btn-primary">View History</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/path-to-update-profile-image.jpg" className="card-img-top" alt="Update Profile" />
            <div className="card-body">
              <h2 className="card-title">Update Profile</h2>
              <p className="card-text">Edit your profile details and preferences.</p>
              <Link to="/updateFarmerDetails" className="btn btn-primary">Update Profile</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default FarmerDashboard;
