import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {
  MatButtonModule,
  MatCardModule, MatCheckboxModule,
  MatChipsModule,
  MatDialogModule,
  MatGridListModule,
  MatIconModule, MatListModule, MatSlideToggleModule,
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


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    CarDialogComponent,
    AlertsComponent
  ],
  imports: [
    BrowserModule,
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
    DataTablesModule,
    AgmCoreModule.forRoot({apiKey: 'AIzaSyDfqFKXWLRFSg8UMeNdvNDsROxGz4C68j8'})
  ],
  providers: [CarService, AlertsService, ReadingsService],
  entryComponents: [
    DashboardComponent,
    CarDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
