import logo from './logo.svg';
import './App.css';
import { Login } from './components/Main/Login';
import { Registation } from './components/Main/Registration';
import { Profile } from './components/Profile/Profile'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route } from "react-router-dom";
import { Routes, Switch } from "react-router";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Login />
        <Routes>
          <Route path='/registration' element={<Registation />} />
          <Route path='/login' element={<Login />} />
          <Route path='/profile' element={<Profile />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
