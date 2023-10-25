import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import './ProfileDetails'
import generateConfig from '../AuthConfig/AuthHeader';


function AddCropForm() {
  const [profileData, setProfileData] = useState(null);
  const id = useSelector((state) => state.auth.id);
  const email = useSelector((state) => state.auth.username);
  const jwtToken = useSelector((state) => state.auth.token);
  const history = useHistory();
  const [formData, setFormData] = useState({
    cropType: '',
    cropPrice: 0,
    location: '',
    cropQuantity: 0,
  });
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  useEffect(() => {
    fetch(`http://localhost:4865/farmer/findFarmerById/${id}`,generateConfig(jwtToken))
      .then((response) => response.json())
      .then((data) => setProfileData(data))
      .catch((error) => console.error('Error fetching data:', error));
  }, [id]);
    
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        const api = `http://localhost:4865/farmer/addCrop/${email}`;
        console.log(formData);
        const response = await axios.post(api,formData,generateConfig(jwtToken) );
        console.log('Crop posted successfully:', response.data);
        alert("crop posted successfully");
        history.push('/FarmerDashboard');
      } catch (error) {
        // Handle registration error, e.g., display an error message or handle server-side validation errors
        console.error('Action failed:', error);
      }

  };
  return ( <div className="profileContainer">
  <div className="container rounded bg-white mt-5 mb-5" id='box'>
    {profileData ? (
      <div className="row">
        <div className="col-md-3 border-right">
          <div className="d-flex flex-column align-items-center text-center p-3 py-5">
            <img
              className="rounded-circle mt-5"
              width="150px"
              src="https://thispersondoesnotexist.com/"
              alt="Profile"
            />
            <span className="font-weight-bold">{profileData.name}</span>
            <span className="text-black-50">{profileData.email}</span>
          
          </div>
        </div>
        <form onSubmit={handleSubmit} className="col-md-5 border-right">


          <div className="p-3 py-5">
            <div className="d-flex justify-content-between align-items-center mb-3">
              <h4 className="text-right">Add Crop</h4>
            </div>
            <div className="row mt-2">
              <div className="col-md-6">
                <label className="labels-profileDetails">Crop Type:</label>
              </div>
              <div className="col-md-6">
                <input
                  type="text"
                  className="form-control-profileDetails"
                  placeholder="Enter Crop Name"
                  name="cropType"
                  value={formData.cropType}
                  onChange={handleChange}

                />
              </div>
            </div>

            <div className="row mt-2">
              <div className="col-md-6">
                <label className="labels-profileDetails">Price/kg:</label>
              </div>
              <div className="col-md-6">
                <input
                  type="text"
                  className="form-control-profileDetails"
                  placeholder="Crop Price"
                  name="cropPrice"
                  value={formData.cropPrice}
                  onChange={handleChange}

                />
              </div>
            </div>
            

            <div className="row mt-2">
              <div className="col-md-6">
                <label className="labels-profileDetails">Location</label>
              </div>
              <div className="col-md-6">
                <input
                  type="text"
                  className="form-control-profileDetails"
                  name="location"
                  placeholder="Enter Location"
                  value={formData.location}
                  onChange={handleChange}
                />
              </div>

            </div>

            <div className="row mt-2">
              <div className="col-md-6">
                <label className="labels-profileDetails">Crop Quantity</label>
              </div>
              <div className="col-md-6">
                <input
                  type="text"
                  className="form-control-profileDetails"
                  placeholder="Enter Quantity"
                  name="cropQuantity"
                  value={formData.cropQuantity}
                  onChange={handleChange}
                />
              </div>

            </div>
            <button type="submit" className="btn btn-outline-danger">
              Update Details
            </button>
            {/* Add more fields here for the relevant data */}
          </div>

        </form>
        <div className="col-md-4">
          <div className="p-3 py-5">
            <div className="d-flex justify-content-between align-items-center experience-profileDetails">
              <span>Account Details</span>

            </div>
            <br />
            <div className="col-md-12">
              <label className="labels-profileDetails">Account Number</label>
              <div>{profileData.accountNumber}</div>

            </div>
            <div className="col-md-12">
              <label className="labels-profileDetails">Account Balance</label>
              <div>{profileData.accountBalance}</div>

            </div>

          </div>
        </div>
      </div>
    ) : (

      <div>
        <div class="spinner-border m-5" role="status">

          <span class="visually-hidden">Loading...</span>
        </div>
      </div>
    )}
  </div>
</div>
);

    
}

export default AddCropForm;

// <div>
//       <h2>Add Crop {userEmail}</h2>
//       <form onSubmit={handleSubmit}>
//         <div className="form-group1"> {/* Unique class */}
//           <label>Crop Type:</label>
//           <input
//             type="text"
//             className="crop-type-input"
//             value={cropType}
//             onChange={(e) => setCropType(e.target.value)}
//             required
//           />
//         </div>
//         <div className="form-group2"> {/* Unique class */}
//           <label>Crop Price:</label>
//           <input
//             type="number"
//             className="crop-price-input"
//             value={cropPrice}
//             onChange={(e) => setCropPrice(parseInt(e.target.value))}
//             required
//           />
//         </div>
//         <div className="form-group3"> {/* Unique class */}
//           <label>Crop Quantity:</label>
//           <input
//             type="number"
//             className="crop-quantity-input"
//             value={cropQuantity}
//             onChange={(e) => setCropQuantity(parseInt(e.target.value))}
//             required
//           />
//         </div>
//         <div className="form-group4"> {/* Unique class */}
//           <label>Location:</label>
//           <input
//             type="text"
//             className="location-input"
//             value={location}
//             onChange={(e) => setLocation(e.target.value)}
//             required
//           />
//         </div>
//         <button type="submit" className="btn btn-primary">
//           Add Crop
//         </button>
//       </form>
//     </div>
//   );