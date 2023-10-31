import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import './FarmerRegistration.css'; // Import your CSS file for styling

function FarmerRegistration() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [gender, setGender] = useState('');
  const [location, setLocation] = useState('');
  const [age, setAge] = useState(0);
  const [accountNumber, setAccountNumber] = useState('');
  const [accountBalance, setAccountBalance] = useState(0);
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = {
      name,
      email,
      password,
      gender,
      location,
      age,
      accountNumber,
      accountBalance,
    };

    try {
      const response = await axios.post('http://localhost:4865/public/registerFarmer', formData);

      console.log('Registration successful:', response.data);
      alert("Registration successful... Check your email for confirmation\nRedirecting to the login page");
      history.push('/login');
    } catch (error) {
      console.error('Registration failed:', error);
    }
  };

  return (
    <div className="registration-container">
      <div className="registration-image">
        <img src="/images/farmer.jpg" alt="Registration Image" className="registration-image" />
      </div>
      <div className="registration-form">
        <h2 className="registration-heading">Farmer Registration</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            {/* <label className="form-label">Name:</label> */}
            <input
              type="text"
              value={name}
              placeholder='Enter Name'
              onChange={(e) => setName(e.target.value)}
              required
              className="form-input"
            />
          </div>
          <div className="form-group">
            {/* <label className="form-label">Email:</label> */}
            <input
              type="email"
              value={email}
              placeholder='Enter Email'
              onChange={(e) => setEmail(e.target.value)}
              required
              className="form-input"
            />
          </div>
          <div className="form-group">
            {/* <label className="form-label">Password:</label> */}
            <input
              type="password"
              value={password}
              placeholder='Enter Password'
              onChange={(e) => setPassword(e.target.value)}
              required
              className="form-input"
            />
          </div>
          <div className="form-group">
            {/* <label className="form-label">Gender:</label> */}
            <select
              value={gender}
              onChange={(e) => setGender(e.target.value)}
              required
              className="form-input"
              
            >
              <option value="">Select Gender</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
              <option value="Other">Other</option>
            </select>
          </div>
          <div className="form-group">
            {/* <label className="form-label">Location:</label> */}
            <input
              type="text"
              value={location}
              onChange={(e) => setLocation(e.target.value)}
              required
              placeholder='Enter Location'
              className="form-input"
            />
          </div>
          <div className="form-group">
            {/* <label className="form-label">Age:</label> */}
            <input
              type="number"
              value={age}
              onChange={(e) => setAge(e.target.value)}
              required
              placeholder='Enter Age'
              className="form-input"
            />
          </div>
          <div className="form-group">
            {/* <label className="form-label">Account Number:</label> */}
            <input
              type="text"
              value={accountNumber}
              onChange={(e) => setAccountNumber(e.target.value)}
              required
              placeholder='Enter A/C NO'
              className="form-input"
            />
          </div>
          <button type="submit" className="registration-button">
            Register
          </button>
        </form>
      </div>
    </div>
  );
}

export default FarmerRegistration;
