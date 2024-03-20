import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import './FarmerRegistration.css';

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

  // Validation state variables
  const [isNameValid, setIsNameValid] = useState(true);
  const [isEmailValid, setIsEmailValid] = useState(true);
  const [isPasswordValid, setIsPasswordValid] = useState(true);
  const [isGenderValid, setIsGenderValid] = useState(true);
  const [isLocationValid, setIsLocationValid] = useState(true);
  const [isAgeValid, setIsAgeValid] = useState(true);
  const [isAccountNumberValid, setIsAccountNumberValid] = useState(true);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validation logic
    const isNameValid = name.trim() !== '';
    const isEmailValid = /\S+@\S+\.\S+/.test(email);
    const isPasswordValid = password.length >= 6;
    const isGenderValid = gender !== '';
    const isLocationValid = location.trim() !== '';
    const isAgeValid = age >= 18;
    const isAccountNumberValid = accountNumber.trim() !== '';

    // Update validation state variables
    setIsNameValid(isNameValid);
    setIsEmailValid(isEmailValid);
    setIsPasswordValid(isPasswordValid);
    setIsGenderValid(isGenderValid);
    setIsLocationValid(isLocationValid);
    setIsAgeValid(isAgeValid);
    setIsAccountNumberValid(isAccountNumberValid);

    // If any field is not valid, do not submit the form
    if (
      !isNameValid ||
      !isEmailValid ||
      !isPasswordValid ||
      !isGenderValid ||
      !isLocationValid ||
      !isAgeValid ||
      !isAccountNumberValid
    ) {
      return;
    }

    // Continue with form submission
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
      alert(
        'Registration successful... Check your email for confirmation\nRedirecting to the login page'
      );
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
          <div className={`form-group ${isNameValid ? '' : 'invalid'}`}>
            <input
              type="text"
              value={name}
              placeholder="Enter Name"
              onChange={(e) => setName(e.target.value)}
             
              className="form-input"
            />
            {!isNameValid && <span className="error-message">Name is required</span>}
          </div>
          <div className={`form-group ${isEmailValid ? '' : 'invalid'}`}>
            <input
              type="email"
              value={email}
              placeholder="Enter Email"
              onChange={(e) => setEmail(e.target.value)}
             
              className="form-input"
            />
            {!isEmailValid && <span className="error-message">Invalid email address</span>}
          </div>
          <div className={`form-group ${isPasswordValid ? '' : 'invalid'}`}>
            <input
              type="password"
              value={password}
              placeholder="Enter Password"
              onChange={(e) => setPassword(e.target.value)}
              
              className="form-input"
            />
            {!isPasswordValid && <span className="error-message">Password must be at least 6 characters</span>}
          </div>
          <div className={`form-group ${isGenderValid ? '' : 'invalid'}`}>
            <select
              value={gender}
              onChange={(e) => setGender(e.target.value)}
              
              className="form-input"
            >
              <option value="">Select Gender</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
              <option value="Other">Other</option>
            </select>
            {!isGenderValid && <span className="error-message">Gender is required</span>}
          </div>
          <div className={`form-group ${isLocationValid ? '' : 'invalid'}`}>
            <input
              type="text"
              value={location}
              onChange={(e) => setLocation(e.target.value)}
             
              placeholder="Enter Location"
              className="form-input"
            />
            {!isLocationValid && <span className="error-message">Location is required</span>}
          </div>
          <div className={`form-group ${isAgeValid ? '' : 'invalid'}`}>
            <input
              type="number"
              value={age}
              onChange={(e) => setAge(e.target.value)}
            
              placeholder="Enter Age"
              className="form-input"
            />
            {!isAgeValid && <span className="error-message">Age must be 18 or older</span>}
          </div>
          <div className={`form-group ${isAccountNumberValid ? '' : 'invalid'}`}>
            <input
              type="text"
              value={accountNumber}
              onChange={(e) => setAccountNumber(e.target.value)}
            
              placeholder="Enter A/C NO"
              className="form-input"
            />
            {!isAccountNumberValid && <span className="error-message">Account Number is required</span>}
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
