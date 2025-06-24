import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from "@angular/router";
import { AuthService } from "./auth.service";
import { Injectable } from "@angular/core";

@Injectable({
	providedIn: 'root'
})
export class AuthGard implements CanActivate {
	constructor (private authService: AuthService, private router: Router) {}

	public canActivate (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
		return true;
		if (!this.authService.isAuthenticated()) {
			this.router.navigate(['/login']);
			return false;
		}

		if (!route.data['accessRole'] || route.data['accessRole'] === '*') return true;
		if (route.data['accessRole'].split(',').includes(this.authService.getRole())) return true;

		this.router.navigate(['/']);
		return false;
	}
}
