import {Component, Inject, OnInit} from '@angular/core';
import {Car} from '../models/car';
import {CarService} from '../car.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {AlertsService} from '../alerts.service';
import {CarAlerts} from '../models/car.alerts';
import {Alert} from '../models/alert';
import {log} from 'util';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  cars: Car[];
  carAlerts: CarAlerts[] = [];

  constructor(private carService: CarService, private alertService: AlertsService, public dialog: MatDialog) {
  }

  getCars(): void {
    this.carService.getCars().subscribe(cars => {
      this.cars = cars;


      this.cars.forEach(car => {
        car.lastServiceDate = new Date(car.lastServiceDate);
        const carAlert = new CarAlerts(car);
        this.alertService.getHighAlerts(car).subscribe(alerts => {
          carAlert.highAlerts = alerts;
        });
        this.alertService.getMediumAlerts(car).subscribe(alerts => {
          carAlert.mediumAlerts = alerts;
        });
        this.alertService.getLowAlerts(car).subscribe(alerts => {
          carAlert.lowAlerts = alerts;
        });

        this.carAlerts.push(carAlert);
      });
    });
  }

  openCar(car: Car): void {
    this.dialog.open(CarDialogComponent, {width: '350px', data: car});
  }

  ngOnInit() {
    this.getCars();
  }

}

@Component({
  selector: 'app-dashboard-car',
  templateUrl: './dashboard.car.dialog.html',
  styleUrls: ['./dashboard.car.dialog.css']
})
export class CarDialogComponent {
  constructor(public carDialogRef: MatDialogRef<CarDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: Car) {
  }

  onNoClick(): void {
    this.carDialogRef.close();
  }
}
