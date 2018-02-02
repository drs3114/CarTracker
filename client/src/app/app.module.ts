import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDialogModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule, MatInputModule,
  MatListModule,
  MatSelectModule,
  MatSlideToggleModule,
  MatTabsModule,
  MatToolbarModule
} from '@angular/material';
import {CarDialogComponent, DashboardComponent} from './components/dashboard/dashboard.component';
import {CarService} from './services/car.service';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './/app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AlertsComponent} from './components/alerts/alerts.component';
import {AlertsService} from './services/alerts.service';
import {DataTablesModule} from 'angular-datatables';
import {AgmCoreModule} from '@agm/core';
import {ReadingsService} from './services/readings.service';
import {ChartsModule} from 'ng2-charts';
import {ChartComponent} from './components/chart/chart.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EmailComponent} from './components/email/email.component';
import {EmailService} from './services/email.service';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    CarDialogComponent,
    AlertsComponent,
    ChartComponent,
    EmailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatToolbarModule,
    MatGridListModule,
    MatCardModule,
    MatButtonModule,
    MatDialogModule,
    AppRoutingModule,
    MatChipsModule,
    MatTabsModule,
    MatIconModule,
    MatListModule,
    MatSlideToggleModule,
    MatCheckboxModule,
    MatSelectModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    DataTablesModule,
    ChartsModule,
    AgmCoreModule.forRoot({apiKey: 'AIzaSyDfqFKXWLRFSg8UMeNdvNDsROxGz4C68j8'})
  ],
  providers: [CarService, AlertsService, ReadingsService, EmailService],
  entryComponents: [
    DashboardComponent,
    CarDialogComponent,
    ChartComponent,
    EmailComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
