import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import './FarmerPanel.css';
import { useSelector } from 'react-redux';
import generateConfig from '../AuthConfig/AuthHeader';

const FarmerPanel = () => {
    const [farmers, setFarmers] = useState([]);
    const [viewActiveFarmers, setViewActiveFarmers] = useState(false);
    const [viewInactiveFarmers, setViewInactiveFarmers] = useState(false);
    const token = useSelector(state=> state.auth.token);
    const authHeader = generateConfig(token);
    const history = useHistory();

    useEffect(() => {
        // Fetch the list of all farmers initially
        fetchFarmers();
    }, []);

    const fetchFarmers = () => {
        axios.get('http://localhost:4865/farmer/getAll',authHeader)
            .then((response) => {
                setFarmers(response.data);
                setViewActiveFarmers(false);
                setViewInactiveFarmers(false);
            })
            .catch((error) => {
                console.error('Error fetching all farmers:', error);
            });
    };

    const handleViewActiveFarmers = () => {
        // Fetch the list of active farmers
        axios.get('http://localhost:4865/admin/activeFarmers',authHeader)
            .then((response) => {
                setFarmers(response.data);
                setViewActiveFarmers(true);
                setViewInactiveFarmers(false);
            })
            .catch((error) => {
                console.error('Error fetching active farmers:', error);
            });
    };

    const handleViewInactiveFarmers = () => {
        // Fetch the list of inactive farmers
        axios.get('http://localhost:4865/admin/inactiveFarmers',authHeader)
            .then((response) => {
                setFarmers(response.data);
                setViewActiveFarmers(false);
                setViewInactiveFarmers(true);
            })
            .catch((error) => {
                console.error('Error fetching inactive farmers:', error);
            });
    };
    

    const handleUpdateFarmer = (farmerId,email) => {
        // Redirect to a certain page with the farmerId as a path variable
        history.push(`/updateFarmerDetails/${farmerId}/${email}`);
    };

    const handleDeleteFarmer = (farmerId) => {
        // Make an API request to delete the farmer
        axios
            .delete(`http://localhost:4865/admin/deleteFarmerById/${farmerId}`,authHeader)
            .then((response) => {
                // If deletion is successful, remove the farmer tuple from the state
                alert("Farmer Deleted")
                setFarmers((farmers) => farmers.filter((farmer) => farmer.farmerId !== farmerId));
            })
            .catch((error) => {
                console.error('Error deleting farmer:', error);
            });
    };

    useEffect(() => {
        // Fetch the list of all farmers
        axios.get('http://localhost:4865/farmer/getAll',authHeader)
            .then((response) => {
                setFarmers(response.data);
            })
            .catch((error) => {
                console.error('Error fetching farmers:', error);
            });
    }, []);

    const getStarRating = (rating) => '★'.repeat(Math.round(rating));

    return (
        <div className="admin-panel">
            <div className="left-section">
                <h2>Farmer Management</h2>
                <table className="farmer-table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>Age</th>
                            <th>Rating</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {farmers.map((farmer) => (
                            <tr key={farmer.farmerId}>
                                <td>{farmer.name}</td>
                                <td>{farmer.email}</td>
                                <td>{farmer.gender}</td>
                                <td>{farmer.age}</td>
                                <td className="stars">{getStarRating(farmer.rating)}</td>
                                <td>
                                    <button id='updateBtn'
                                        className="btn btn-outline-success"
                                        onClick={() => handleUpdateFarmer(farmer.farmerId, farmer.email)}
                                    >
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
                                            <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z" />
                                        </svg>
                                    </button>
                                    <button
                                        className="btn btn-outline-danger"
                                        onClick={() => handleDeleteFarmer(farmer.farmerId)}
                                    >
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-x" viewBox="0 0 16 16">
                                            <path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm.256 7a4.474 4.474 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10c.26 0 .507.009.74.025.226-.341.496-.65.804-.918C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4s1 1 1 1h5.256Z" />
                                            <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm-.646-4.854.646.647.646-.647a.5.5 0 0 1 .708.708l-.647.646.647.646a.5.5 0 0 1-.708.708l-.646-.647-.646.647a.5.5 0 0 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 .708-.708Z" />
                                        </svg>
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>


            <div className="right-section">
                <div className="action-card active" onClick={handleViewActiveFarmers}>
                    <img src="/images/inactive.gif" alt="Active Farmers" className='statusImage' />
                    <h3>View Active Farmers</h3>
                    <p>View the list of active farmers</p>
                </div>
                <div className="action-card" onClick={handleViewInactiveFarmers}>
                    <img src="/images/active.gif" alt="Inactive Farmers" className='statusImage' />
                    <h3>View Inactive Farmers</h3>
                    <p>View the list of inactive farmers</p>
                </div>
            </div>

        </div>
    );
};

export default FarmerPanel;