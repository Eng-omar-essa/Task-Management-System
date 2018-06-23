import { Observable } from 'rxjs/Observable';
import { Observable } from 'rxjs';
import { TaskService } from './../../../services/task.service';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTaskComponent } from './add-task.component';
import { Task } from '../../../model/Task.model';

describe('AddTaskComponent', () => {
  let component: AddTaskComponent;
  let fixture: ComponentFixture<AddTaskComponent>;
  let taskService: TaskService;
  let task: Task = {createdAt: Fri Jun 22 2018 20:15:37 GMT+0200 (Egypt Standard Time), updatedAt: null, dueDate: "2018-06-04 00:00:00", resolvedAt: "2018-06-23 00:00:00", title: "efwtr", …};
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTaskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call server  SaveTask', () => {
    let error = 'error form server';
    let spy =  spyOn(taskService,'saveTask').and.returnValue(Observable.throw(error));
    component.addTaskSubmit(task,true);
    expect(component.err).toBe(error);
  });
});
