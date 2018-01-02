import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class VisitorService {

  constructor(private http: HttpClient) {
  }

  getVisitors() {
    return this.http.get('/api/v1/visitors').forEach(response => response);
  }
}
