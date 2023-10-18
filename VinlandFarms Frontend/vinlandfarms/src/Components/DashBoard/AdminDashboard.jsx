import React from 'react';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
function AdminDashboard() {
    const role = useSelector(state => state.auth.role);
    const token = useSelector(state => state.auth.token);
    const email = useSelector(state => state.auth.username);
  return (
    <div className="container">
      <h1 className="mt-4">Welcome Dealer</h1>

      <div className="row">
        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer4.jpg" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">Farmer Management</h2>
              <p className="card-text">Manage Farmers.</p>
              <Link to="/farmerPanel" className="btn btn-primary">View</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer3.jpg" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">Dealer Management</h2>
              <p className="card-text">Manage dealers.</p>
              <Link to="/dealerPanel" className="btn btn-primary">View</Link>
            </div>
          </div>
        </div>

        

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer2.jpg" className="card-img-top" alt="Transaction History" />
            <div className="card-body">
              <h2 className="card-title">Transaction History</h2>
              <p className="card-text">View your past transactions and earnings.</p>
              <Link to="/transaction-history" className="btn btn-primary">View History</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer1.jpg" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">View Active Farmers</h2>
              <p className="card-text">View all Active farmers.</p>
              <Link to="/buyCrop" className="btn btn-primary">View</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer4.jpg" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">View Inactive Farmers</h2>
              <p className="card-text">View all Inactive farmers.</p>
              <Link to="/inActiveFarmers" className="btn btn-primary">View</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer3.jpg" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">View Active Dealers</h2>
              <p className="card-text">View all Active dealers.</p>
              <Link to="/buyCrop" className="btn btn-primary">View</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer2.jpg" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">View Inactive dealers</h2>
              <p className="card-text">View all Inactive dealers.</p>
              <Link to="/buyCrop" className="btn btn-primary">View</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="/images/farmer1.jpg" className="card-img-top" alt="Update Profile" />
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

export default AdminDashboard;
