import React from 'react';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import Carousel from '../Carousel/carousel';
import AboutUs from '../StaticPages/AboutUs';
import './Dashboard.css';
import Home from '../StaticPages/Home';
function AdminDashboard() {
    const role = useSelector(state => state.auth.role);
    const token = useSelector(state => state.auth.token);
    const email = useSelector(state => state.auth.username);
    const adminTagline = 'Admin Panel for Efficient Management.';
    const adminPara = 'Managing and overseeing the farming community. Keep everything organized and efficient with our admin tools.';
    const imgSrc = '/images/adminBackground.gif';

  return (
    <>
    <h1 className="text-center">Welcome To Vinland Farms </h1>
      <Carousel></Carousel>
      <div className="container">
      <Home tagline={adminTagline} para={adminPara} imgSrc={imgSrc} />
      </div>
      
    <div className="container">
      
      <div className="row">
        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="https://i.pinimg.com/originals/50/78/a0/5078a05eb1b6847d93383eaa4c0ed500.gif" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">Farmer Management</h2>
              <p className="card-text">Manage Farmers.</p>
              <Link to="/farmerPanel" className="dashboardBtn">View</Link>
            </div>
          </div>
        </div>

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="https://media.swipepages.com/2020/12/animation_500_kjb5357c-min.gif" className="card-img-top" alt="Add Crop" />
            <div className="card-body">
              <h2 className="card-title">Dealer Management</h2>
              <p className="card-text">Manage dealers.</p>
              <Link to="/dealerPanel" className="dashboardBtn">View</Link>
            </div>
          </div>
        </div>

        

        <div className="col-md-4 mb-4">
          <div className="card">
            <img src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/141a2594777445.5e87284ae2666.gif" className="card-img-top" alt="Transaction History" />
            <div className="card-body">
              <h2 className="card-title">Transaction History</h2>
              <p className="card-text">View All transactions.</p>
              
              <Link to="/transactions" className="dashboardBtn text-center">View </Link>
            </div>
          </div>
        </div>

      
      </div>
      
    </div>
    
    </>
  );
}

export default AdminDashboard;
