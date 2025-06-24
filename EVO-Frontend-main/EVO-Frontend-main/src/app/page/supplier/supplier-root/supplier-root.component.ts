import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavBarComponent } from "../../../common/nav-bar/nav-bar.component";

@Component({
  selector: 'app-supplier-root',
  imports: [RouterOutlet, NavBarComponent],
  templateUrl: './supplier-root.component.html',
  styleUrl: './supplier-root.component.css'
})
export class SupplierRootComponent {

}
