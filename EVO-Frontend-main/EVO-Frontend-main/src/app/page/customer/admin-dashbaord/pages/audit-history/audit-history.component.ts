import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-audit-history',
  imports: [CommonModule],
  templateUrl: './audit-history.component.html',
  styleUrl: './audit-history.component.css'
})
export class AuditHistoryComponent {

  getActionClass(actionType: string): string {
    switch(actionType.toLowerCase()){
      case'create':return'action-create';
      case'update':return'action-update';
      case'delete':return'action-delete';
      default: return '';
    }
  }

  public audits = [
    { userId: 1, adminId: '001', reasonForChange: 'derfs', timestamp: 'Supplier', actionType: 'create', affectedEntity: 'create', newDate: '2023/12/23', oldDate: '2020/02/22' },
    { userId: 2, adminId: '002', reasonForChange: 'frdt', timestamp: 'User', actionType: 'update', affectedEntity: 'create', newDate: '2023/12/23', oldDate: '2020/02/22'  },
    { userId: 3, adminId: '003', reasonForChange: 'nhgb', timestamp: 'Supplier', actionType: 'create', affectedEntity: 'create', newDate: '2023/12/23', oldDate: '2020/02/22'  },
    { userId: 4, adminId: '004', reasonForChange: 'gtry', timestamp: 'User', actionType: 'delete', affectedEntity: 'create', newDate: '2023/12/23', oldDate: '2020/02/22'  },
    { userId: 5, adminId: '005', reasonForChange: 'uyhgftr', timestamp: 'Supplier', actionType: 'update', affectedEntity: 'create', newDate: '2023/12/23', oldDate: '2020/02/22'  },
    { userId: 6, adminId: '006', reasonForChange: 'qedse', timestamp: 'User', actionType: 'create', affectedEntity: 'create', newDate: '2023/12/23', oldDate: '2020/02/22'  }
  ];
}
