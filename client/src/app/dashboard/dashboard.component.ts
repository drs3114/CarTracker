import {Component, Inject, OnInit} from '@angular/core';
import {Car} from '../models/car';
import {CarService} from '../car.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material';
import {selector} from 'rxjs/operator/publish';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  cars: Car[];

  constructor(private carService: CarService, public dialog: MatDialog) {
  }

  getCars(): void {
    this.carService.getCars().subscribe(cars => {
      this.cars = cars;
      this.cars.forEach(car => {
        car.lastServiceDate = new Date(car.lastServiceDate);
      });
    });
  }

  openCar(car: Car): void {
    this.dialog.open(CarDialogComponent, {width: '300px', data: car});
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
