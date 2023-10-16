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
import InActiveFarmersList from './Components/AdminComponents/ViewInactiveFarmers';
import TransactionList from './Components/AdminComponents/TransactionList';
import DealerTransactionList from './Components/DealerComponents/TransactionList';
import FarmerTransactionList from './Components/FarmerComponents/TransactionList';
import ProfileDetails from './Components/FarmerComponents/ProfileDetails';
import DealerProfile from './Components/DealerComponents/DealerProfile';
import FarmerPanel from './Components/AdminComponents/FarmerPanel';
import UpdateFarmer from './Components/AdminComponents/UpdateFarmer';

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
          {/* <Route path="/updateFarmerDetails" component={UpdateFarmerDetails} /> */}
          <Route path="/buyCrop" component={BuyCrops} />
          <Route path="/addCrop" component={AddCropForm} />
          <Route path="/inActiveFarmers" component={InActiveFarmersList} />
          <Route path="/allTransactions" component={TransactionList} />
          <Route path="/dealerTransactions" component={DealerTransactionList} />
          <Route path="/farmerTransactions" component={FarmerTransactionList} />
          <Route path="/farmerProfile" component={ProfileDetails} />
          <Route path="/dealerProfile" component={DealerProfile} />
          <Route path="/farmerPanel" component={FarmerPanel} />
          <Route path='/updateFarmerDetails/:farmerId/:email' component={UpdateFarmer}></Route> 
          
          <Route path="/login" component={LoginForm} />
        </Switch>
      </div>
    </Router>
  </Provider>
  );
}

export default App;
