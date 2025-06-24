import { Customer } from "../customer/Customer";
import { ProfilePreviousWorkImage } from "./ProfilePreviousWorkImage";

export class ProfilePreviousWork {
  previousWorkID: number;
  title: string;
  description: string;
  completionDate: Date;
  customer: Customer | null;
  images: ProfilePreviousWorkImage[];

  constructor(
    previousWorkID: number,
    title: string,
    description: string,
    completionDate: Date,
    customer: Customer | null,
    images: ProfilePreviousWorkImage[]
  ) {
    this.previousWorkID = previousWorkID;
    this.title = title;
    this.description = description;
    this.completionDate = completionDate;
    this.customer = customer;
    this.images = images;
  }
}
