import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { useSelector } from 'react-redux';

const PrivateRoute = ({ component: Component, role, ...rest }) => {
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  const userRole = useSelector((state) => state.auth.role);

  return (
    <Route
      {...rest}
      render={(props) => {
        if (isAuthenticated && userRole === role) {
          return <Component {...props} />;
        } else {
          alert("You are not authorized to access this webpage. Please login to continue.");
          return (
            <Redirect
              to={{
                pathname: '/login',
                state: { from: props.location , userRole: userRole},
              }}
            />
          );
        }
      }}
    />
  );
};

export default PrivateRoute;
