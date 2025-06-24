import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';
import { Router, RouterLink } from '@angular/router';
import OLD_User from '../../../model/User';
import { SupabaseService } from '../../../../service/supabase.service';
import { HttpClient } from '@angular/common/http';
import Supplier, {
  SupplierOptionalIDType,
} from '../../../model/supplier/Supplier';
import { SupplierCategoryType } from '../../../../utils/SupplierCategoryType';
import { ProfileImage } from '../../../model/supplier/ProfileImage';
import { BeautySaloon } from '../../../model/supplier/BeautySaloon';
import { Catering } from '../../../model/supplier/Catering';
import { Venue } from '../../../model/supplier/Venue';
import { Music } from '../../../model/supplier/Music';
import { SupplierRequest } from '../../../model/supplier/SupplierRequest';
import { ProfilePackage } from '../../../model/supplier/ProfilePackage';
import { Inventory } from '../../../model/supplier/Inventory';
import { ProfilePreviousWork } from '../../../model/supplier/ProfilePreviousWork';
import { environment } from '../../../environment/env.test';
import { CustomerSignupService } from '../../../../service/signup-service/customer-signup.service';
import { Customer } from '../../../model/Customer';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  selectedButtonName: string = 'Customer';
  supplierRegPage: number = 1;

  firstName: string = '';
  lastName: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  agreeTerms: boolean = false;
  supplerCateoryType: SupplierCategoryType = SupplierCategoryType.VENUE;
  contactNumber: string = '';
  profileImageUrl: string = '';
  address: string = '';

  businessName: string = '';
  businessContactNumber: string = '';
  businessDescription: string = '';
  mobileNumber: string = '';
  website: string = '';
  selectedFile: File | null = null;
  uploadedFileUrl: string = '';
  isUploading: boolean = false;
  availability?: boolean;
  profileImage?: ProfileImage;
  beautySaloon?: BeautySaloon;
  catering?: Catering;
  venue?: Venue;
  music?: Music;
  requests?: SupplierRequest[];
  packages?: ProfilePackage[];
  previousWorks?: ProfilePreviousWork[];
  images?: ProfileImage[];
  inventories?: Inventory[];

  onButtonClick(buttonName: string) {
    this.selectedButtonName = buttonName;
    this.resetFormFields();

    if (buttonName === 'Supplier') {
      this.supplierRegPage = 1;
    }
  }

  resetFormFields() {
    this.firstName = '';
    this.lastName = '';
    this.email = '';
    this.password = '';
    this.confirmPassword = '';
    this.agreeTerms = false;

    this.businessName = '';
    this.businessDescription = '';
    this.mobileNumber = '';
    this.website = '';
    this.selectedFile = null;
    this.uploadedFileUrl = '';
  }

  validateBasicInfo(): boolean {
    if (!this.firstName || !this.lastName) {
      Swal.fire({
        title: 'Error',
        text: 'First Name and Last Name are required.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
      return false;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!this.email || !emailRegex.test(this.email)) {
      Swal.fire({
        title: 'Error',
        text: 'Please enter a valid email address.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
      return false;
    }

    if (!this.password || this.password.length < 8) {
      Swal.fire({
        title: 'Error',
        text: 'Password must be at least 8 characters long.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
      return false;
    }

    if (this.password !== this.confirmPassword) {
      Swal.fire({
        title: 'Error',
        text: 'Passwords do not match.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
      return false;
    }

    if (!this.agreeTerms) {
      Swal.fire({
        title: 'Error',
        text: 'You must agree to the terms and conditions.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
      return false;
    }

    return true;
  }

  validateSupplierBusinessInfo(): boolean {
    if (!this.businessName) {
      Swal.fire({
        title: 'Error',
        text: 'Business Name is required.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
      return false;
    }

    if (!this.businessDescription) {
      Swal.fire({
        title: 'Error',
        text: 'Business Description is required.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
      return false;
    }

    if (!this.mobileNumber) {
      Swal.fire({
        title: 'Error',
        text: 'Mobile Number is required.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
      return false;
    }

    if (!this.selectedFile) {
      Swal.fire({
        title: 'Error',
        text: 'Please upload a Business Verification Document.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
      return false;
    }

    return true;
  }

  onSupplierNextButtonClick(page: number) {
    if (page === 2) {
      if (this.validateBasicInfo()) {
        this.supplierRegPage = page;
      }
    } else {
      this.supplierRegPage = page;
    }
  }

  onCustomerSignUpButtonClicked() {
    if (this.validateBasicInfo()) {
      const customer: Customer = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password,
        contactNumber: this.contactNumber,
        profileImageUrl: this.profileImageUrl || '',
        address: this.address,
      };

      console.log('Customer object to be sent:', customer);

      this.signupService.signup(customer).subscribe({
        next: (response) => {
          Swal.fire({
            title: 'Successfully Registered',
            text: 'You have successfully registered and can now start planning your events. Enjoy your experience with us!',
            icon: 'success',
            confirmButtonText: 'OK',
          }).then((result) => {
            if (result.isConfirmed) {
              // Navigate to dashboard or login
              this.resetFormFields();
              this.router.navigate(['/login']);
            }
          });
        },
        error: (error) => {
          console.error('Error during registration:', error);
          Swal.fire({
            title: 'Registration Failed',
            text: 'Something went wrong. Please try again later.',
            icon: 'error',
            confirmButtonText: 'OK',
          });
        },
      });
    }
  }

  async onSupplierSignUpButtonClicked() {
    // console.log('correct');

    if (!this.validateBasicInfo() || !this.validateSupplierBusinessInfo()) {
      return;
    }

    this.isUploading = true;

    try {
      await this.uploadFileToSupabase();

      // userId: number,
      // profileId: number,
      // businessName: string,
      // SupplierType: string,
      // email: string,
      // password:string,
      // phoneNumber: string,
      // description: string,
      // website: string,
      // imgUrl: string,
      // rating: number,
      // uploadedFileUrl:string

      const supplier: SupplierOptionalIDType = {
        id: null,
        businessName: this.businessName,
        description: this.businessDescription,
        location: location,
        category: this.supplerCateoryType,
        businessContactNumber: this.mobileNumber,
        businessEmail: this.email,
        availability: this.availability,
        beautySaloon: this.beautySaloon,
        catering: this.catering,
        venue: this.venue,
        music: this.music,
        requests: this.requests,
        packages: this.packages,
        previousWorks: this.previousWorks,
        images: this.images,
        inventories: this.inventories,
        email: this.email,
        password: this.password
      };

      console.log('New SupplierOptionalIDType object created :', supplier);

      this.http
        .post(`${environment.baseUrl}/api/supplier/add`, supplier)
        .subscribe((res) => {});

      Swal.fire({
        title: 'Successfully Signed Up!!',
        text: 'Your supplier account has been created and submitted for review. We will notify you of the status via email.',
        icon: 'success',
        confirmButtonText: 'OK',
      }).then((result) => {
        if (result.isConfirmed) {
          this.resetFormFields();
          this.supplierRegPage = 1;
          this.selectedButtonName = 'Customer';

          // Dashboard Navigation when API returns true
        }
      });
    } catch (error) {
      console.error('Error during supplier registration:', error);
      Swal.fire({
        title: 'Error',
        text: 'Failed to complete registration. Please try again.',
        icon: 'error',
        confirmButtonText: 'OK',
      });
    } finally {
      this.isUploading = false;
    }
  }

  onFileSelected(event: any): void {
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      this.selectedFile = fileList[0];

      const validTypes = [
        'application/pdf',
        'image/jpeg',
        'image/jpg',
        'image/png',
      ];
      if (!validTypes.includes(this.selectedFile.type)) {
        Swal.fire({
          title: 'Error',
          text: 'Please select a PDF, JPEG, or PNG file.',
          icon: 'error',
          confirmButtonText: 'OK',
        });
        this.selectedFile = null;
        event.target.value = null;
        return;
      }

      const maxSizeInBytes = 20 * 1024 * 1024;
      if (this.selectedFile.size > maxSizeInBytes) {
        Swal.fire({
          title: 'Error',
          text: 'File size exceeds 20MB limit.',
          icon: 'error',
          confirmButtonText: 'OK',
        });
        this.selectedFile = null;
        event.target.value = null;
        return;
      }
    }
  }

  constructor(
    private supabase: SupabaseService,
    private signupService: CustomerSignupService,
    private router: Router,
    private http: HttpClient
  ) {}

  private async uploadFileToSupabase(): Promise<void> {
    if (!this.selectedFile) {
      throw new Error('No file selected');
    }

    const bucketName = 'evo.brs';
    const timestamp = new Date().getTime();
    const uniqueFileName = `${timestamp}_${this.selectedFile.name}`;
    const filePath = `uploads/${uniqueFileName}`;

    try {
      const data = await this.supabase.uploadFile(
        bucketName,
        filePath,
        this.selectedFile
      );
      console.log('File uploaded successfully:', data);
      this.uploadedFileUrl = this.supabase.getFileUrl(bucketName, filePath);
      console.log('File URL:', this.uploadedFileUrl);
    } catch (error) {
      console.error('Error uploading file:', error);
      throw new Error('Failed to upload file');
    }
  }
}
