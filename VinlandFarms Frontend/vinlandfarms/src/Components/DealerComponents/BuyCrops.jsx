import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './BuyCrops.css'; // Import your CSS for styling

function BuyCrops() {
  const [cropList, setCropList] = useState([]);
  const [selectedCrop, setSelectedCrop] = useState(null);
  const [quantity, setQuantity] = useState(1);

  useEffect(() => {
    // Fetch the list of crop entities from the API
    axios.get('http://localhost:5002/dealer/searchAllCrops ')
      .then((response) => {
        setCropList(response.data);
      })
      .catch((error) => {
        console.error('Error fetching crop list:', error);
      });
  }, []);

  const handleBuyNow = () => {
    if (selectedCrop) {
      // Extract information from the selected crop
      const { farmerId, cropType, cropPrice } = selectedCrop;
      const dealerId = 'your_dealer_id'; // Replace with the actual dealer ID

      // Calculate the estimated price
      const estimatedPrice = quantity * cropPrice;

      // Call the buyCrop API with the selected quantity
      axios.post(`/dealer/buyCrop/${farmerId}/${dealerId}/${cropType}/${quantity}`)
        .then((response) => {
          // Handle the purchase response here
          console.log('Purchase successful:', response.data);
        })
        .catch((error) => {
          // Handle purchase errors here
          console.error('Error purchasing crop:', error);
        });
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
                <img src="/images/crop.jpg" alt={crop.cropType} />
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
