import React, { useState } from 'react';
import axios from 'axios';

function ForgotPassword() {
  const [email, setEmail] = useState('');
  const [otp, setOtp] = useState('');
  const [res, setRes] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmNewPassword, setConfirmNewPassword] = useState('');
  const [isOtpSent, setIsOtpSent] = useState(false);
  const [isPasswordChanged, setIsPasswordChanged] = useState(false);

  const handleSendOtp = () => {
    try {
       axios.get(`http://localhost:5006/public/sendOTP/${email}`);
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
    <div>
      <h2>Forgot Password</h2>
      {isOtpSent ? (
        <div>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="text"
            placeholder="OTP"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
          />
          <input
            type="password"
            placeholder="New Password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
          />
          <input
            type="password"
            placeholder="Confirm New Password"
            value={confirmNewPassword}
            onChange={(e) => setConfirmNewPassword(e.target.value)}
          />
          <button onClick={handleChangePassword}>Change Password</button>
          {isPasswordChanged && <p>{res}</p>}
        </div>
      ) : (
        <div>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <button onClick={handleSendOtp}>Send OTP</button>
        </div>
      )}
    </div>
  );
}

export default ForgotPassword;
