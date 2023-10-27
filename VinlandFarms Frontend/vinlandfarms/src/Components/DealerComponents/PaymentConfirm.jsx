import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import './PaymentPage.css'; // Import the CSS file for styling
import generateConfig from '../AuthConfig/AuthHeader';
import { useSelector } from 'react-redux';

function PaymentConfirm({location}) {
  const history = useHistory();
  const farmerData = location.state.farmerData;
  const token = useSelector(state => state.auth.token);
  const [rating, setRating] = useState(0);
  const [isRatingSubmitted, setIsRatingSubmitted] = useState(false);

  const handleRatingChange = (newRating) => {
    setRating(newRating);
  };

  const handleSubmitRating = () => {
    if (rating >= 1 && rating <= 5) {
      // Replace 'farmerId' with the actual farmer ID
      const farmerId = farmerData.farmerId;
      console.log(farmerId,rating); // Replace with the actual farmer ID
      axios
        .get(`http://localhost:4865/dealer/updateFarmerRating/${farmerId}/${rating}`,generateConfig(token))
        .then((response) => {
          console.log(response.data);
          setIsRatingSubmitted(true);
        })
        .catch((error) => {
          console.error('Error submitting rating:', error);
        });
    } else {
      alert('Please select a rating between 1 and 5.');
    }
  };

  const home = () => {
    history.push('/DealerDashboard');
  };

  return (
    <div className="payment-container">
      <div className="payment-content">
        <img src="https://cdn.dribbble.com/users/1280935/screenshots/6974685/media/ec4c386222b837da0ff6eabec3f59ba3.gif" alt="Product" />
        <div className="payment-details">
          <p><strong>Payment:</strong> Successful</p>
          {isRatingSubmitted ? (
            <p><strong>Rating Submitted:</strong> {rating} stars</p>
          ) : (
            <div>
              <p><strong>Give a Rating:</strong></p>
              <div className="star-rating">
                {[1, 2, 3, 4, 5].map((star) => (
                  <span
                    key={star}
                    className={`star ${rating >= star ? 'active' : ''}`}
                    onClick={() => handleRatingChange(star)}
                  >
                    &#9733;
                  </span>
                ))}
              </div>
              <button className='paymentBtn' onClick={handleSubmitRating}>Rate!</button>
            </div>
          )}
          <button className='paymentBtn' onClick={home}>Home</button>
        </div>
      </div>
    </div>
  );
}

export default PaymentConfirm;
