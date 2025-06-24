import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AgendaService } from '../../../../service/event-services/agendaService';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { routes } from '../../../app.routes';
import { EventFlowService } from '../../../../service/event-services/event-flow/eventFlowService';

interface Session {
  id?: any;
  title: string;
  type: string;
  startTime: string;
  endTime: string;
  isEditing?: boolean;
}

interface EventDetails {
  type: string;
  venue: string;
  date: string;
  organizer: string;
}

@Component({
  selector: 'app-agenda',
  standalone: true,
  imports: [NgFor, CommonModule, FormsModule,RouterModule],
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.css']
})
export class AgendaComponent implements OnInit {
  showEventDetails: boolean = true;
  agenda: EventDetails[] = [{ type: '', venue: '', date: '', organizer: '' }];
  sessions: Session[] = [];
  newSession: Session = { title: '', type: '', startTime: '', endTime: '' };
  showSessionModal: boolean = false;

  agendadata = JSON.parse(localStorage.getItem('FormData') || 'null');
  supplierdata = JSON.parse(localStorage.getItem('suppliers') || 'null');

  constructor(private agendaService: AgendaService, private eventFlowService: EventFlowService, private rout:Router) { }

  ngOnInit(): void {

    this.agenda[0] = {
      type: this.agendadata?.eventType || 'Conference',
      venue: this.agendadata?.brideName || 'Virtual Meeting',
      date: this.agendadata?.eventDate || '2025-05-15',
      organizer: this.agendadata?.groomName || 'Your Organization'
    };

    
    if (this.supplierdata) {
      this.sessions = Array.isArray(this.supplierdata) ? 
        this.supplierdata.map(item => ({
          title: item.description || 'Session',
          type: item.availability || 'General',
          startTime: item.startTime || '',
          endTime: item.endTime || '',
          isEditing: false
        })) : 
        [{
          title: this.supplierdata.description || 'Session',
          type: this.supplierdata.availability || 'General',
          startTime: this.supplierdata.startTime || '',
          endTime: this.supplierdata.endTime || '',
          isEditing: false
        }];
    }
   
  }

  toggleEventDetails(): void {
    this.showEventDetails = !this.showEventDetails;
  }

  toggleEditSession(session: Session): void {
    session.isEditing = !session.isEditing;
    if (!session.isEditing) {
      this.validateSessionTime(session);
    }
  }

  validateSessionTime(session: Session): boolean {
    if (session.endTime && session.startTime && session.endTime < session.startTime) {
      alert('End time cannot be before start time');
      session.endTime = '';
      return false;
    }
    return true;
  }

  openSessionModal(): void {
    this.newSession = { title: '', type: '', startTime: '', endTime: '' };
    this.showSessionModal = true;
  }

  closeSessionModal(): void {
    this.showSessionModal = false;
  }

  addSession(): void {
    if (!this.newSession.title || !this.newSession.type) {
      alert('Please fill all required fields');
      return;
    }

    if (!this.validateSessionTime(this.newSession)) {
      return;
    }

    this.sessions.push({
      ...this.newSession,
      isEditing: false
    });
    this.closeSessionModal();
  }

  removeSession(index: number): void {
    if (confirm('Are you sure you want to remove this session?')) {
      this.sessions.splice(index, 1);
    }
  }

  saveSession(index: number): void {
    if (!this.validateSessionTime(this.sessions[index])) {
      return;
    }
    this.sessions[index].isEditing = false;
  }

  submitAgenda() {
    
    if (!this.agenda[0].type || !this.agenda[0].venue || !this.agenda[0].date || !this.agenda[0].organizer) {
      alert('Please fill in all event details.');
      return;
    }

  
    if (this.sessions.length === 0) {
      alert('Please add at least one session.');
      return;
    }
    for (const session of this.sessions) {
      if (!session.title || !session.type || !session.startTime || !session.endTime) {
      alert('Please fill in all session fields.');
      return;
      }
      if (!this.validateSessionTime(session)) {
      return;
      }
    }

    const agendaData = {
      id: Date.now(),
      time: '',
      date: this.agenda[0].date || '',
      tasks: this.sessions,
      eventDetails: this.agenda[0],
      sessions: this.sessions
    };
    const savedAgendas: any[] = JSON.parse(localStorage.getItem('savedAgendas') || '[]');

    savedAgendas.push(agendaData);
    localStorage.setItem('savedAgendas', JSON.stringify(savedAgendas));

    alert('Agenda has been successfully created and saved locally.');

    this.eventFlowService.dumpEventToDatabase(); // Saves event details from local storage to database.

    this.rout.navigate(['customer/upcoming-event']);
  }
  loadAgenda() {
    const savedAgendas = JSON.parse(localStorage.getItem('savedAgendas') || '[]');
    if (savedAgendas.length > 0) {
      this.agenda = savedAgendas[0].eventDetails;
      this.sessions = savedAgendas[0].sessions;
    } else {
      alert('No saved agendas found.');
    }
  }
}