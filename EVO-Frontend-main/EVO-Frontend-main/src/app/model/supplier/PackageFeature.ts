import Supplier from "./Supplier";


export class PackageFeature {
  featureID: number;
  supplier: Supplier;
  featureName: string;
  featurePrice: number;

  constructor(featureID: number, supplier: any, featureName: string, featurePrice: number) {
    this.featureID = featureID;
    this.supplier = supplier;
    this.featureName = featureName;
    this.featurePrice = featurePrice;
  }
}
