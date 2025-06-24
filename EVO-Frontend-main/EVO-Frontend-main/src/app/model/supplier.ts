export default class OLD_Supplier {
    supplierId: number;
    supplierName: string;
    userType: string;
    password: string;
    email: string;
    registeredDate: Date;
    businessName: string;
    businessDescription: string;
    mobileNumber: string;
    website: string;
    brDocument: string;   
   // completedOrders: number  
  
    constructor(supplierId: number, supplierName: string, userType: string, password: string, email: string, registeredDate: Date, businessName: string, businessDescription: string, mobileNumber: string, website: string, brDocument: string) {

        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.userType = userType;
        this.password = password;
        this.email = email;
        this.registeredDate = registeredDate;
        this.businessName = businessName;
        this.businessDescription = businessDescription;
        this.mobileNumber = mobileNumber;
        this.website = website;
        this.brDocument = brDocument;
         
    }
}
