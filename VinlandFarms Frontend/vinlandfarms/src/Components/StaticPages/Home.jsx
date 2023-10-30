import React from 'react';
import './Home.css';
import { Link } from 'react-router-dom';

const Home = ({ tagline, para, imgSrc }) => {
  return (
    <div className="home-container">
      <div className="home-section">
        <h3 className='home-tagline'>{tagline}</h3>
        <p className='home-para'>{para}</p>
        <div className='linkContainer'>
          <Link to="/aboutus" className="">Learn More About Us.</Link>
        </div>
      </div>
      <div className='image'>
        <img src={imgSrc} alt='home'></img>
      </div>
    </div>
  );
}

export default Home;
