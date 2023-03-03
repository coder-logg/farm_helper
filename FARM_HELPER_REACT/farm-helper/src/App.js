import logo from './logo.svg';
import './App.css';
import { Login } from './components/Main/Login';
import { Registation } from './components/Main/Registration';
import { Farmer } from './components/Farmer/Farmer'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { Orders } from './components/Farmer/Orders/Orders';
import { ChooseDriver } from './components/Farmer/ChooseDriver/ChooseDriver';
import { Farm } from './components/Farmer/Farm/Farm';
import { Reviews } from './components/Reviews';
import { Driver } from './components/Driver/Driver';
import { Car } from './components/Driver/Car';
import { DriveOrder } from './components/Driver/DriveOrder';
import { Admin } from './components/Admin/Admin';
import { Arbitration } from './components/Admin/Arbitration/Arbitrarion';
import { AddToDB } from './components/Admin/AddToDB';
function App() {
  return (
    <div className='App'>
      <BrowserRouter>
        <Routes>
          <Route path='/registration' element={<Registation />} />
          <Route path='/login' element={<Login />} />
          <Route path='/arbitration/:login/' element={<Arbitration />} />
          <Route path='/orders/:login/' element={<Orders />} />
          <Route path='/choose_driver/:login/' element={<ChooseDriver />} />
          <Route path='/farm/:login/' element={<Farm />} />
          <Route path='/reviews/:login/' element={<Reviews />} />
          <Route path='/driver/:login/' element={<Driver />} />
          <Route path='/admin/:login' element={<Admin />} />
          <Route path='/add_to_db/:login' element={<AddToDB />} />
          <Route path='/car/:login/' element={<Car />} />
          <Route path='/drive_order/:login/' element={<DriveOrder />} />
          <Route path='/farmer/:login/' element={<Farmer />} />
          {/* <Route path="/farmer/:login/*" element={<PrivateRoute element={<Farmer />} />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}
function PrivateRoute({ element, ...rest }) {
  const isAuthenticated = localStorage.getItem('isAuthenticated');
  console.log(localStorage)
  return (
    <div>
      {isAuthenticated ? element : <Navigate to="/login" />}
    </div>
  );
}
export default App;
