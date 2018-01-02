
import {Injectable} from "@angular/core";
import "rxjs/add/operator/map"
import {Http} from "@angular/http";

@Injectable()
export class VisitorService {

  constructor(private http: Http) {
  }

  getVisitors() {
    return this.http.get('/api/v1/visitors').map(response => response.json());
  }
}
