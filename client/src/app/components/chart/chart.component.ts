import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Reading} from '../../models/reading';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  public lineChartOptions: any = {
    responsive: true
  };

  public lineChartColors: Array<any> = [
    {
      backgroundColor: 'rgba(143, 19, 129, 0.5)',
      borderColor: 'rgba(140, 114, 137,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLabels: Array<any> = [];
  public lineChartLegend = true;
  public lineChartType = 'line';
  public fuelData: Array<number>;
  public speedData: Array<number>;
  public engineHpData: Array<number>;
  public engineRpmData: Array<number>;
  public selectedValue: string;
  public chartTime: number;
  public timeRanges = [15, 30, 45, 60];
  public viewing = false;
  public chartData = [];
  public chartTypes = [
    {name: 'Fuel', value: this.fuelData},
    {name: 'Speed', value: this.speedData},
    {name: 'Engine HP', value: this.engineHpData},
    {name: 'Engine RPM', value: this.engineRpmData}
  ];
  public lineChartData: Array<any>;

  constructor(public charDialogRef: MatDialogRef<ChartComponent>, @Inject(MAT_DIALOG_DATA) public data: Array<Reading>) {
  }

  selectData(minutes: number): void {
    const HALF_AN_HOUR = minutes * 60 * 1000;
    const now: any = new Date();
    this.fuelData = [];
    this.speedData = [];
    this.engineHpData = [];
    this.engineRpmData = [];
    this.lineChartLabels = [];
    this.data.forEach(reading => {
      if ((now - reading.timestamp) <= HALF_AN_HOUR) {
        this.fuelData.push(reading.fuelVolume);
        this.speedData.push(reading.speed);
        this.engineHpData.push(reading.engineHp);
        this.engineRpmData.push(reading.engineRpm);
        this.lineChartLabels.push(reading.timestamp.toLocaleString());
      }
    });
  }

  viewChart(name: string, minutes: number): void {
    this.selectData(minutes);
    switch (name) {
      case 'Fuel':
        this.chartData = this.fuelData;
        break;
      case 'Speed':
        this.chartData = this.speedData;
        break;
      case 'Engine HP':
        this.chartData = this.engineHpData;
        break;
      case 'Engine RPM':
        this.chartData = this.engineRpmData;
    }
    this.viewing = true;
    this.lineChartData = [{data: this.chartData, label: this.selectedValue}];
  }

  close(): void {
    this.charDialogRef.close();
  }

  ngOnInit() {

  }

}
