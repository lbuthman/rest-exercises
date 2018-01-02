import { Component, OnInit } from '@angular/core';
import {Visitor} from "../visitor.model";

@Component({
  selector: 'visitors-list',
  templateUrl: './visitors-list.component.html',
  styleUrls: ['./visitors-list.component.css']
})
export class VisitorsListComponent implements OnInit {

  visitors: Visitor[] = [];

  constructor() { }

  ngOnInit() {
    this.visitors.push(new Visitor("Luke", true));
    this.visitors.push(new Visitor("Albie", false));
    this.visitors.push(new Visitor("Julie", false));
  }

  onVisitorChange($event, visitor) {
    console.log("checkbox checked")
  }

}
