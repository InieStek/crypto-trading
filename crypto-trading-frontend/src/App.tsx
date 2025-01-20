import React from 'react';
import { Routes, Route } from 'react-router-dom';
import RegisterPage from './pages/RegisterPage';
import LoginPage from './pages/LoginPage'; // Import strony logowania

const App: React.FC = () => {
  return (
      <div>
        <h1>Welcome to Crypto Trading</h1>
        <Routes>
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />
        </Routes>
      </div>
  );
};

export default App;
