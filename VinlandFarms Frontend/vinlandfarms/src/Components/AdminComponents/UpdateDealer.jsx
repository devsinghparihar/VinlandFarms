import React, { useState, useEffect } from 'react';
import '../FarmerComponents/ProfileDetails.css'; // Make sure the CSS file name matches the component name
import { useSelector } from 'react-redux';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import generateConfig from '../AuthConfig/AuthHeader';

const UpdateDealer = () => {
    const [profileData, setProfileData] = useState(null);
    const location = useLocation();
    const id = location.pathname.split("/")[2];
    const email = location.pathname.split("/")[3];
    const jwtToken = useSelector((state) => state.auth.token);
    const authHeader = generateConfig(jwtToken);
    const [formData, setFormData] = useState({
        name: '',
        gender: '',
        age: 0,
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {

            console.log(formData)
            const response = await axios.put(
                `http://localhost:4865/admin/updateDealer/${email}`,
                formData,authHeader

            );

            console.log('dealer details updated:', response.data);
        } catch (error) {
            console.error('Error updating dealer details:', error);
            alert("an error occured")
        }
    };


    useEffect(() => {
        fetch(`http://localhost:4865/dealer/findDealerById/${id}`,authHeader)
            .then((response) => response.json())
            .then((data) => setProfileData(data))
            .catch((error) => console.error('Error fetching data:', error));
    }, [id]);





    return (
        <div className="profileContainer">
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
                                    <h4 className="text-right">Profile Details</h4>
                                </div>
                                <div className="row mt-2">
                                    <div className="col-md-6">
                                        <label className="labels-profileDetails">Name</label>
                                    </div>
                                    <div className="col-md-6">
                                        <input
                                            type="text"
                                            className="form-control-profileDetails"
                                            placeholder={profileData.name}
                                            name="name"
                                            value={formData.name}
                                            onChange={handleChange}

                                        />
                                    </div>
                                </div>
                                <div className="row mt-2">
                                    <div className="col-md-6">
                                        <label className="labels-profileDetails">Gender</label>
                                    </div>
                                    <div className="col-md-6">
                                        <select
                                            className="form-control-profileDetails"
                                            name="gender"
                                            value={formData.gender}
                                            onChange={handleChange}
                                        >
                                            <option value="">Select Gender</option>
                                            <option value="Male">Male</option>
                                            <option value="Female">Female</option>
                                            <option value="Other">Other</option>
                                        </select>
                                    </div>
                                </div>

                                {/* <div className="row mt-2">
                  <div className="col-md-6">
                    <label className="labels-profileDetails">Location</label>
                  </div>
                  <div className="col-md-6">
                    <input
                      type="text"
                      className="form-control-profileDetails"
                      name="location"
                      placeholder={profileData.location}
                      value={formData.location}
                      onChange={handleChange} 
                    />
                  </div>

                </div> */}

                                <div className="row mt-2">
                                    <div className="col-md-6">
                                        <label className="labels-profileDetails">Age</label>
                                    </div>
                                    <div className="col-md-6">
                                        <input
                                            type="text"
                                            className="form-control-profileDetails"
                                            placeholder={profileData.age}
                                            name="age"
                                            value={formData.age}
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
};

export default UpdateDealer;
