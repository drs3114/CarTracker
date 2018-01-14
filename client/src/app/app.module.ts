import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {
  MatButtonModule,
  MatCardModule,
  MatChipsModule,
  MatDialogModule,
  MatGridListModule,
  MatIconModule, MatListModule,
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
    DataTablesModule,
    AgmCoreModule.forRoot({apiKey: 'AIzaSyDfqFKXWLRFSg8UMeNdvNDsROxGz4C68j8'})
  ],
  providers: [CarService, AlertsService],
  entryComponents: [
    DashboardComponent,
    CarDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
