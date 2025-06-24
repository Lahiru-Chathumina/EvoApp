import { SupplerRequestStatusType } from "../../../utils/SupplerRequestStatusType";
import { Customer } from "../customer/Customer";
import { PackageFeature } from "./PackageFeature";
import { ProfilePackage } from "./ProfilePackage";

export class SupplierRequest {
  id: number;
  requestDate: Date;
  dueDateTime: Date;
  requestStatus: SupplerRequestStatusType;
  customer: Customer;
  selectedPackage: ProfilePackage;
  extraFeatures: PackageFeature[];
  location: Location;

  constructor(
    id: number,
    requestDate: Date,
    dueDateTime: Date,
    requestStatus: SupplerRequestStatusType,
    customer: Customer,
    selectedPackage: ProfilePackage,
    extraFeatures: PackageFeature[],
    location: Location
  ) {
    this.id = id;
    this.requestDate = requestDate;
    this.dueDateTime = dueDateTime;
    this.requestStatus = requestStatus;
    this.customer = customer;
    this.selectedPackage = selectedPackage;
    this.extraFeatures = extraFeatures;
    this.location = location;
  }
}


