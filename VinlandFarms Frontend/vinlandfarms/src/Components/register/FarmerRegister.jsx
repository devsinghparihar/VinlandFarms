import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import '../Login/login.css'

function FarmerRegistration() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [gender, setGender] = useState('');
  const [location, setLocation] = useState('');
  const [age, setAge] = useState(0);
  const [accountNumber, setAccountNumber] = useState('');
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
      
    };

    try {
        
        
        const response = await axios.post('http://localhost:5006/public/registerFarmer', formData);
  
       
        console.log('Registration successful:', response.data);
        alert("Registration succcessfull... Check your email for confirmation\nRedirecting to login page");
        history.push('/login');
      } catch (error) {
        
        console.error('Registration failed:', error);
      }
    };

  return (
    <>
      <div className="section log">
        <div className="logImg">
          <img src="/images/admin.jpg" alt="" />
        </div>
        <form action="" className="form-control" >
          <h1 className="login">Welcome to VinlandFarms</h1>
          <div className="inputFields" id='inp'>
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              name="email"
              // value={emailCheck}
              // onChange={handleEmailChange}
              required
              className="text-field"
              // ref={email}
            />
            {/* {!isEmailValid(emailCheck) && (
              <span className="badge rounded-pill text-bg-danger">Email not valid</span>
            )} */}
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} className="text-field" required />
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} className="text-field" required />
            <select value={gender} onChange={(e) => setGender(e.target.value)} className="text-field" required>
            <option value="">Select</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>
          <input type="text" value={location} onChange={(e) => setLocation(e.target.value)} className="text-field" required />
          <input type="number" value={age} onChange={(e) => setAge(e.target.value)} className="text-field" required />
          <input type="text" value={accountNumber} onChange={(e) => setAccountNumber(e.target.value)} className="text-field" required />


            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              name="password"
              // value={passwordCheck}
              // onChange={handlePasswordChange}
              required
              className="password-field"
              // ref={password}
            />
            {/* {!isPasswordValid(passwordCheck) && (
              <span className="badge rounded-pill text-bg-danger">Password not valid</span>
            )} */}
          </div>
          <button type="button" onClick={handleSubmit}>Login</button>
        </form>
      </div>
    </>
  );
}

export default FarmerRegistration;
{/* <div>
      <h2>Farmer Registration</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label>
          <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
        </div>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <div>
          <label>Gender:</label>
          <select value={gender} onChange={(e) => setGender(e.target.value)} required>
            <option value="">Select</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>
        </div>
        <div>
          <label>Location:</label>
          <input type="text" value={location} onChange={(e) => setLocation(e.target.value)} required />
        </div>
        <div>
          <label>Age:</label>
          <input type="number" value={age} onChange={(e) => setAge(e.target.value)} required />
        </div>
        <div>
          <label>Account Number:</label>
          <input type="text" value={accountNumber} onChange={(e) => setAccountNumber(e.target.value)} required />
        </div>
        <button type="submit">Register</button>
      </form>
    </div> */}