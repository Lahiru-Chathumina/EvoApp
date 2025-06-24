import { Admin } from "../admin/Admin";
import Supplier from "../supplier/Supplier";
import { Customer } from "./Customer";
import { UserReport } from "./UserReport";

export class User {
  userId: number;
  firstName: string;
  lastName: string;
  password: string;
  email: string;
  registeredDate: string;
  userType: string;
  contactNumber: string;
  address: string;
  city: string;
  report?: UserReport;
  supplier?: Supplier;
  admin?: Admin;
  customer?: Customer;

  constructor(
    userId: number,
    firstName: string,
    lastName: string,
    password: string,
    email: string,
    registeredDate: string,
    userType: string,
    contactNumber: string,
    address: string,
    city: string,
    report?: UserReport,
    supplier?: Supplier,
    admin?: Admin,
    customer?: Customer
  ) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.email = email;
    this.registeredDate = registeredDate;
    this.userType = userType;
    this.contactNumber = contactNumber;
    this.address = address;
    this.city = city;
    this.report = report;
    this.supplier = supplier;
    this.admin = admin;
    this.customer = customer;
  }
}
