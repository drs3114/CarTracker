import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {MatButtonModule, MatCardModule, MatChipsModule, MatDialogModule, MatGridListModule, MatToolbarModule} from '@angular/material';
import {CarDialogComponent, DashboardComponent} from './dashboard/dashboard.component';
import {FlexLayoutModule} from '@angular/flex-layout';
import {CarService} from './car.service';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './/app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AlertsComponent } from './alerts/alerts.component';
import { AlertsService } from './alerts.service';


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
    MatChipsModule
  ],
  providers: [CarService, AlertsService],
  entryComponents: [
    DashboardComponent,
    CarDialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
