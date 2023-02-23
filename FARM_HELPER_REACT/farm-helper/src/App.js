import logo from './logo.svg';
import './App.css';
import { Login } from './components/Main/Login';
import { Registation } from './components/Main/Registration';
import { Farmer } from './components/Farmer/Farmer'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route } from "react-router-dom";
import { Routes, Navigate } from "react-router";
import { Orders } from './components/Farmer/Orders/Orders';
import { ChooseDriver } from './components/Farmer/ChooseDriver/ChooseDriver';
import { Farm } from './components/Farmer/Farm/Farm';
import { Reviews } from './components/Reviews';
import { Driver } from './components/Driver/Driver';
import { Car } from './components/Driver/Car';
import { DriveOrder } from './components/Driver/DriveOrder';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='/registration' element={<Registation />} />
          <Route path='/login' element={<Login />} />
          <Route path='/farmer/:login' element={<Farmer />} />
          <Route path='/orders' element={<Orders />} />
          <Route path='/choose_driver' element={<ChooseDriver />} />
          <Route path='/farmer' element={<Farm />} />
          <Route path='/reviews' element={<Reviews />} />
          <Route path='/driver/:login' element={<Driver />} />
          <Route path='/car' element={<Car />} />
          <Route path='/drive_order' element={<DriveOrder />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
