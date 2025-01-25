import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import RegisterPage from './pages/RegisterPage';
import LoginPage from './pages/LoginPage'; // Import strony logowania

const App: React.FC = () => {
    return (
        <div className="min-h-screen flex flex-col bg-gray-50 relative">
            {/* Nagłówek "Welcome to Crypto Trading" na samej górze */}
            <h1 className="text-4xl font-bold text-center py-6">Welcome to Crypto Trading</h1>

            {/* Przycisk logowania i rejestracji w prawym górnym rogu */}
            <div className="absolute top-6 right-6 space-x-4">
                <Link to="/login">
                    <button className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-500 focus:outline-none">
                        Login
                    </button>
                </Link>
                <Link to="/register">
                    <button className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-500 focus:outline-none">
                        Register
                    </button>
                </Link>
            </div>

            {/* Zawartość strony */}
            <Routes>
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/login" element={<LoginPage />} />
            </Routes>
        </div>
    );
};

export default App;
