import { Component, OnInit, TemplateRef } from '@angular/core';
import { Ng2Bs3ModalModule } from 'ng2-bs3-modal/ng2-bs3-modal';
import { NgxPaginationModule } from 'ngx-pagination';
import { Task } from '../../../model/Task.model';
import { AnonymousSubscription } from 'rxjs/Subscription';
import { SharedService } from '../../../services/shared.service';
import { TaskService } from '../../../services/task.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TaskStatus } from '../../enum/task.status';
import { BsModalRef } from 'ngx-bootstrap';


@Component({
  selector: 'app-list-task',
  templateUrl: './list-task.component.html',
  styleUrls: ['./list-task.component.css'],
  providers : [Ng2Bs3ModalModule, NgxPaginationModule],
})
export class ListTaskComponent implements OnInit {


  tasks: Task[] = [];
  timerSubscription: AnonymousSubscription;
  postsSubscription: AnonymousSubscription;
  updateTask: SharedService;
  isDelete: boolean;
  public modalRef: BsModalRef;
  visibleAnimate: boolean;
  visible: boolean;
  constructor(private taskService: TaskService , private router: Router , private sharedService: SharedService) {
      this.updateTask = sharedService;
  }

  ngOnInit() {
    this.refreshData();
    this.updateTask.getEmittedUpdateValue().subscribe(oldItem => this.refreshData());
  }

  OnDestroy(): void {
    if (this.postsSubscription) {
      this.postsSubscription.unsubscribe();
    }
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }

  refreshData() {
    this.taskService.getAllTasks('').subscribe(tasks => {
      if (Object.keys(tasks).length > 0) {
        this.tasks = tasks;
      } else {
        this.tasks = [];
      }
      this.subscribeToData();
    });
  }

  subscribeToData() {
    this.timerSubscription = Observable.timer(5000).first().subscribe(() => this.refreshData());
  }

  getDueDateLabel(task: Task) {
    return task.taskStatus === TaskStatus.COMPLETED ? 'label-success' : 'label-primary';
  }

  onTaskCreated(task: Task) {
      this.tasks.push(task);
  }

  deleteTask(task: Task) {
  this.tasks = this.tasks.filter(function(el) {
        return el.id !== task.id;
  });
  }
}
