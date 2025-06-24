import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog } from '../../../app/model/supplier/Blog';
import { UserType } from '../../../utils/UserType';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../app/environment/env.test';


@Injectable({
  providedIn: 'root',
})
export class BlogService {

  constructor(private http:HttpClient) {}

  public getBlogs(): Observable<Blog[]> {
    return new Observable<Blog[]>((observer) => {
      const limit = 5;
      this.http.get<any[]>(`${environment.baseUrl}/api/blogs`).subscribe(
        (data) => {
          const blogs: Blog[] = data.slice(0, limit).map((item) => ({
            id: item.id,
            title: item.title,
            author_name: item.authorName,
            author_type: item.authorType as UserType,
            profile_image_url: item.profileImageUrl,
            content: item.content,
            createdAt: item.createdAt,
            like_count: item.likeCount + Math.floor(Math.random() * 100) ,
            bookmark_count: item.bookmarkCount,
            liked: item.liked ?? false,
            bookmarked: item.bookmarked ?? false,
            image_url: item.imageUrl
          }));
          observer.next(blogs);
          observer.complete();
        }
      );
    });
  }

  public createBlog(blog: any): Observable<any> {
    return new Observable<any>((observer) => {
      this.http.post(`${environment.baseUrl}/api/blogs`, blog).subscribe(
      (response) => {
        observer.next(response);
        observer.complete();
      },
      (error) => {
        console.error('Error creating blog:', error);
        observer.error(error);
      }
      );
    });
  }

  likeBlog(userId: number, id: string) {
    return new Observable((observer) => {
      this.http.post(`${environment.baseUrl}/api/blogs/${id}/${userId}`, {}).subscribe({
        next: (response) => {
          observer.next(response);
          observer.complete();
        },
        error: (error) => {
          console.error('Error liking blog:', error);
          observer.error(error);
        }
      });
    });
  }

}
