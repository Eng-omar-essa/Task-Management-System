import { AppRoutingModule } from './app.routes';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpModule } from '@angular/http';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { TaskComponent } from './component/task/task.component';
import { AddTaskComponent } from './component/task/add-task/add-task.component';
import { EditTaskComponent } from './component/task/edit-task/edit-task.component';
import { ListTaskComponent } from './component/task/list-task/list-task.component';

import { DatepickerModule , TimepickerModule, ModalModule } from 'ngx-bootstrap';
import { CommonModule } from '@angular/common';

import { Ng2Bs3ModalModule } from 'ng2-bs3-modal/ng2-bs3-modal';
import { NgxPaginationModule } from 'ngx-pagination';
import { TaskService } from './services/task.service';
import { DateFormatPipe } from './component/pipes/date-format.pipe';
import { SharedService } from './services/shared.service';
import { KeysPipe } from './component/pipes/keys-pipe';
import { ClickOutsideModule } from 'ng-click-outside';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TaskComponent,
    AddTaskComponent,
    EditTaskComponent,
    ListTaskComponent,
    KeysPipe,
    DateFormatPipe,
  ],
  exports: [KeysPipe],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    ReactiveFormsModule,
    NgbModule.forRoot(),
    Ng2Bs3ModalModule,
    NgxPaginationModule,
    DatepickerModule.forRoot(),
    TimepickerModule.forRoot(),
    CommonModule,
    ClickOutsideModule,
    FormsModule,
    HttpModule,

  ],
  providers: [TaskService, DateFormatPipe , ClickOutsideModule , SharedService],
  bootstrap: [AppComponent]
})
export class AppModule { }
