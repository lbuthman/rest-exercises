
import {EventEmitter, Injectable} from "@angular/core";
import "rxjs/add/operator/map"
import {Http} from "@angular/http";
import {Visitor} from "./visitor.model";

@Injectable()
export class VisitorService {

  onVisitorAdded = new EventEmitter<Visitor>();

  constructor(private http: Http) {
  }

  getVisitors() {
    return this.http.get('/api/v1/visitors').map(response => response.json());
  }

  addVisitor(visitor: Visitor) {
    return this.http.post("/api/v1/visitors", visitor).map(response => response.json());
  }

  editVisitor(visitor: Visitor) {

  }

  deleteVisitor(visitor: Visitor) {
    let path = "/api/v1/visitors/" + visitor.id;
    return this.http.delete(path);
  }
}
