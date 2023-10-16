import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
function AdminRegistration() {
  const history = useHistory();  
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [gender, setGender] = useState('');
  const [role, setRole] = useState('ROLE_ADMIN'); // Set the role for admin

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = {
      name,
      email,
      password,
      gender
    };

    try {
        
        const response = await axios.post('http://localhost:5006/public/registerAdmin', formData);
  
        console.log('Registration successful:', response.data);
        alert("Registration succcessfull... Check your email for confirmation\nRedirecting to login page");
        history.push('/login');
      } catch (error) {
        console.error('Registration failed:', error);
      }
    };

  return (
    <div>
      <h2>Admin Registration</h2>
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
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default AdminRegistration;
