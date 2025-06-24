import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { AuthService } from '../../../../service/auth-service/auth.service';
import { CustomerUpdateService } from '../../../../service/user-services/CustomerUpdateService';
import { createClient, SupabaseClient } from '@supabase/supabase-js';
import { environment } from '../../../environment/env.test';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-profile-customer',
  imports: [ CommonModule, FormsModule ],
  templateUrl: './profile-customer.component.html',
  styleUrl: './profile-customer.component.css'
})
export class ProfileCustomerComponent implements OnInit {
  isEditing = false;
  loading = false;
  profileImgSrc = '';
  firstName = '';
  lastName = '';
  email = '';
  contactNumber = '';
  address = '';
  password = '';
  rePassword = '';

  private supabase: SupabaseClient;
  private selectedFile: File | null = null;

  constructor(
    private authService: AuthService,
    private customerUpdateService: CustomerUpdateService,
    private cdr: ChangeDetectorRef
  ) {
    this.supabase = createClient(
      environment.supabaseURL,
      environment.supaBaseKey
    );
  }

  ngOnInit(): void {
    const userId = this.authService.getUserId();
    if (userId) {
      this.customerUpdateService.GetCustomer(userId).subscribe({
        next: (customer) => {
          this.profileImgSrc = customer.profileImageUrl || this.authService.getProfileImage();
          this.firstName = customer.firstName || '';
          this.lastName = customer.lastName || '';
          this.email = customer.email || '';
          this.contactNumber = customer.contactNumber || '';
          this.address = customer.address || '';
        },
        error: () => {
          Swal.fire('Error', 'Failed to load customer data.', 'error');
        }
      });
    }
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      this.selectedFile = input.files[0];
      const reader = new FileReader();
      reader.onload = (e) => {
        this.profileImgSrc = e.target?.result as string;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }

  toggleEdit(): void {
    this.isEditing = !this.isEditing;
  }

  async saveChanges(): Promise<void> {
    if (!this.firstName || !this.lastName || !this.email || !this.contactNumber || !this.address || !this.password || !this.rePassword) {
      Swal.fire('Error', 'Please fill all required fields.', 'error');
      return;
    }
    if (this.password !== this.rePassword) {
      Swal.fire('Error', 'Passwords do not match.', 'error');
      return;
    }

    this.loading = true;

    try {
      let profileImageUrl = this.profileImgSrc;
      if (this.selectedFile) {
        const userId = this.authService.getUserId();
        const filePath = `profile-images/${userId}_${Date.now()}_${this.selectedFile.name}`;
        const { error } = await this.supabase.storage
          .from('profile.images')
          .upload(filePath, this.selectedFile, { upsert: true });

        if (error) throw error;

        const { data } = this.supabase.storage
          .from('profile.images')
          .getPublicUrl(filePath);

        profileImageUrl = data.publicUrl;
      }

      const userId = this.authService.getUserId();
      if (userId === null || userId === undefined) {
        throw new Error('User ID is missing.');
      }

      const updatePayload = {
        id: userId,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password,
        contactNumber: this.contactNumber,
        profileImageUrl: profileImageUrl,
        address: this.address
      };

      await firstValueFrom(
        this.customerUpdateService.updateCustomer(userId, updatePayload)
      );

      this.isEditing = false;

      Swal.fire('Success!', 'Profile updated successfully!', 'success').then(() => {
        this.authService.refreshToken();
        this.cdr.detectChanges();
      });
    } catch (error) {
      Swal.fire('Error', 'Failed to update profile.', 'error');
    } finally {
      this.loading = false;
    }
  }
}
