import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import './FarmerPanel.css';

const DealerPanel = () => {
    const [dealers, setDealers] = useState([]);
    const [viewActiveDealers, setViewActiveDealers] = useState(false);
    const [viewInactiveDealers, setViewInactiveDealers] = useState(false);
    const history = useHistory();
   

    useEffect(() => {
        
        fetchDealers();
    }, []);

    const fetchDealers = () => {
        axios.get('http://localhost:5002/dealer/getAllDealers')
            .then((response) => {
                setDealers(response.data);
                setViewActiveDealers(false);
                setViewInactiveDealers(false);
            })
            .catch((error) => {
                console.error('Error fetching all dealers:', error);
            });
    };

    const handleViewActiveDealers = () => {
        // Fetch the list of actDealersmers
        axios.get('http://localhost:5003/admin/activeDealers')
            .then((response) => {
                setDealers(response.data);
                setViewActiveDealers(true);
                setViewInactiveDealers(false);
            })
            .catch((error) => {
                console.error('Error fetching active Dealers:', error);
            });
    };

    const handleViewInactiveDealers = () => {
        // Fetch the list of inactive farmers
        axios.get('http://localhost:5003/admin/inactiveDealers')
            .then((response) => {
                setDealers(response.data);
                setViewActiveDealers(false);
                setViewInactiveDealers(true);
            })
            .catch((error) => {
                console.error('Error fetching inactive Dealers:', error);
            });
    };
    

    const handleUpdateDealer = (dealerId,email) => {
        // Redirect to a certain page with the farmerId as a path variable
        history.push(`/updateDealerDetails/${dealerId}/${email}`);
    };

    const handleDeleteDealer = (dealerId) => {
        // Make an API request to delete the farmer
        axios
            .delete(`http://localhost:5003/admin/deleteDealerById/${dealerId}`)
            .then((response) => {
                // If deletion is successful, remove the farmer tuple from the state
                setDealers((dealer) => dealer.filter((dealer) => dealer.dealerId !== dealerId));
            })
            .catch((error) => {
                console.error('Error deleting Dealer:', error);
            });
    };

    useEffect(() => {
        // Fetch the list of all farmers
        axios.get('http://localhost:5002/dealer/getAllDealers')
            .then((response) => {
                setDealers(response.data);
            })
            .catch((error) => {
                console.error('Error fetching dealers:', error);
            });
    }, []);

    

    return (
        <div className="admin-panel">
            <div className="left-section">
                <h2>Dealer Management</h2>
                <table className="farmer-table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>Age</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {dealers.map((dealer) => (
                            <tr key={dealer.dealerId}>
                                <td>{dealer.name}</td>
                                <td>{dealer.email}</td>
                                <td>{dealer.gender}</td>
                                <td>{dealer.age}</td>
                               
                                <td>
                                    <button id='updateBtn'
                                        className="btn btn-outline-success"
                                        onClick={() => handleUpdateDealer(dealer.dealerId, dealer.email)}
                                    >
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
                                            <path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z" />
                                        </svg>
                                    </button>
                                    <button
                                        className="btn btn-outline-danger"
                                        onClick={() => handleDeleteDealer(dealer.dealerId)}
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
                <div className="action-card active" onClick={handleViewActiveDealers}>
                    <img src="/images/inactive.jpg" alt="Active Farmers" className='statusImage' />
                    <h3>View Active Dealers</h3>
                    <p>View the list of active dealers</p>
                </div>
                <div className="action-card" onClick={handleViewInactiveDealers}>
                    <img src="/images/active.jpg" alt="Inactive Farmers" className='statusImage' />
                    <h3>View Inactive dealers</h3>
                    <p>View the list of inactive dealers</p>
                </div>
            </div>

        </div>
    );
};

export default DealerPanel;