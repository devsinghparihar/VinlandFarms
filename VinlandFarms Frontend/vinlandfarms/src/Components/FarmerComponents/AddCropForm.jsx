import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import axios from 'axios';

function AddCropForm() {
  const [cropType, setCropType] = useState('');
  const [cropPrice, setCropPrice] = useState(0);
  const [cropQuantity, setCropQuantity] = useState(0);
  const [location, setLocation] = useState('');

//   const dispatch = useDispatch();
//   const userEmail = useSelector((state) => state.user.username);
  const userEmail = useSelector(state => state.auth.username);
    
  const handleSubmit = (e) => {
    e.preventDefault();

    const cropData = {
      cropType,
      cropPrice,
      cropQuantity,
      location,
    };

    // Send crop data to the API
    // dispatch(addCrop(userEmail, cropData));
    try {
        const api = `http://localhost:5001/farmer/addCrop/${userEmail}`;
        // Send formData to the API for farmer registration
        console.log(api);
        console.log(cropData);
        
        const response =  axios.post(api, cropData);
  
        // Handle the response as needed, e.g., show a success message or redirect to a login page
        console.log('Crop posted successfully:', response.data);
        alert("crop posted successfully");
        // history.push('/log');
      } catch (error) {
        // Handle registration error, e.g., display an error message or handle server-side validation errors
        console.error('Action failed:', error);
      }

    // Clear form fields
    setCropType('');
    setCropPrice(0);
    setCropQuantity(0);
    setLocation('');
  };

  return (
    <div>
      <h2>Add Crop{userEmail}</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Crop Type:</label>
          <input
            type="text"
            className="form-control"
            value={cropType}
            onChange={(e) => setCropType(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Crop Price:</label>
          <input
            type="number"
            className="form-control"
            value={cropPrice}
            onChange={(e) => setCropPrice(parseInt(e.target.value))}
            required
          />
        </div>
        <div className="form-group">
          <label>Crop Quantity:</label>
          <input
            type="number"
            className="form-control"
            value={cropQuantity}
            onChange={(e) => setCropQuantity(parseInt(e.target.value))}
            required
          />
        </div>
        <div className="form-group">
          <label>Location:</label>
          <input
            type="text"
            className="form-control"
            value={location}
            onChange={(e) => setLocation(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Add Crop
        </button>
      </form>
    </div>
  );
}

export default AddCropForm;
