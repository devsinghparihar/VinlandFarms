import React, { useState } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux';
function UpdateFarmerDetails() {
    const email = useSelector(state => state.auth.username);
    const jwtToken = useSelector(state => state.auth.token);
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
        // setting headers
        const config = {
            headers: {
                
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${jwtToken}`,
              
            },
          };
      
      const response = await axios.post(`http://localhost:5006/farmer/updateFarmer/${email}`, formData,config);

      // Handle the response as needed, e.g., show a success message
      console.log('Farmer details updated:', response.data);
      
    } catch (error) {
      // Handle errors, e.g., display an error message
      console.error('Error updating farmer details:', error);
    }
  };

  return (
    <div>
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
        <button type="submit" className="btn btn-primary">
          Update Details
        </button>
      </form>
    </div>
  );
}

export default UpdateFarmerDetails;
