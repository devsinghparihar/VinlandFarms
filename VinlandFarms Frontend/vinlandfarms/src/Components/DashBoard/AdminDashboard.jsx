import React from 'react';
import { useSelector } from 'react-redux';

function AdminDashboard() {
    const role = useSelector(state => state.auth.role);
    const token = useSelector(state => state.auth.token);
    const email = useSelector(state => state.auth.username);
  return (
    <div>
      <h1>Admin Dashboard</h1>
        {role}
        {email}

      {/* Add components or widgets specific to the Admin Dashboard */}
    </div>
  );
}

export default AdminDashboard;
