import Customer from "./Customer";
import ProfilePackage from "./ProfilePackage";
import Supplier from "./supplier";

export default class SupplierRequest {
    id?: number;
    supplier?: Supplier;
    customer?: Customer;
    packages?: ProfilePackage;
    requestStatus?: String;
    requestDate?: Date;
    dueDateTime?: Date;

    constructor(
        supplier?: Supplier, 
        requestStatus?: String
    ) {
        this.supplier = supplier;
        this.requestStatus = requestStatus;
    }
}

