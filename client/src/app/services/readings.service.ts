import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Reading} from '../models/reading';

@Injectable()
export class ReadingsService {

  private readingsUrl = 'http://localhost:8080/tracker/api/readings/';

  constructor(private http: HttpClient) {
  }

  getReadingsForVin(vin: string): Observable<Reading[]> {
    const url = this.readingsUrl + vin;
    return this.http.get<Reading[]>(url);
  }

}
