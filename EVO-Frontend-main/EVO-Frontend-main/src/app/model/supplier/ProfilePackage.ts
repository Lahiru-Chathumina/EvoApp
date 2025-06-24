import { PackageFeature } from "./PackageFeature";

export class ProfilePackage {
  packageId: number | null;
  description: string;
  packageName: string;
  price: number;
  status: string;
  features: PackageFeature[];

  constructor(
    packageId: number | null = null,
    description: string = '',
    packageName: string = '',
    price: number = 0.0,
    status: string = '',
    features: PackageFeature[] = []
  ) {
    this.packageId = packageId;
    this.description = description;
    this.packageName = packageName;
    this.price = price;
    this.status = status;
    this.features = features;
  }
}
