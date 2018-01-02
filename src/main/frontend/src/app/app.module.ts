import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule} from "@angular/http";

import { AppComponent } from './app.component';
import { VisitorsComponent } from './visitors/visitors.component';
import { VisitorsAddComponent } from './visitors/visitors-add/visitors-add.component';
import { VisitorsListComponent } from './visitors/visitors-list/visitors-list.component';
import {VisitorService} from "./visitors/visitor.service";
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AppComponent,
    VisitorsComponent,
    VisitorsAddComponent,
    VisitorsListComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule
  ],
  providers: [VisitorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
