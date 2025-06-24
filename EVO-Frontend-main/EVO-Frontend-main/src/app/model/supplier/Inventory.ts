import { SupplierCategoryType } from "../../../utils/SupplierCategoryType";

export class Inventory {
  inventoryID: number;
  imageURL: string;
  description: string;
  supplierCategory: SupplierCategoryType;

  constructor(
    inventoryID: number,
    imageURL: string,
    description: string,
    supplierCategory: SupplierCategoryType
  ) {
    this.inventoryID = inventoryID;
    this.imageURL = imageURL;
    this.description = description;
    this.supplierCategory = supplierCategory;
  }
}

