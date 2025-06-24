import { SupplierType } from "../../../utils/SupplierType";

export interface New_Supplier {
  id: number;
  businessName: string;
  businessContactNumber: string;
  businessEmail: string;
  description: string;
  availability: boolean;
  category: SupplierType;
  imageUrl: string;
}