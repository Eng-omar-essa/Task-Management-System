import { Routes, RouterModule } from '@angular/router';
import { TaskComponent } from './component/task/task.component';
import { NgModule } from '@angular/core';
import { AddTaskComponent } from './component/task/add-task/add-task.component';

const router: Routes = [

  { path: '' , component : TaskComponent },
  { path: 'addTask' , component : AddTaskComponent }

];

@NgModule({
    imports: [RouterModule.forRoot(router)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {
  }

