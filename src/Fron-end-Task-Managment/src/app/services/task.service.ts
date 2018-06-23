import { Injectable } from '@angular/core';
import { Task } from '../model/Task.model';
import { configuration } from '../component/config/configuration';
import { Http } from '@angular/http';
import 'rxjs/Rx';
import { DateFormatPipe } from '../component/pipes/date-format.pipe';
@Injectable()
export class TaskService {
  private  mainURI = configuration.serviceUrl;

  toUTCDate = function(date) {
    const _utc = new Date(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(),
      date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
    return _utc;
  };

  constructor(private http: Http, private dateFormatPipe: DateFormatPipe) {
  }

  getTask(UUID: String) {
    return this.http.get(this.mainURI + '/' + UUID)
      .map(
        (response) => {
          return response.json() ? response.json() : {};
        }
      );
  }

  getAllTasks(params: string) {
    return this.http.get(this.mainURI + params)
        .map(
          (response) => {
            return response.json() ? response.json() : {};
          }
        );
  }

  saveTask(task: Task, checked: boolean) {

    task = new Task(this.dateFormatPipe.transform(task.createdAt), this.dateFormatPipe.transform(task.updatedAt),
      this.dateFormatPipe.transform(task.dueDate), this.dateFormatPipe.transform(task.resolvedAt), task.title, task.description,
        task.priority, task.taskStatus, task.id, task.version, this.dateFormatPipe.transform(task.reminderAt));
    return this.http.post(this.mainURI, task);
  }

  updateTask(task: Task, checked: boolean) {

    task = new Task(this.dateFormatPipe.transform(task.createdAt),
      this.dateFormatPipe.transform(this.toUTCDate(new Date()).toString().toString()),
      this.dateFormatPipe.transform(task.dueDate), this.dateFormatPipe.transform(task.resolvedAt), task.title, task.description,
      task.priority, task.taskStatus, task.id, task.version, this.dateFormatPipe.transform(task.reminderAt));

    if (checked) {
      return this.http.put(this.mainURI + '/' + task.id, task)
        .map(
          (response) => {
            return response.json() ? response.json() : {};
          }
        );
    }

  }


}
