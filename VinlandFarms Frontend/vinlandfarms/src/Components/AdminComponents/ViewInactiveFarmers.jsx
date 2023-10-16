import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './FarmerList.css';

function InActiveFarmersList() {
  const [farmers, setFarmers] = useState([]);

  useEffect(() => {
    
    axios.get('http://localhost:5003/admin/inactiveFarmers')
      .then((response) => {
        setFarmers(response.data);
      })
      .catch((error) => {
        console.error('Error fetching farmers:', error);
      });
  }, []);

  const handleDeleteFarmer = (farmerId) => {
  
    axios.delete(`http://localhost:5003/admin/deleteFarmerById/${farmerId}`)
      .then(() => {
      
        setFarmers(farmers.filter((farmer) => farmer.farmerId !== farmerId));
      })
      .catch((error) => {
        console.error('Error deleting farmer:', error);
      });
  };

  return (
    <div className="container mt-4">
      <h1 className="mb-4">List of Farmers</h1>
      <table className="table table-hover">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Location</th>
            <th>Rating</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {farmers.map((farmer) => (
            <tr key={farmer.farmerId} className="table-row">
              <td>{farmer.name}</td>
              <td>{farmer.email}</td>
              <td>{farmer.gender}</td>
              <td>{farmer.location}</td>
              <td>{farmer.rating}</td>
              <td>Inactive</td>
              <td>
                <button
                  className="btn btn-danger"
                  onClick={() => handleDeleteFarmer(farmer.farmerId)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default InActiveFarmersList;
