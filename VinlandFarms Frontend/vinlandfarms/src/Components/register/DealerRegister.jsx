import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import './FarmerRegistration.css';

function DealerRegistration() {
  const history = useHistory();
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [gender, setGender] = useState('');
  const [age, setAge] = useState(0);
  const [accountNumber, setAccountNumber] = useState('');

  // Validation state variables
  const [isNameValid, setIsNameValid] = useState(true);
  const [isEmailValid, setIsEmailValid] = useState(true);
  const [isPasswordValid, setIsPasswordValid] = useState(true);
  const [isGenderValid, setIsGenderValid] = useState(true);
  const [isAgeValid, setIsAgeValid] = useState(true);
  const [isAccountNumberValid, setIsAccountNumberValid] = useState(true);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validation logic
    const isNameValid = name.trim() !== '';
    const isEmailValid = /\S+@\S+\.\S+/.test(email); // Basic email format validation
    const isPasswordValid = password.length >= 6; // Example: Password length >= 6 characters
    const isGenderValid = gender !== '';
    const isAgeValid = age >= 18; // Example: Age is greater than or equal to 18
    const isAccountNumberValid = accountNumber.trim() !== '';

    // Update validation state variables
    setIsNameValid(isNameValid);
    setIsEmailValid(isEmailValid);
    setIsPasswordValid(isPasswordValid);
    setIsGenderValid(isGenderValid);
    setIsAgeValid(isAgeValid);
    setIsAccountNumberValid(isAccountNumberValid);

    // If any field is not valid, do not submit the form
    if (!isNameValid || !isEmailValid || !isPasswordValid || !isGenderValid || !isAgeValid || !isAccountNumberValid) {
      return;
    }

    // Continue with form submission
    const formData = {
      name,
      email,
      password,
      gender,
      age,
      accountNumber,
    };

    try {
      const response = await axios.post('http://localhost:4865/public/registerDealer', formData);
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
        <img src="/images/dealer.jpg" alt="Registration Image" className="registration-image" />
      </div>
      <div className="registration-form">
        <h2 className="registration-heading">Dealer Registration</h2>
        <form onSubmit={handleSubmit}>
          <div className={`form-group ${isNameValid ? '' : 'invalid'}`}>
            <input
              type="text"
              value={name}
              placeholder='Enter Name'
              onChange={(e) => setName(e.target.value)}
              className="form-input"
            />
            {!isNameValid && <div className="error-message">Name is required</div>}
          </div>
          <div className={`form-group ${isEmailValid ? '' : 'invalid'}`}>
            <input
              type="text"
              value={email}
              placeholder='Enter Email'
              onChange={(e) => setEmail(e.target.value)}
              className="form-input"
            />
            {!isEmailValid && <div className="error-message">Enter a valid email</div>}
          </div>
          <div className={`form-group ${isPasswordValid ? '' : 'invalid'}`}>
            <input
              type="password"
              value={password}
              placeholder='Enter Password'
              onChange={(e) => setPassword(e.target.value)}
              className="form-input"
            />
            {!isPasswordValid && <div className="error-message">Password must be at least 6 characters</div>}
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
            {!isGenderValid && <div className="error-message">Gender is required</div>}
          </div>
          <div className={`form-group ${isAgeValid ? '' : 'invalid'}`}>
            <input
              type="number"
              value={age}
              onChange={(e) => setAge(e.target.value)}
              placeholder='Enter Age'
              className="form-input"
            />
            {!isAgeValid && <div className="error-message">Age must be at least 18</div>}
          </div>
          <div className={`form-group ${isAccountNumberValid ? '' : 'invalid'}`}>
            <input
              type="text"
              value={accountNumber}
              onChange={(e) => setAccountNumber(e.target.value)}
              placeholder='Enter A/C NO'
              className="form-input"
            />
            {!isAccountNumberValid && <div className="error-message">Account Number is required</div>}
          </div>
          <button type="submit" className="registration-button">
            Register
          </button>
        </form>
      </div>
    </div>
  );
}

export default DealerRegistration;
