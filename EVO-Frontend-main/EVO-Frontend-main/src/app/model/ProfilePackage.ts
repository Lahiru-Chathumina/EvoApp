import Supplier from "./supplier";

export default class ProfilePackage {
    packageId?: number;
    packageName?: string;
    description?: string;
    price?: number;
    supplier?: Supplier;

    constructor(
        packageId?: number, 
        packageName?: string, 
        description?: string, 
        price?: number
    ) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.description = description;
        this.price = price;
    }
}