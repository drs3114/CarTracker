import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Alert} from '../models/alert';
import {Car} from '../models/car';

@Injectable()
export class AlertsService {

  private alertUrlPrefix = 'http://localhost:8080/tracker/api/vehicles/';


  constructor(private http: HttpClient) {
  }

  getHighAlerts(car: Car): Observable<Alert[]> {
    const alertUrl = this.alertUrlPrefix + car.vin + '/alerts?priority=HIGH';
    return this.http.get<Alert[]>(alertUrl);
  }

  getLowAlerts(car: Car): Observable<Alert[]> {

    const alertUrl = this.alertUrlPrefix + car.vin + '/alerts?priority=LOW';
    return this.http.get<Alert[]>(alertUrl);
  }

  getMediumAlerts(car: Car): Observable<Alert[]> {

    const alertUrl = this.alertUrlPrefix + car.vin + '/alerts?priority=MEDIUM';
    return this.http.get<Alert[]>(alertUrl);
  }

}
