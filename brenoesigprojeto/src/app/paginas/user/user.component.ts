import { Component } from '@angular/core';
import { TaskManagerComponent } from './task-manager/task-manager.component'; // Importando o TaskManagerComponent
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-user',
  standalone: true,
  imports: [TaskManagerComponent, FormsModule], // Importando o TaskManagerComponent
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {}
