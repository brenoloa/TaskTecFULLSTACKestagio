import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../../services/task.service';
import { Task } from '../../../types/task.type';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-task-manager',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './task-manager.component.html',
  styleUrls: ['./task-manager.component.css']
})
export class TaskManagerComponent implements OnInit {
  username: string = '';
  tasks: Task[] = [];
  filteredTasks: Task[] = [];

  formTask: Task = {
    title: '',
    description: '',
    priority: 'média',
    deadline: null,
    completed: false,
    status: 'Em andamento',
    responsavel: ''
  };

  filterPriority: string = '';
  filterStatus: string = '';
  filterDeadlineOption: 'menor' | 'maior' = 'menor';
  editingTaskId: string | null = null;

  editTaskForm: Task = {
    title: '',
    description: '',
    priority: 'média',
    deadline: null,
    completed: false,
    status: 'Em andamento',
    responsavel: ''
  };

  constructor(private taskService: TaskService, private loginService: LoginService) {}

  ngOnInit(): void {
    this.username = sessionStorage.getItem('username') || 'Usuário';
    this.loadTasks();
  }

  loadTasks(): void {
    this.taskService.getTasks().subscribe((tasks) => {
      this.tasks = tasks;
      this.applyFilter();
    });
  }

  applyFilter(): void {
    this.filteredTasks = [...this.tasks];

    if (this.filterPriority) {
      this.filteredTasks = this.filteredTasks.filter(task => task.priority === this.filterPriority);
    }

    if (this.filterStatus) {
      this.filteredTasks = this.filteredTasks.filter(task => task.status === this.filterStatus);
    }

    this.filteredTasks.sort((a, b) => {
      const dateA = new Date(a.deadline || '').getTime();
      const dateB = new Date(b.deadline || '').getTime();
      return this.filterDeadlineOption === 'menor' ? dateA - dateB : dateB - dateA;
    });
  }

  createTask(): void {
    if (!this.validateTask(this.formTask)) {
      alert("Por favor, preencha todos os campos obrigatórios.");
      return;
    }

    this.taskService.createTask(this.formTask).subscribe(() => {
      this.loadTasks();
      this.resetForm();
    });
  }

  markAsConcluded(task: Task): void {
    task.status = task.status === 'Em andamento' ? 'Concluída' : 'Em andamento';

    if (task.id) {
      this.taskService.updateTask(task.id, task).subscribe(() => {
        this.loadTasks();
      });
    }
  }

  openEditModal(task: Task): void {
    if (task.status === 'Concluída') {
      alert("Não pode editar uma tarefa concluída.");
      return;
    } else if (task.id) {
      this.editingTaskId = task.id;
      this.editTaskForm = { ...task };
      document.getElementById("editModal")!.style.display = "block";
    }
  }



  updateTask(): void {
    if (this.editingTaskId) {
      this.taskService.updateTask(this.editingTaskId, this.editTaskForm).subscribe(() => {
        this.loadTasks();
        this.resetForm();
        document.getElementById("editModal")!.style.display = "none";
      });
    }
  }

  deleteTask(taskId?: string): void {
    if (taskId) {
      this.taskService.deleteTask(taskId).subscribe(() => this.loadTasks());
    }
  }

  resetForm(): void {
    this.formTask = {
      title: '',
      description: '',
      priority: 'média',
      deadline: null,
      completed: false,
      status: 'Em andamento',
      responsavel: ''
    };
    this.editingTaskId = null;
  }

  validateTask(task: Task): boolean {
    return !!(task.title && task.description && task.responsavel && task.deadline);
  }

  logout(): void {
    this.loginService.logout();
  }

  changeDeadlineFilter(option: 'menor' | 'maior'): void {
    this.filterDeadlineOption = option;
    this.applyFilter();
  }
}
