import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Car} from './models/car';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class CarService {

  private carsUrl = 'http://localhost:8080/tracker/api/vehicles';

  constructor(private http: HttpClient) {
  }

  getCars(): Observable<Car[]> {
    return this.http.get<Car[]>(this.carsUrl);
  }

}
