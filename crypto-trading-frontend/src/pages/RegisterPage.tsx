import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Importuj useNavigate
import { User } from '../types/User'; // Importuj interfejs User

const RegisterPage: React.FC = () => {
  const navigate = useNavigate(); // Hook do nawigacji
  const [formData, setFormData] = useState<User>({
    username: '',
    email: '',
    password: '',
    role: 'USER', // Domyślnie ustawiamy rolę na USER
    emailVerified: false, // Domyślnie email nie jest zweryfikowany
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8083/api/users', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
      });
      if (response.ok) {
        console.log('User registered successfully!');
        navigate('/'); // Przekierowanie na stronę główną
      } else {
        console.error('Failed to register user.');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
      <div className="flex items-center justify-center min-h-screen bg-gray-100">
        <div className="w-full max-w-md p-6 bg-white rounded-lg shadow-md">
          <h2 className="text-xl font-semibold text-center text-gray-700 mb-4">
            Register <span className="text-purple-600">your account</span>
          </h2>
          <form onSubmit={handleSubmit} className="space-y-4">
            {/* Username */}
            <div>
              <label className="block text-sm font-medium text-gray-600">Username</label>
              <input
                  type="text"
                  name="username"
                  value={formData.username}
                  onChange={handleChange}
                  className="w-full px-4 py-2 mt-1 text-gray-700 bg-gray-100 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
                  placeholder="Enter your username"
                  required
              />
            </div>
            {/* Email */}
            <div>
              <label className="block text-sm font-medium text-gray-600">Email</label>
              <input
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  className="w-full px-4 py-2 mt-1 text-gray-700 bg-gray-100 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
                  placeholder="Enter your email"
                  required
              />
            </div>
            {/* Password */}
            <div>
              <label className="block text-sm font-medium text-gray-600">Password</label>
              <input
                  type="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  className="w-full px-4 py-2 mt-1 text-gray-700 bg-gray-100 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-600"
                  placeholder="Enter your password"
                  required
              />
            </div>
            {/* Submit */}
            <button
                type="submit"
                className="w-full px-4 py-2 mt-4 text-white bg-purple-600 rounded-lg hover:bg-purple-500 focus:outline-none focus:ring-2 focus:ring-purple-600"
            >
              REGISTER
            </button>
          </form>
        </div>
      </div>
  );
};

export default RegisterPage;
