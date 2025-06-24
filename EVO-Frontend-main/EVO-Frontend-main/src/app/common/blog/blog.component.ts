import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Blog } from '../../model/supplier/Blog';
import { BlogService } from '../../../service/user-services/blog/blog.service';
import { thisExpression } from '@babel/types';
import { FormsModule } from '@angular/forms';
import { UserType } from '../../../utils/UserType';
import { AuthService } from '../../../service/auth-service/auth.service';
// Add Supabase client import
import { createClient } from '@supabase/supabase-js';
import { environment } from '../../environment/env.test';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-blog',
  imports: [CommonModule, FormsModule],
  templateUrl: './blog.component.html',
  styleUrl: './blog.component.css',
})
export class BlogComponent implements OnInit {
  constructor(
    private blogService: BlogService,
    private authService: AuthService
  ) {}

  data: Blog[] = [];
  userId: number = 0;
  modalPostTitle: string = '';
  modalPostContent: string = '';

  modalImageFile: File | null = null;
  modalImagePreview: string | null = null;

  modalProfileImageUrl: string = '';
  modalAuthorName: string = '';
  modalauthorType: string = '';
  modalEmail: string = '';

  preloader: boolean = false;

  supabase = createClient(environment.supabaseURL, environment.supaBaseKey);

  ngOnInit(): void {
    this.userId = this.authService.getUserId() ?? 0;
    this.modalProfileImageUrl = this.authService.getProfileImage();
    this.modalAuthorName = this.authService.getFullName();
    this.modalEmail = this.authService.getUsername ? this.authService.getUsername() ?? '' : '';
    this.modalauthorType = this.authService.getRole() === UserType.CUSTOMER ? 'Customer' : 'Supplier';
    this.blogService.getBlogs().subscribe((blogs) => {
      this.data = blogs;
      console.log(this.data);
    });
  }

  prettifyDate(date: string): string {
    return new Date(date).toLocaleDateString();
  }

  likePost(id: number) {
    let post = this.data.find((post) => post.id === id);

    if (post) {
      post.liked = !post?.liked;
      if (post.liked == true) post.like_count++; else post.like_count--;
      this.blogService.likeBlog(this.userId, post.id!.toString());
    }
  }


  onImageSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.modalImageFile = file;
      const reader = new FileReader();
      reader.onload = e => this.modalImagePreview = reader.result as string;
      reader.readAsDataURL(file);
    }
  }

  async createPost() {
    if (!this.modalPostTitle || !this.modalPostContent) {
      alert("Please fill all required fields!");
      return;
    }

    this.preloader = true;

    let imageUrl: string | undefined = undefined;

    if (this.modalImageFile) {
      const fileExt = this.modalImageFile.name.split('.').pop();
      const fileName = `${Date.now()}.${fileExt}`;
      const { error } = await this.supabase
        .storage
        .from('blog-images')
        .upload(fileName, this.modalImageFile);

      if (error) {
        this.preloader = false;
        alert('Image upload failed!');
        return;
      }

      const { data: publicUrlData } = this.supabase
        .storage
        .from('blog-images')
        .getPublicUrl(fileName);

      imageUrl = publicUrlData?.publicUrl;
    }

    const postData = {
      profileImageUrl: this.modalProfileImageUrl,
      authorName: this.modalAuthorName,
      authorType: this.modalauthorType,
      title: this.modalPostTitle,
      imageUrl: imageUrl,
      content: this.modalPostContent
    };

    this.blogService.createBlog(postData).subscribe({
      next: () => {
        this.ngOnInit();
        this.preloader = false;
        Swal.fire({
          icon: 'success',
          title: 'Post Created!',
          text: 'Your post has been published.',
          timer: 2000,
          showConfirmButton: false
        });
      },
      error: (error) => {
        this.preloader = false;
        Swal.fire({
          icon: 'error',
          title: 'Failed!',
          text: 'Failed to create post! ' + error.message
        });
      }
    });

    this.modalPostTitle = '';
    this.modalPostContent = '';
    this.modalImageFile = null;
    this.modalImagePreview = null;
  }
}
