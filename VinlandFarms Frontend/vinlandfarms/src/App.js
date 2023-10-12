import logo from './logo.svg';
import './App.css';
import React from 'react';
import { Provider } from 'react-redux';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import store from './store';
import LoginForm from './Components/Login/LoginForm';
import AdminDashboard from './Components/DashBoard/AdminDashboard';
import FarmerDashboard from './Components/DashBoard/FarmerDashboard';
import DealerDashboard from './Components/DashBoard/DealerDashboard';
import FarmerRegistration from './Components/register/FarmerRegister';
import DealerRegistration from './Components/register/DealerRegister';
import AdminRegistration from './Components/register/AdminRegister';
import AddCropForm from './Components/FarmerComponents/AddCropForm';
import UpdateFarmerDetails from './Components/FarmerComponents/UpdateFarmerDetails';
import BuyCrops from './Components/DealerComponents/BuyCrops';

function App() {
  return (
    <Provider store={store}>
    <Router>
      <div className="App">
        <Switch>
          <Route path="/AdminDashboard" component={AdminDashboard} />
          <Route path="/FarmerDashboard" component={FarmerDashboard} />
          <Route path="/DealerDashboard" component={DealerDashboard} />
          <Route path="/farmer-registration" component={FarmerRegistration} />
          <Route path="/dealer-registration" component={DealerRegistration} />
          <Route path="/admin-registration" component={AdminRegistration} />
          <Route path="/updateFarmerDetails" component={UpdateFarmerDetails} />
          <Route path="/buyCrop" component={BuyCrops} />
          <Route path="/addCrop" component={AddCropForm} />
          <Route path="/login" component={LoginForm} />
        </Switch>
      </div>
    </Router>
  </Provider>
  );
}

export default App;
