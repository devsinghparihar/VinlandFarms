import React, { useRef, useState } from 'react';
import { useDispatch } from 'react-redux';
import { loginSuccess } from '../../actions/authActions';
import { useHistory, useLocation, Link } from 'react-router-dom';
import axios from 'axios';
import './login.css';



function LoginForm() {
  const email = useRef();
  const password = useRef();
  const [passwordCheck, setPasswordCheck] = useState('');
  const [emailCheck, setEmailCheck] = useState('');
  const dispatch = useDispatch();
  const history = useHistory();
  const location = useLocation();


  const isPasswordValid = (password) => {
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    // return passwordRegex.test(password);
    return true;
  };



  const isEmailValid = (email) => {
    const emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return emailRegex.test(email);
  };



  const handlePasswordChange = (event) => {
    setPasswordCheck(event.target.value);
  };



  const handleEmailChange = (event) => {
    setEmailCheck(event.target.value);
  };



  const handleLogin = async () => {
    console.log("inside function");
    const userEmail = email.current.value; // Access the email ref's value
    const userPassword = password.current.value; // Access the password ref's value



    try {
      // console.log(userEmail, userPassword);
      const response = await axios.post('http://localhost:4865/public/signin', {
        username: userEmail,
        password: userPassword,
      });


      console.log(response.data)
      const { username, roles, token, id } = response.data;
      const role = roles[0];
      dispatch(loginSuccess(username, role, token, id));

      if (location.state && location.state.from && location.state.userRole == role) {
        history.replace(location.state.from);
      } else {
        if (role === 'ROLE_ADMIN') {
          history.push('/AdminDashboard');
        } else if (role === 'ROLE_FARMER') {
          history.push('/FarmerDashboard');
        } else if (role === 'ROLE_DEALER') {
          history.push('/DealerDashboard');
        } else {
          // Handle other roles or errors
        }
       
      }

      
    } catch (error) {
      alert("Bad credentials");
      console.error('Login failed:', error);
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
          <div className="inputFields">
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              name="email"
              value={emailCheck}
              onChange={handleEmailChange}
              required
              className="text-field"
              ref={email}
            />
            {!isEmailValid(emailCheck) && (
              <span className="badge rounded-pill text-bg-danger">Email not valid</span>
            )}



            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              name="password"
              value={passwordCheck}
              onChange={handlePasswordChange}
              required
              className="password-field"
              ref={password}
            />
            {!isPasswordValid(passwordCheck) && (
              <span className="badge rounded-pill text-bg-danger">Password not valid</span>
            )}
          </div>
          <button type="button" onClick={handleLogin}>Login</button>
          <p>Forgot Password ?  <Link to="/forgotPassword">Click Here</Link></p>
        </form>
      </div>
      
      <p style={{ marginTop: "10px" , marginLeft:"30em" }}>Don't have an account ? Sign Up as <Link to="/farmer-registration">Farmer</Link>,<Link to="/dealer-registration">Dealer</Link></p>
    </>
  );
}



export default LoginForm;