import { SupplierCategoryType } from '../../../utils/SupplierCategoryType';
import { BeautySaloon } from './BeautySaloon';
import { Catering } from './Catering';
import { Inventory } from './Inventory';
import { Music } from './Music';
import { ProfileImage } from './ProfileImage';
import { ProfilePackage } from './ProfilePackage';
import { ProfilePreviousWork } from './ProfilePreviousWork';
import { SupplierRequest } from './SupplierRequest';
import { Venue } from './Venue';

export default class Supplier {
  id?: number;
  businessName: string;
  businessContactNumber?: string;
  businessEmail?: string;
  description: string;
  availability?: boolean;
  location: Location;
  profileImage?: string;
  category: SupplierCategoryType;
  beautySaloon?: BeautySaloon;
  catering?: Catering;
  venue?: Venue;
  music?: Music;
  requests?: SupplierRequest[];
  packages?: ProfilePackage[];
  previousWorks?: ProfilePreviousWork[];
  images?: ProfileImage[];
  inventories?: Inventory[];

  constructor(
    businessName: string,
    description: string,
    location: Location,
    category: SupplierCategoryType,
    businessContactNumber?: string,
    businessEmail?: string,
    availability?: boolean,
    profileImage?: string,
    beautySaloon?: BeautySaloon,
    catering?: Catering,
    venue?: Venue,
    music?: Music,
    requests?: SupplierRequest[],
    packages?: ProfilePackage[],
    previousWorks?: ProfilePreviousWork[],
    images?: ProfileImage[],
    inventories?: Inventory[],
    id?: number
  ) {
    this.id = id || undefined;
    this.businessName = businessName;
    this.businessContactNumber = businessContactNumber;
    this.businessEmail = businessEmail;
    this.description = description;
    this.availability = availability;
    this.location = location;
    this.profileImage = profileImage;
    this.category = category;
    this.beautySaloon = beautySaloon;
    this.catering = catering;
    this.venue = venue;
    this.music = music;
    this.requests = requests;
    this.packages = packages;
    this.previousWorks = previousWorks;
    this.images = images;
    this.inventories = inventories;
  }
}

export type SupplierOptionalIDType = {
  id?: number | null;
  email?: string,
  password?: string
  businessName: string;
  businessContactNumber?: string;
  businessEmail?: string;
  description: string;
  availability?: boolean;
  location: Location;
  profileImage?: string;
  category: SupplierCategoryType;
  beautySaloon?: BeautySaloon;
  catering?: Catering;
  venue?: Venue;
  music?: Music;
  requests?: SupplierRequest[];
  packages?: ProfilePackage[];
  previousWorks?: ProfilePreviousWork[];
  images?: ProfileImage[];
  inventories?: Inventory[];
};
