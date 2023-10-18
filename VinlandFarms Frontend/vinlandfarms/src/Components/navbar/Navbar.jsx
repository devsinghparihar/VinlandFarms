import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory, useLocation } from 'react-router-dom';
import { logout } from '../../actions/authActions.js'; // Replace with your actual logout action import
import './Navbar.css'; // Import your CSS file for styling


function Navbar() {
  const history = useHistory();
  const dispatch = useDispatch();
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  const role = useSelector((state) => state.auth.role);
  const location = useLocation();

  const handleLogout = () => {
    // Dispatch the logout action to clear the Redux store
    dispatch(logout());

    // Redirect to the login page
    history.push('/login');
  };
  const handleBack = () => {
    console.log(location)
    if( location.pathname == '/login'){
        dispatch(logout());
    }
    // Navigate back in the history
    history.goBack();
  };

  return (
    <nav className="navbar">
      <div className="navbar-logo">
        Vinland Farms {role}
      </div>
      <ul className="navbar-links">
        {isAuthenticated && (
          <div className="role-buttons">
            {role === 'ROLE_ADMIN' && (
              <div className="role-button">
                <button>Admin Button 1</button>
                <button>Admin Button 2</button>
                <button>Admin Button 3</button>
              </div>
            )}
            {role === 'ROLE_FARMER' && (
              <div className="role-button">
                <button>Farmer Button 1</button>
                <button>Farmer Button 2</button>
                <button>Farmer Button 3</button>
              </div>
            )}
            {role === 'ROLE_DEALER' && (
              <div className="role-button">
                <button >Add Money</button>
                
                <button>Dealer Button 2</button>
                <button>Dealer Button 3</button>
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
      </ul>
    </nav>
  );
}

export default Navbar;
