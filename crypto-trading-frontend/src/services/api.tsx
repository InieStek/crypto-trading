import axios, { AxiosResponse } from 'axios';
import { User } from '../types/User';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Typowanie odpowiedzi API
export const createUser = (userData: User): Promise<AxiosResponse<User>> =>
    api.post('/users', userData);

export const getUser = (id: string): Promise<AxiosResponse<User>> =>
    api.get(`/users/${id}`);

export const updateUser = (id: string, userData: User): Promise<AxiosResponse<void>> =>
    api.put(`/users/${id}`, userData);

export const deleteUser = (id: string): Promise<AxiosResponse<void>> =>
    api.delete(`/users/${id}`);

export default api;
