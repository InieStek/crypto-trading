export interface User {
  id?: string;
  username: string;
  email: string;
  password: string;
  role?: 'USER' | 'ADMIN';
  emailVerified?: boolean;
}
