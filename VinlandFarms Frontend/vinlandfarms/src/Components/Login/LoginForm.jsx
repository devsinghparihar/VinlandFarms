import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { loginSuccess} from '../../actions/authActions'
import { useHistory } from 'react-router-dom';
import axios from 'axios';


function LoginForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();
  const history = useHistory();

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleLogin = async () => {
    try {
        // console.log(email,password);
      const response = await axios.post('http://localhost:5006/public/signin', { 
        username:email, 
        password:password 
    });
   
      const { username, roles, accessToken } = response.data;
      const role = roles[0];
        console.log(response.data);
        console.log(response);
        console.log(accessToken);
        console.log(response.data.roles[0]);
      dispatch(loginSuccess(username, role, accessToken));

      // Redirect or perform other actions as needed after successful login
      if (role === 'ROLE_ADMIN') {
        history.push('/AdminDashboard');
      } else if (role === 'ROLE_FARMER') {
        history.push('/FarmerDashboard');
      } else if (role === 'ROLE_DEALER') {
        history.push('/DealerDashboard');
      } else {
        // Handle other roles or errors
      }

    } catch (error) {
        alert("Bad credentials")
      // Handle login error, display a message, or perform other actions
      console.error('Login failed:', error);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={handleEmailChange} />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={handlePasswordChange} />
        </div>
        <button type="button" onClick={handleLogin}>Login</button>
      </form>
    </div>
  );
}

export default LoginForm;
