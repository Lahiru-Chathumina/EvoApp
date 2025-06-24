import { Routes } from '@angular/router';
import { LandingComponent } from './page/landing/landing.component';
import { LoginComponent } from './page/user/login/login.component';
import { SignupComponent } from './page/user/signup/signup.component';
import { EventRootComponent } from './page/event/event-root/event-root.component';
import { SupplierRootComponent } from './page/supplier/supplier-root/supplier-root.component';
import { RatingReviewsComponent } from './page/supplier/rating-reviews/rating-reviews.component';
import { BookingEventComponent } from './page/supplier/dash/booking-event/booking-event.component';
import { EventSelectionComponent } from './page/event/event-selection/event-selection.component';
import { AllEventsComponent } from './page/event/all-events/all-events.component';
import { ServiceSelectionComponent } from './page/event/service-selection/service-selection.component';
import { VenueDashboardComponent } from './page/event/venue-dashboard/venue-dashboard.component';
import { EventSummaryComponent } from './page/event/event-summary/event-summary.component';
import { AgendaComponent } from './page/event/agenda/agenda.component';
import { AllSuppliersComponent } from './page/supplier/all-suppliers/all-suppliers.component';
import { PhotographerInfoComponent } from './page/supplier/photographer-info/photographer-info.component';
import { EntertainmentInfoComponent } from './page/supplier/entertainment-info/entertainment-info.component';
import { BeautyEssentialsInfoComponent } from './page/supplier/beauty-essentials-info/beauty-essentials-info.component';
import { DashboardComponent } from './page/supplier/dash/dashboard/dashboard.component';
import { AddPreviousComponent } from './page/supplier/dash/add-previous/add-previous.component';
import { DashRootComponent } from './page/supplier/dash/dash-root/dash-root.component';
import { ProfileViewComponent } from './page/supplier/dash/profile/profile-view/profile-view.component';
import { ManagePackagesComponent } from './page/supplier/dash/manage-packages/manage-packages.component';
import { InventoryComponent } from './page/supplier/dash/inventory/inventory.component';
import { PaymentsComponent } from './page/supplier/dash/payments/payments.component';
import { MassagesComponent } from './page/supplier/dash/massages/massages.component';
import { MyReviewsComponent } from './page/supplier/dash/my-reviews/my-reviews.component';
import { CustomerDashboardComponent } from './page/customer/customer-dashboard/customer-dashboard.component';
import { EventManageComponent } from './page/customer/event-manage/event-manage.component';
import { MassageComponent } from './page/customer/massage/massage.component';
import { OngoingEventComponent } from './page/customer/ongoing-event/ongoing-event.component';
import { PaymentsCustomerComponent } from './page/customer/payments-customer/payments-customer.component';
import { ProfileCustomerComponent } from './page/customer/profile-customer/profile-customer.component';
import { DashRootCustomerComponent } from './page/customer/dash-root-customer/dash-root-customer.component';
import { ProfileEditComponent } from './page/supplier/dash/profile/profile-edit/profile-edit.component';
import { ProfileComponent } from './page/supplier/dash/profile/profile.component';
import { PreviousWorkComponent } from './page/supplier/previous-work/previous-work.component';
import { ConditionsComponent } from './page/customer/admin/conditions/conditions.component';
import { AddPackagesComponent } from './page/supplier/dash/add-packages/add-packages.component';
import { AdminRootComponent } from './page/customer/admin-dashbaord/admin-root/admin-root.component';

import { ReportsComponent } from './page/customer/admin-dashbaord/pages/reports/reports.component';
import { NotificationsComponent } from './page/customer/admin-dashbaord/pages/notifications/notifications.component';
import { AuditHistoryComponent } from './page/customer/admin-dashbaord/pages/audit-history/audit-history.component';
import { OverviewComponent } from './page/customer/admin-dashbaord/pages/overview/overview.component';
import { RequestsComponent } from './page/customer/admin-dashbaord/pages/requests/requests.component';
import { SupplierPendingRequestComponent } from './page/supplier/dash/supplier-pending-request/supplier-pending-request.component';

import { MessageComponent } from './page/customer/admin-dashbaord/pages/message/message.component';
import { WeddingFormComponent } from './page/event/wedding-form/wedding-form.component';
import { BirthdayPartyFormComponent } from './page/event/birthday-party-form/birthday-party-form.component';
import { GetTogetherFormComponent } from './page/event/get-together-form/get-together-form.component';
import { AnnivesaryFormComponent } from './page/event/annivesary-form/annivesary-form.component';
import { UpcommingEventComponent } from './page/customer/upcomming-event/upcomming-event.component';
import { TransectionsComponent } from './page/customer/admin-dashbaord/pages/transections/transections.component';
import { BlogComponent } from './common/blog/blog.component';
import { AuthGard } from '../service/auth-service/auth.gard.service';
import { AdminUserManagementComponent } from './page/customer/admin-dashbaord/pages/admin-customer-details/admin-user-mangement/admin-user-management.component';

export const routes: Routes = [
  {
    path: "",
    component: LandingComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: SignupComponent
  },
  {
    path: "event",
    component: EventRootComponent,
    data: { accessRole: '*' },
    canActivate: [AuthGard],
    children: [
      {
        path: "",
        component: AllEventsComponent,
        canActivate: [AuthGard]
      },
      {
        path: "event-selection",
        component: EventSelectionComponent
      },
      {
        path: "venue-selection",
        component: VenueDashboardComponent
      },
      {
        path: "event-service-selection",
        component: ServiceSelectionComponent
      },
      {
        path: "event-summery",
        component: EventSummaryComponent
      },
      {
        path: "agenda",
        component: AgendaComponent
      },
      {
        path:"wedding-form",
        component:WeddingFormComponent
      },{
        path:"birthday-party-form",
        component:BirthdayPartyFormComponent
      },{
        path:"get-together-form",
        component:GetTogetherFormComponent
      },{
        path:"anniversary-form",
        component:AnnivesaryFormComponent
      }
    ]
  },
  {
    path: "supplier",
    component: SupplierRootComponent,
    canActivate: [AuthGard],
    data: { accessRole: 'ADMIN,SUPPLIER,CUSTOMER' },
    children: [
      {
        path: "",
        component: AllSuppliersComponent
      },
      {
        path: "dashboard",
        component: DashboardComponent,
        children: [
          {
            path: "",
            component: DashRootComponent
          },
          {
            path: "profile",
            component: ProfileComponent,
            children: [
              {
                path: "",
                component: ProfileViewComponent
              },
              {
                path: "profile-view",
                component: ProfileViewComponent

              },
              {
                path: "profile-edit",
                component: ProfileEditComponent

              }
            ]

          },
          {
            path: "add-previous",
            component: AddPreviousComponent
          },
          {
            path: "all-bookings",
            component: BookingEventComponent
          },
          {
            path: "packages",
            component: ManagePackagesComponent
          },
          {
            path:"add-packages",
            component:AddPackagesComponent
          },
          {
            path: "inventory",
            component: InventoryComponent
          },
          {
            path: "blogs",
            component: BlogComponent
          },
          {
            path: "payments",
            component: PaymentsComponent
          },
          {
            path: "massages",
            component: MassagesComponent
          },
          {
            path: "my-reviews",
            component: MyReviewsComponent
          },{
            path: "requests",
            component: SupplierPendingRequestComponent
          }
        ]
      },
      {
        path: "review",
        component: RatingReviewsComponent
      },
      {
        path: "bookings",
        component: BookingEventComponent
      },
      {
        path: "photographer-info",
        component: PhotographerInfoComponent
      },
      {
        path: "photographer-info/:id",
        component: PhotographerInfoComponent
      },
      {
        path: "entertaiment-info",
        component: EntertainmentInfoComponent
      },
      {
        path: "beauty-info",
        component: BeautyEssentialsInfoComponent
      },
      {
        path:"previous-work",
        component:PreviousWorkComponent
      },
      // {
      //   path:"view-inventory",
      //   component:ViewInventoryComponent
      // }
    ]
  },
  {
    path: "customer",
    component: CustomerDashboardComponent,
    canActivate: [AuthGard],
    data: { accessRole: 'ADMIN,CUSTOMER' },
    children:[
      {
        path:"",
        component:DashRootCustomerComponent
      },
      {
        path:"event-manage",
        component:EventManageComponent
      },
      {
        path:"massage",
        component:MassageComponent
      },
      {
        path:"upcoming-event",
        component:UpcommingEventComponent
      },
      {
        path:"ongoing-event",
        component:OngoingEventComponent
      },
      {
        path:"payments",
        component:PaymentsCustomerComponent
      },
      {
        path:"profile",
        component:ProfileCustomerComponent
      },
      {
        path:"conditions",
        component:ConditionsComponent
      },
      {
        path:"blogs",
        component: BlogComponent
      }
    ]
  },
  {
    path: "admin-dash",
    component:AdminRootComponent,
    canActivate: [AuthGard],
    data: { accessRole: 'ADMIN' },
    children:[
      {
        path:"",
        component:OverviewComponent
      },
      {
        path:"transections",
        component:TransectionsComponent
      },
      {
        path:"reports",
        component:ReportsComponent
      },
      {
        path:"notifications",
        component:NotificationsComponent
      },
      {
        path:"audit",
        component:AuditHistoryComponent
      },
      {
        path:"requests",
        component:RequestsComponent
      },
      {
        path:"messages",
        component:MessageComponent

      },
      {

        path:"admin-user-manage",
        component:AdminUserManagementComponent

      }


      // {
      //   path:"admin-profile",
      //   component:AdminProfileComponent
      // }
    ]
  }
];
