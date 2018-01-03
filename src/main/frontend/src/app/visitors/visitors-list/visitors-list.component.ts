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
    visitor.greeted = visitor.greeted != true;
  }

  onDeleteClick(visitor) {
    this.visitorService.deleteVisitor(visitor)
      .subscribe(() => {
          let index = this.visitors.indexOf(visitor);
          this.visitors.splice(index, 1);
        }
      );
  }

  onEditClick(visitor) {
    this.visitorService.editVisitor(visitor)
      .subscribe(
        (visitor: Visitor) => {
          // let index = this.visitors.indexOf(visitor);
          // this.visitors[index] = new Visitor("Hello!", true);
          // this.visitorService.onVisitorAdded.emit(this.visitors[index]);
        }
      )
  }

}
