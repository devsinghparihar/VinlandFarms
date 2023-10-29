// import React, { useState } from 'react';
// import { useHistory } from 'react-router-dom';
// import axios from 'axios';
// function DealerRegistration() {
//     const history = useHistory();
//   const [name, setName] = useState('');
//   const [email, setEmail] = useState('');
//   const [password, setPassword] = useState('');
//   const [gender, setGender] = useState('');
//   const [location, setLocation] = useState('');
//   const [age, setAge] = useState(0);
//   const [accountNumber, setAccountNumber] = useState('');

   

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     const formData = {
//       name,
//       email,
//       password,
//       gender,
//       age,
//       accountNumber,
    
//     };

//     try {
        
        
//         const response = await axios.post('http://localhost:4865/public/registerDealer', formData);
  
//         console.log('Registration successful:', response.data);
//         alert("Registration succcessfull... Check your email for confirmation\nRedirecting to login page");
//         history.push('/login');
//       } catch (error) {
//         console.error('Registration failed:', error);
//       }
//     };

//     return (
//       <div className="registration-container">
//         <div className="registration-image">
//           <img src="/images/dealer.jpg" alt="Registration Image" className="registration-image" />
//         </div>
//         <div className="registration-form">
//           <h2 className="registration-heading">Dealer Registration</h2>
//           <form onSubmit={handleSubmit}>
//             <div className="form-group">
//               {/* <label className="form-label">Name:</label> */}
//               <input
//                 type="text"
//                 value={name}
//                 placeholder='Enter Name'
//                 onChange={(e) => setName(e.target.value)}
//                 required
//                 className="form-input"
//               />
//             </div>
//             <div className="form-group">
//               {/* <label className="form-label">Email:</label> */}
//               <input
//                 type="email"
//                 value={email}
//                 placeholder='Enter Email'
//                 onChange={(e) => setEmail(e.target.value)}
//                 required
//                 className="form-input"
//               />
//             </div>
//             <div className="form-group">
//               {/* <label className="form-label">Password:</label> */}
//               <input
//                 type="password"
//                 value={password}
//                 placeholder='Enter Password'
//                 onChange={(e) => setPassword(e.target.value)}
//                 required
//                 className="form-input"
//               />
//             </div>
//             <div className="form-group">
//               {/* <label className="form-label">Gender:</label> */}
//               <select
//                 value={gender}
//                 onChange={(e) => setGender(e.target.value)}
//                 required
//                 className="form-input"
                
//               >
//                 <option value="">Select Gender</option>
//                 <option value="Male">Male</option>
//                 <option value="Female">Female</option>
//                 <option value="Other">Other</option>
//               </select>
//             </div>
           
//             <div className="form-group">
//               {/* <label className="form-label">Age:</label> */}
//               <input
//                 type="number"
//                 value={age}
//                 onChange={(e) => setAge(e.target.value)}
//                 required
//                 placeholder='Enter Age'
//                 className="form-input"
//               />
//             </div>
//             <div className="form-group">
//               {/* <label className="form-label">Account Number:</label> */}
//               <input
//                 type="text"
//                 value={accountNumber}
//                 onChange={(e) => setAccountNumber(e.target.value)}
//                 required
//                 placeholder='Enter A/C NO'
//                 className="form-input"
//               />
//             </div>
//             <button type="submit" className="registration-button">
//               Register
//             </button>
//           </form>
//         </div>
//       </div>
//     );
//   }

// export default DealerRegistration;
import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import './FarmerRegistration.css'; // Import the CSS file for styling

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
      alert("Registration successful... Check your email for confirmation\nRedirecting to login page");
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
          </div>
          <div className={`form-group ${isEmailValid ? '' : 'invalid'}`}>
            <input
              type="text"
              value={email}
              placeholder='Enter Email'
              onChange={(e) => setEmail(e.target.value)}
              
              className="form-input"
            />
          </div>
          <div className={`form-group ${isPasswordValid ? '' : 'invalid'}`}>
            <input
              type="password"
              value={password}
              placeholder='Enter Password'
              onChange={(e) => setPassword(e.target.value)}
              
              className="form-input"
            />
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
          </div>
          <div className={`form-group ${isAgeValid ? '' : 'invalid'}`}>
            <input
              type="number"
              value={age}
              onChange={(e) => setAge(e.target.value)}
              
              placeholder='Enter Age'
              className="form-input"
            />
          </div>
          <div className={`form-group ${isAccountNumberValid ? '' : 'invalid'}`}>
            <input
              type="text"
              value={accountNumber}
              onChange={(e) => setAccountNumber(e.target.value)}
              
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

export default DealerRegistration;
