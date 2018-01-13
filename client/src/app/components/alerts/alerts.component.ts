import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {CarService} from '../../services/car.service';
import {AlertsService} from '../../services/alerts.service';
import {Car} from '../../models/car';
import {Alert} from '../../models/alert';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {MatPaginator, MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.css']
})
export class AlertsComponent implements OnInit, AfterViewInit {

  car: Car = new Car();
  highPriorityAlerts: Alert[] = [];
  mediumPriorityAlerts: Alert[] = [];
  lowPriorityAlerts: Alert[] = [];

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
    });
  }

  getMediumPriorityAlerts(): void {
    this.alertService.getMediumAlerts(this.car).subscribe(alerts => {
      this.mediumPriorityAlerts = alerts;
      this.mediumPriorityAlerts.forEach(alert => {
        alert.time = this.formatDateTime(alert.time);
      });
    });
  }

  getLowPriorityAlerts(): void {
    this.alertService.getLowAlerts(this.car).subscribe(alerts => {
      this.lowPriorityAlerts = alerts;
      this.lowPriorityAlerts.forEach(alert => {
        alert.time = this.formatDateTime(alert.time);
      });
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

  ngAfterViewInit() {
  }

  ngOnInit() {
    this.fetch();
  }

}
