import React from 'react';
import { useHistory } from 'react-router-dom';
import './PaymentPage.css'; // Import the CSS file for styling
// import Razorpay from 'razorpay';

function PaymentConfirm() {
    const history = useHistory();
    const home = () => {
        history.push('/DealerDashboard');
      };


  return (
    <div className="payment-container">
      <div className="payment-content">
        <img src="https://cdn.dribbble.com/users/1915816/screenshots/5733479/pay-wait.gif" alt="Product" />
        <div className="payment-details">
          
          <p><strong>Payment:</strong>  Successful</p>
          <button className='paymentBtn' onClick={home}>Home</button>
        </div>
        
      </div>
    </div>
  );
}

export default PaymentConfirm;
