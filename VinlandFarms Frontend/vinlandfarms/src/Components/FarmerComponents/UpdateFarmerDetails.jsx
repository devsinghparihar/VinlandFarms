import React, { useState } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux';
import './updateFarmerDetails.css';

function UpdateFarmerDetails() {
  const email = useSelector((state) => state.auth.username);
  const jwtToken = useSelector((state) => state.auth.token);
  const [formData, setFormData] = useState({
    name: '',
    password: '',
    gender: '',
    location: '',
    age: 0,
    accountNumber: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const config = {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${jwtToken}`,
        },
      };

      const response = await axios.put(
        `http://localhost:5001/farmer/updateFarmer/${email}`,
        formData,config
        
      );

      console.log('Farmer details updated:', response.data);
    } catch (error) {
      console.error('Error updating farmer details:', error);
      alert("an error occured")
    }
  };

  return (
    <div className="form-container">
      <h2>Update Farmer Details</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Gender:</label>
          <input
            type="text"
            name="gender"
            value={formData.gender}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Location:</label>
          <input
            type="text"
            name="location"
            value={formData.location}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Age:</label>
          <input
            type="number"
            name="age"
            value={formData.age}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Account Number:</label>
          <input
            type="text"
            name="accountNumber"
            value={formData.accountNumber}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <button type="submit" className="btn btn-update">
            Update Details
          </button>
        </div>
      </form>
    </div>
  );
}

export default UpdateFarmerDetails;
