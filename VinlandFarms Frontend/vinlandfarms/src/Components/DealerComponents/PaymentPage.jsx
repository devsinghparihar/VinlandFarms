import React, { useState, useEffect } from 'react';
import axios from 'axios';
import generateConfig from '../AuthConfig/AuthHeader';
import { useSelector } from 'react-redux';
import { useHistory, useLocation } from 'react-router-dom';
import './PaymentPage.css';

function PaymentPage({ location, history }) {
  const paymentData = location.state.paymentData;
  const token = useSelector(state => state.auth.token);
  const id = useSelector(state => state.auth.id);

  const [dealerInfo, setDealerInfo] = useState(null);
  const [showInsufficientBalanceAlert, setShowInsufficientBalanceAlert] = useState(false);

  useEffect(() => {
    if (id) {
      axios.get(`http://localhost:4865/dealer/findDealerById/${id}`, generateConfig(token))
        .then((response) => {
          setDealerInfo(response.data);
        })
        .catch((error) => {
          console.error('Error fetching dealer info:', error);
        });
    }
  }, [id, token]);

  const paymentStart = async () => {
    if (dealerInfo && dealerInfo.accountBalance < paymentData.estimatedPrice) {
      setShowInsufficientBalanceAlert(true);
    } else {

      let amount = paymentData.estimatedPrice;

      if (amount == '' || amount == null) {
        alert("amount is required");
        return;
      }
      try {
        const response = await axios.get("http://localhost:5007/payment/create_order/" + amount);
        console.log(response.data);
        openTransactionModal(response.data)
      } catch (error) {
        alert(error);
      }

      try {
        const { farmerId, dealerId, cropType, quantity } = paymentData;
        const response = await axios.post(
          `http://localhost:4865/dealer/buyCrop/${farmerId}/${dealerId}/${cropType}/${quantity}`,
          null,
          generateConfig(token)
        );

        console.log(response.data);
        const farmerData = {
          farmerId,
        };
        history.push('/paymentConfirm', { farmerData });
      } catch (error) {
        console.log(error);
      }
    }
  };

  function openTransactionModal(response) {
    var options = {
      order_id: response.orderId,
      key: response.key,
      amount: response.amount,
      currency: response.currency,
      name: 'Vinland Farms',
      description: 'Payment for Crop Booking',
      image: 'https://cdn.pixabay.com/photo/2012/10/10/05/04/train-60539_1280.jpg',
      handler: (res) => {
        processResponse(res);
      },
      prefill: {
        name: 'VinlandFarms',
        email: 'vinlandfarms@saga.com',
        contact: '7000636867'
      },
      notes: {
        address: 'Vinland Farms'
      },
      theme: {
        color: '#F37256'
      }
    };


    var razorpay = new window.Razorpay(options);
    razorpay.open();
    try {
      const { farmerId, dealerId, cropType, quantity } = paymentData;
      const response = axios.post(
        `http://localhost:4865/dealer/buyCrop/${farmerId}/${dealerId}/${cropType}/${quantity}`,
        null,
        generateConfig(token)
      );

      console.log(response.data);
      const farmerData = {
        farmerId,

      };
      history.push('/paymentConfirm', { farmerData });
    } catch (error) {
      console.log(error);
    }


  }
  function processResponse(resp) {
    console.log(resp);
  }

  return (
    < >
      {showInsufficientBalanceAlert && (
        <div className="alert alert-danger" role="alert">
          Insufficient balance. Please add funds to your account.
        </div>
      )}
      <div className="payment-container">


        <div className="payment-content">
          <img src="https://i.pinimg.com/originals/f8/c4/22/f8c422a0a0e6793b3f9113d419c5143a.gif" alt="Product" />
          <div className="payment-details">
            <h2>Payment Page</h2>
            <p><strong>Farmer ID:</strong> {paymentData.farmerId}</p>
            <p><strong>Dealer ID:</strong> {paymentData.dealerId}</p>
            <p><strong>Crop Type:</strong> {paymentData.cropType}</p>
            <p><strong>Quantity:</strong> {paymentData.quantity}</p>
            <p><strong>Crop Price:</strong> {paymentData.cropPrice}</p>
            <p><strong>Estimated Bill:</strong> {paymentData.estimatedPrice}</p>
            <button className='paymentBtn' onClick={paymentStart}>Buy Now</button>
          </div>
        </div>
      </div>
    </>
  );
}

export default PaymentPage;
