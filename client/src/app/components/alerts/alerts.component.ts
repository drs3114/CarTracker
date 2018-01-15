import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {CarService} from '../../services/car.service';
import {AlertsService} from '../../services/alerts.service';
import {Car} from '../../models/car';
import {Alert} from '../../models/alert';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Subject} from 'rxjs/Subject';
import {MatDialog} from '@angular/material';
import {Reading} from '../../models/reading';
import {ReadingsService} from '../../services/readings.service';
import {ChartComponent} from '../chart/chart.component';

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
  readings: Reading[] = [];
  highAlertDtTrigger: Subject<any> = new Subject<any>();
  mediumAlertDtTrigger: Subject<any> = new Subject<any>();
  lowAlertDtTrigger: Subject<any> = new Subject<any>();
  readingsDtTrigger: Subject<any> = new Subject<any>();
  readingSelected = false;
  lat = 0;
  long = 0;

  constructor(private carService: CarService,
              private alertService: AlertsService,
              private readingService: ReadingsService,
              private route: ActivatedRoute,
              private location: Location,
              public dialog: MatDialog) {
  }

  fetch(): void {
    const vin = this.route.snapshot.paramMap.get('vin');
    this.carService.getCar(vin).subscribe(car => {
      this.car = car;
      this.car.lastServiceDate = new Date(this.car.lastServiceDate);
      this.getHighPriorityAlerts();
      this.getMediumPriorityAlerts();
      this.getLowPriorityAlerts();
      this.getReadings();
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
      this.highPriorityAlerts.reverse();
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
      this.mediumPriorityAlerts.reverse();
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
      this.lowPriorityAlerts.reverse();
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

  getReadings(): void {
    this.readingService.getReadingsForVin(this.car.vin).subscribe(readings => {
      this.readings = readings;
      this.readings.forEach(reading => {
        reading.timestamp = new Date(reading.timestamp);
      });
      this.readings.sort((a, b) => {
        return a.timestamp - b.timestamp;
      });
      this.readings.reverse();
      this.readingsDtTrigger.next();
    });
  }

  updateMaps(reading: Reading): void {
    this.readingSelected = true;
    this.lat = reading.latitude;
    this.long = reading.longitude;
  }

  displayChart(): void {
    this.dialog.open(ChartComponent, {width: '800px', data: this.readings});
  }

  ngOnInit() {
    const that = this;
    this.fetch();
  }

}
