export interface Task {
  id?: string;
  title: string;
  description: string;
  priority: string;
  deadline: string | null;
  completed: boolean;
  status: 'Em andamento' | 'Concluída';
  responsavel: string;
}
