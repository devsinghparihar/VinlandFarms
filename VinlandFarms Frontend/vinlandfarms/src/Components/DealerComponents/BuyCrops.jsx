import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux';
import { useHistory, useLocation, Link } from 'react-router-dom';
import './BuyCrops.css';
import generateConfig from '../AuthConfig/AuthHeader';

function BuyCrops() {
  const [cropList, setCropList] = useState([]);
  const [selectedCrop, setSelectedCrop] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const dealerId = useSelector(state => state.auth.id);
  const token = useSelector(state => state.auth.token);
  const history = useHistory();
  const location = useLocation();

  useEffect(() => {
    
    axios.get('http://localhost:4865/dealer/searchAllCrops',generateConfig(token))
      .then((response) => {
        setCropList(response.data);
      })
      .catch((error) => {
        console.error('Error fetching crop list:', error);
      });
  }, []);

  const handleBuyNow = async () => {
    if (selectedCrop) {
      const { farmerId, cropType, cropPrice } = selectedCrop;
      const estimatedPrice = quantity * cropPrice;
      const paymentData = {
        farmerId,
        dealerId,
        cropType,
        quantity,
        cropPrice,
        estimatedPrice
      };

      // Navigate to the payment page and pass paymentData as state
      alert("Redirecting to payment page")
      history.push('/payment', { paymentData });
      // Extract information from the selected crop
      // const { farmerId, cropType, cropPrice } = selectedCrop;
      

      // // Calculate the estimated price
      // const estimatedPrice = quantity * cropPrice;

      
      // // http://localhost:5006/dealer/buyCrop/1/1/1/1'
      // try{
      //   console.log(generateConfig(token))
      // const res =await axios.post(`http://localhost:4865/dealer/buyCrop/${farmerId}/${dealerId}/${cropType}/${quantity}`,null,generateConfig(token))
      //  console.log(res.data); }
      //  catch(error){
      //   console.log(error)
      //  }
    }
  };

  return (
    <div className="buy-crops-container">
      <h2>Buy Crops</h2>
      <div className="buy-crops-content">
        <div className="crop-list">
          {cropList.map((crop) => (
            <div key={crop.farmerId + crop.cropType} className="crop-card" onClick={() => setSelectedCrop(crop)}>
              <div className="crop-image">
                <img src="/images/fruitBasket.jpg" alt={crop.cropType} />
              </div>
              <div className="crop-details">
                <div>
                  <strong>Name:</strong> {crop.name}
                </div>
                <div>
                  <strong>Crop Type:</strong> {crop.cropType}
                </div>
                <div>
                  <strong>Price:</strong> ${crop.cropPrice}
                </div>
                <div>
                  <strong>Available Quantity:</strong> {crop.cropQuantity}
                </div>
              </div>
            </div>
          ))}
        </div>
        <div className="sidebar">
          {selectedCrop ? (
            <div>
              <h3>Selected Crop: {selectedCrop.cropType}</h3>
              <p><strong>Provider Name:</strong> {selectedCrop.name}</p>
              <label>
                <strong>Quantity Selected:</strong>
                <input
                  type="number"
                  min="1"
                  max={selectedCrop.cropQuantity}
                  value={quantity}
                  onChange={(e) => setQuantity(e.target.value)}
                />
              </label>
              <p><strong>Estimated Price:</strong> ${quantity * selectedCrop.cropPrice}</p>
              <button onClick={handleBuyNow}>Buy Now</button>
            </div>
          ) : (
            <p>Please click on a card to make a selection.</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default BuyCrops;
