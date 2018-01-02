import { Component, OnInit } from '@angular/core';
import {Visitor} from "../visitor.model";
import {VisitorService} from "../visitor.service";

@Component({
  selector: 'visitors-list',
  templateUrl: './visitors-list.component.html',
  styleUrls: ['./visitors-list.component.css']
})
export class VisitorsListComponent implements OnInit {

  visitors: Visitor[] = [];

  constructor(private visitorService: VisitorService) { }

  ngOnInit() {
    this.visitorService.getVisitors()
      .subscribe(
        (visitors: any[]) => {
          this.visitors = visitors;
        },
        (error) => console.log(error)
      );

    this.visitorService.onVisitorAdded.subscribe(
      (visitor: Visitor) => this.visitors.push(visitor)
    );
  }

  onVisitorChange($event, visitor) {
    console.log("checkbox checked")
  }

}
