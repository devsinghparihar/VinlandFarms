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
import BuyCrops from './Components/DealerComponents/BuyCrops';
import TransactionList from './Components/AdminComponents/TransactionList';
import DealerTransactionList from './Components/DealerComponents/TransactionList';
import FarmerTransactionList from './Components/FarmerComponents/TransactionList';
import ProfileDetails from './Components/FarmerComponents/ProfileDetails';
import DealerProfile from './Components/DealerComponents/DealerProfile';
import FarmerPanel from './Components/AdminComponents/FarmerPanel';
import UpdateFarmer from './Components/AdminComponents/UpdateFarmer';
import DealerPanel from './Components/AdminComponents/DealerPanel';
import UpdateDealer from './Components/AdminComponents/UpdateDealer';
import Navbar from './Components/navbar/Navbar';
import PrivateRoute from './PrivateRoute'; // Import the PrivateRoute component

function App() {
  return (
    <Provider store={store}>
      <Router>
        <Navbar />
        <div className="App">
          <Switch>
            <Route path="/login" component={LoginForm} />
            <Route path="/farmer-registration" component={FarmerRegistration} />
            <Route path="/dealer-registration" component={DealerRegistration} />
            <Route path="/admin-registration"  component={AdminRegistration} />
            <PrivateRoute path="/AdminDashboard" role="ROLE_ADMIN" component={AdminDashboard} />
            <PrivateRoute path="/FarmerDashboard" role="ROLE_FARMER" component={FarmerDashboard} />
            <PrivateRoute path="/DealerDashboard" role="ROLE_DEALER" component={DealerDashboard} />
            <PrivateRoute path="/addCrop" role="ROLE_FARMER" component={AddCropForm} />
            <PrivateRoute path="/buyCrop" role="ROLE_DEALER" component={BuyCrops} />
            <PrivateRoute path="/allTransactions" role="ROLE_ADMIN" component={TransactionList} />
            <PrivateRoute path="/dealerTransactions" role="ROLE_DEALER" component={DealerTransactionList} />
            <PrivateRoute path="/farmerTransactions" role="ROLE_FARMER" component={FarmerTransactionList} />
            <PrivateRoute path="/farmerProfile" role="ROLE_FARMER" component={ProfileDetails} />
            <PrivateRoute path="/dealerProfile" role="ROLE_DEALER" component={DealerProfile} />
            <PrivateRoute path="/farmerPanel" role="ROLE_ADMIN" component={FarmerPanel} />
            <PrivateRoute path="/dealerPanel" role="ROLE_ADMIN" component={DealerPanel} />
            <PrivateRoute path='/updateFarmerDetails/:farmerId/:email' role="ROLE_ADMIN" component={UpdateFarmer} />
            <PrivateRoute path='/updateDealerDetails/:dealerId/:email' role="ROLE_ADMIN" component={UpdateDealer} />
          </Switch>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
