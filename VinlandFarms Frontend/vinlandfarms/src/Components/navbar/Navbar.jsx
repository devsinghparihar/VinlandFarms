import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory, useLocation } from 'react-router-dom';
import { logout } from '../../actions/authActions.js'; // Replace with your actual logout action import
import './Navbar.css'; // Import your CSS file for styling
import axios from 'axios';
import generateConfig from '../AuthConfig/AuthHeader.js';


function Navbar() {
    const history = useHistory();
    const dispatch = useDispatch();
    const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
    const role = useSelector((state) => state.auth.role);
    const email = useSelector((state) => state.auth.username);
    const id = useSelector((state) => state.auth.id);
    const token = useSelector((state) => state.auth.token);
    const location = useLocation();

    //add money 
    const [showModal, setShowModal] = useState(false);
    const [amount, setAmount] = useState('');

    const openModal = () => {
        setShowModal(true);
    };

    const closeModal = () => {
        setShowModal(false);
    };

    const handleAddMoney = async (e) => {
        e.preventDefault();
        try {
            

            const response = await  axios.put(`http://localhost:4865/dealer/addMoneyToWallet/${id}/${amount}`,null,generateConfig(token));
            alert(response.data);

        } catch (error) {
            console.log(error);
        }

        closeModal();
    };
    //add money complete

    //add requirement
    const [showReqModal, setShowReqModal] = useState(false);
    const [req, setReq] = useState('');

    const openReqModal = () => {
        setShowReqModal(true);
    };

    const closeReqModal = () => {
        setShowReqModal(false);
    };

    const handleAddReq = async (e) => {
        e.preventDefault();
        try {
            const header = generateConfig(token)
            console.log(amount, id, header, "sds") ;

            const response = await axios.post(`http://localhost:4865/dealer/addRequirement/${req}/${email}`,null,generateConfig(token));
            alert(req + " requirement added successfully to wishlist");

        } catch (error) {
            console.log(error);
        }

        closeModal();
    };
    


    //add req complete 

    const handleLogout = () => {
        // Dispatch the logout action to clear the Redux store
        dispatch(logout());

        // Redirect to the login page
        history.push('/login');
    };
    const handleLogin = () => {
    
        // Redirect to the login page
        history.push('/login');
    };
    const dealerHome = () => {
    
        // Redirect to the login page
        history.push('/dealerDashBoard');
    };
    const handleBack = () => {
        console.log(location)
        if (location.pathname == '/login') {
            dispatch(logout());
        }
        // Navigate back in the history
        history.goBack();
    };
    const handleAbout = () =>{
        history.push('/aboutus');
    }
    const handleContact = () =>{
        history.push('/contactus');
    }
    const handleHome = (home) =>{
        history.push(`/${home}`);
    }

    return (
        <nav className="navbar">
            <div className="navbar-logo">
            <img src="/images/logo.png" alt="" />
                
                
            </div>
            <ul className="navbar-links">
                {isAuthenticated && (
                    <div className="role-buttons">
                        {role === 'ROLE_ADMIN' && (
                            <div className="role-button">
                                <button onClick={() => handleHome('AdminDashboard')}>Home</button>
                                <button onClick={handleAbout}>About</button>
                                <button onClick={handleContact}>Contact Us</button>
                            </div>
                        )}
                        {role === 'ROLE_FARMER' && (
                            <div className="role-button">
                                <button onClick={() => handleHome('FarmerDashboard')}>Home</button>
                                <button onClick={handleAbout}>About</button>
                                <button onClick={handleContact}>Contact Us</button>
                            </div>
                        )}
                        {role === 'ROLE_DEALER' && (
                            <div className="role-button">
                                <button onClick={openModal} >Add Money</button>
                                {/* modal */}
                                {showModal && (
                                    <div className="modal" tabindex="-1" role="dialog" style={{ display: 'block' }}>
                                        <div className="modal-dialog" role="document">
                                            <div className="modal-content">
                                                <div className="modal-header">
                                                    <h5 className="modal-title">Add Money</h5>
                                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <form onSubmit={handleAddMoney}>
                                                    <div className="form-group" id='modalForm'>

                                                        <input
                                                            type="text"
                                                            placeholder='Enter Amount'
                                                            id='modalInput'
                                                            value={amount}
                                                            onChange={(e) => setAmount(e.target.value)}
                                                        />
                                                    </div>
                                                    <div className="modal-footer">
                                                        <button type="submit" className="btn btn-primary">Add Money</button>
                                                        <button type="button" onClick={closeModal} className="btn btn-secondary" data-dismiss="modal">Close</button>
                                                    </div>
                                                </form>

                                            </div>
                                        </div>
                                    </div>
                                )}

                                {/* modal */}

                                <button onClick={openReqModal}>Add Requirement</button>
                                {/* modal */}
                                {showReqModal && (
                                    <div className="modal" role="dialog" style={{ display: 'block' }}>
                                        <div className="modal-dialog" role="document">
                                            <div className="modal-content">
                                                <div className="modal-header">
                                                    <h5 className="modal-title">Add Crop Requirement</h5>
                                                    <button type="button" onClick={closeReqModal} className="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <form onSubmit={handleAddReq}>
                                                    <div className="form-group" id='modalForm'>

                                                        <input
                                                            type="text"
                                                            placeholder='Enter Crop Requirement'
                                                            id='modalInput'
                                                            value={req}
                                                            onChange={(e) => setReq(e.target.value)}
                                                        />
                                                    </div>
                                                    <div className="modal-footer">
                                                        <button type="submit" className="btn btn-primary">Add Req</button>
                                                        <button type="button" onClick={closeReqModal} className="btn btn-secondary" data-dismiss="modal">Close</button>
                                                    </div>
                                                </form>

                                            </div>
                                        </div>
                                    </div>
                                )}
                                <button onClick={() => handleHome('DealerDashboard')}>Home</button>
                                <button onClick={handleAbout}>About</button>
                                <button onClick={handleContact}>Contact Us</button>
                            </div>
                        )}
                    </div>
                )}
                <li>
                    {isAuthenticated && (
                        <div className="role-button ">
                            <button className="logout-button" onClick={handleLogout}>
                                Logout
                            </button>
                            <button className="back-button" onClick={handleBack}>
                                Back
                            </button>
                        </div>

                    )}

                </li>
                <li >
                    {!isAuthenticated && (
                       <div className="role-button">
                       <button className="logout-button" onClick={handleLogin}>
                           Login
                       </button>
                       
                       
                   </div>
                        
                    )}
                </li>
            </ul>
        </nav>
    );
}

export default Navbar;
