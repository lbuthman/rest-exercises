import { Component, OnInit } from '@angular/core';
import {VisitorService} from "../visitor.service";
import {Visitor} from "../visitor.model";

@Component({
  selector: 'visitors-add',
  templateUrl: './visitors-add.component.html',
  styleUrls: ['./visitors-add.component.css']
})
export class VisitorsAddComponent implements OnInit {

  addVisitorValue: string = null;

  constructor(private visitorService: VisitorService) { }

  ngOnInit() {
  }

  onVisitorAdd(event) {
    let visitor: Visitor = new Visitor(event.target.value, false);

    this.visitorService.addVisitor(visitor)
      .subscribe(
        (newVisitor: Visitor) => {
          // clear the input
          this.addVisitorValue = " ";
          this.visitorService.onVisitorAdded.emit(newVisitor);
        }
      )
  }

}
