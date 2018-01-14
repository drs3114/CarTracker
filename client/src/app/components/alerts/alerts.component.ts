import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {CarService} from '../../services/car.service';
import {AlertsService} from '../../services/alerts.service';
import {Car} from '../../models/car';
import {Alert} from '../../models/alert';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Subject} from 'rxjs/Subject';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.css']
})
export class AlertsComponent implements OnInit {

  car: Car = new Car();
  highPriorityAlerts: Alert[] = [];
  mediumPriorityAlerts: Alert[] = [];
  lowPriorityAlerts: Alert[] = [];
  highAlertDtTrigger: Subject<any> = new Subject();
  mediumAlertDtTrigger: Subject<any> = new Subject();
  lowAlertDtTrigger: Subject<any> = new Subject();

  title = 'My first AGM project';
  lat = 51.678418;
  lng = 7.809007;

  constructor(private carService: CarService,
              private alertService: AlertsService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  fetch(): void {
    const vin = this.route.snapshot.paramMap.get('vin');
    this.carService.getCar(vin).subscribe(car => {
      this.car = car;
      this.getHighPriorityAlerts();
      this.getMediumPriorityAlerts();
      this.getLowPriorityAlerts();
    });
  }

  getHighPriorityAlerts(): void {
    this.alertService.getHighAlerts(this.car).subscribe(alerts => {
      this.highPriorityAlerts = alerts;
      this.highPriorityAlerts.forEach(alert => {
        alert.time = this.formatDateTime(alert.time);
      });
      this.highPriorityAlerts.sort((a, b) => {
        return a.time - b.time;
      });
      this.highAlertDtTrigger.next();
    });
  }

  getMediumPriorityAlerts(): void {
    this.alertService.getMediumAlerts(this.car).subscribe(alerts => {
      this.mediumPriorityAlerts = alerts;

      this.mediumPriorityAlerts.forEach(alert => {
        alert.time = this.formatDateTime(alert.time);
      });

      this.mediumPriorityAlerts.sort((a, b) => {
        return a.time - b.time;
      });
      this.mediumAlertDtTrigger.next();
    });
  }

  getLowPriorityAlerts(): void {
    this.alertService.getLowAlerts(this.car).subscribe(alerts => {
      this.lowPriorityAlerts = alerts;
      this.lowPriorityAlerts.forEach(alert => {
        alert.time = this.formatDateTime(alert.time);
      });
      this.lowPriorityAlerts.sort((a, b) => {
        return a.time - b.time;
      });
      this.lowAlertDtTrigger.next();
    });
  }

  formatDateTime(dateTime: any): Date {
    return new Date('' + dateTime.month +
      ' ' + dateTime.dayOfMonth + ', '
      + dateTime.year + ' ' +
      dateTime.hour + ':' +
      dateTime.minute + ':' +
      dateTime.second);
  }

  ngOnInit() {
    const that = this;
    this.fetch();
  }

}
