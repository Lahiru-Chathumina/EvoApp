import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { environment } from '../../app/environment/env.test';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private expTimeout: any = null;

  constructor(private router: Router, private http: HttpClient) {
    this.startAutoLogoutCheck();
  }

  private startAutoLogoutCheck(): void {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe(() => {
        const token = this.getToken();
        if (token) this.setToken(token);
      });
  }

  public setToken(token: string): void {
    sessionStorage.setItem('authToken', token);

    const payload = token.split('.')[1];
    const decodedPayload = JSON.parse(atob(payload));

    const ONE_MINUTE = 60000;
    const expMillis = decodedPayload.exp * 1000;
    const nowMillis = Date.now();
    const deltaMillis = expMillis - nowMillis;
    let remainingTime =
      deltaMillis > ONE_MINUTE ? deltaMillis - ONE_MINUTE : deltaMillis;

    console.log(`Token will expire in: ${remainingTime} ms`);

    if (this.expTimeout !== null) clearTimeout(this.expTimeout);

    this.expTimeout = setTimeout(() => {
      console.log('Token expired. Auto logging out.');
      this.logout();
    }, remainingTime);
  }

  public isAdmin() {
    const role: string | null = this.getRole();
    return role != null && role.toLowerCase() === 'admin';
  }

  public getToken(): string | null {
    return sessionStorage.getItem('authToken');
  }

  public getUsername(): string | null {
    const token: string | null = this.getToken();

    return !token ? null : this.decodeToken(token).sub;
  }

  public getRole(): string | null {
    const token: string | null = this.getToken();

    return !token ? null : this.decodeToken(token).role;
  }

  public refreshToken(): void {
    const token = this.getToken();
    if (!token) {
      this.logout();
      console.log('No token found, logging out');
      return;
    }

    this.http.post<any>(
      `${environment.baseUrl}/api/auth/refresh-token`,
      {},
      {
        headers: { Authorization: `Bearer ${token}` }
      }
    ).subscribe({
      next: (response) => {
        console.log('Token refreshed successfully:', response);
        if (response && response.token) {
          this.setToken(response.token);
          console.log('New token set successfully');
        } else {
          console.error('No token in refresh response');
          this.logout();
        }
      },
      error: (error) => {
        console.error('Error refreshing token:', error);
        this.logout();
      }
    });
  }

  private isTokenExpired(): boolean {
    const token: string | null = this.getToken();

    return !token
      ? true
      : this.decodeToken(token).exp * 1000 < new Date().getTime();
  }

  private decodeToken(token: string): any {
    const payload = token.split('.')[1];
    const decodedPayload = atob(payload);
    return JSON.parse(decodedPayload);
  }

  public isAuthenticated(): boolean {
    return !this.isTokenExpired();
  }

  public getUserId(): number | null {
    const token = this.getToken();
    return token ? this.decodeToken(token).user_id ?? null : null;
  }

  public getProfileImage(): string {
    const token = this.getToken();
    const defaultImg =
      'https://static.vecteezy.com/system/resources/thumbnails/004/511/281/small_2x/default-avatar-photo-placeholder-profile-picture-vector.jpg';
    if (!token) return defaultImg;
    const img = this.decodeToken(token).profile_image;
    return img && img.trim() !== '' ? img : defaultImg;
  }

  public getFullName(): string {
    const token = this.getToken();
    if (!token) return '';
    const fullName = this.decodeToken(token).username || '';
    return `${fullName}`.trim();
  }

  public logout(): void {
    sessionStorage.removeItem('authToken');
    this.router.navigate(['/login']);
  }

}
