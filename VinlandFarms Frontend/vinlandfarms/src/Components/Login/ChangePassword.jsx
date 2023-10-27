import React, { useRef, useState } from 'react';
import { useDispatch } from 'react-redux';
import { loginSuccess } from '../../actions/authActions';
import { useHistory, useLocation, Link } from 'react-router-dom';
import axios from 'axios';
import './login.css';



function ChangePassword() {



  const [email, setEmail] = useState('');
  const [otp, setOtp] = useState('');
  const [res, setRes] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmNewPassword, setConfirmNewPassword] = useState('');
  const [isOtpSent, setIsOtpSent] = useState(false);
  const [isPasswordChanged, setIsPasswordChanged] = useState(false);


  const handleSendOtp = async () => {
    try {
      await axios.get(`http://localhost:5006/public/sendOTP/${email}`);
      console.log("clicked")
      setIsOtpSent(true);
    } catch (error) {
      // Handle error, e.g., show an error message
      console.error('Error sending OTP:', error);
    }
  };

  const handleChangePassword = async () => {
    // Perform frontend validation, e.g., check if passwords match
    if (newPassword !== confirmNewPassword) {
      // Handle password mismatch error
      alert("Password mismatch");
      return;
    }

    const formData = {
      email,
      otp,
      newPassword,
    };

    try {
      const response = await axios.post('http://localhost:5006/public/changeFarmerPassword', formData);

      console.log(response);
      setRes(response.data);
      setIsPasswordChanged(true);
    } catch (error) {
      // Handle error, e.g., show an error message
      console.error('Error changing password:', error);
    }
  };



  return (
    <>
      <div className="section log">
        <div className="logImg">
          <img src="/images/admin.jpg" alt="" />
        </div>
        
        {isOtpSent ? (
          <div>
            <form action="" className="form-control" >
            <h1 className="login">Welcome to VinlandFarms</h1>
              <div className="inputFields">

                
                
                
                <label htmlFor="email">Email</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                  className="text-field"

                />

                <label htmlFor="otp">OTP</label>
                <input
                  type="text"
                  id="otp"
                  name="otp"
                  placeholder="OTP"
                  value={otp}
                  onChange={(e) => setOtp(e.target.value)}
                  required
                  className="text-field"

                />
                <label htmlFor="password">New Password</label>
                <input
                  type="password"
                  placeholder="New Password"
                  value={newPassword}
                  onChange={(e) => setNewPassword(e.target.value)}
                  className="password-field"
                />
                <label htmlFor="confirmPassword">Confirm password</label>
                <input
                  type="password"
                  placeholder="Confirm New Password"
                  value={confirmNewPassword}
                  onChange={(e) => setConfirmNewPassword(e.target.value)}
                  className="password-field"
                />
              </div>
              <button onClick={handleChangePassword}>Change Password</button>
              {isPasswordChanged && <p>{res}</p>}
            </form>
          </div>
        ) : (
          <div className='otpBox'>
          <form action="" className="form-control" >
          <h1 className="login">Welcome to VinlandFarms</h1>
            <div className="inputFields">
            
              <label htmlFor="otp"> Enter Email</label>
              <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="text-field"
                
              />  
            </div>
            <button onClick={handleSendOtp}>Send OTP </button>
            
          </form>
        </div>
         
        )}

      </div>

      <p style={{ marginTop: "10px", marginLeft: "30em" }}>Don't have an account ? Sign Up as <Link to="/farmer-registration">Farmer</Link>,<Link to="/dealer-registration">Dealer</Link></p>
    </>
  );
}



export default ChangePassword;
