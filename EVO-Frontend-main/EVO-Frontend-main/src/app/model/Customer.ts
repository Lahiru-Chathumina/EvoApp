export interface Customer {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  contactNumber: string;
  profileImageUrl: string;
  address: string;
}

export interface CustomerLogin{
  email: string;
  password: string;
}