export class Customer {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  contactNumber: string;

  constructor(id: number, name: string, email: string, contactNumber: string) {
    this.id = id;
    this.firstName = name.split(' ')[0];
    this.lastName = name.split(' ').slice(1).join(' ');
    this.email = email;
    this.contactNumber = contactNumber;
  }
}
