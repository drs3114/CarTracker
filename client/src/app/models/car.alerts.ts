import {Car} from './car';
import {Alert} from './alert';

export class CarAlerts {
  car: Car;
  highAlerts: Alert[];
  mediumAlerts: Alert[];
  lowAlerts: Alert[];

  constructor(car: Car) {
    this.car = car;
  }
}
