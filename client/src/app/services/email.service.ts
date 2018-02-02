import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Email} from '../models/email';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class EmailService {

  private notificationUrl = 'http://localhost:8080/tracker/api/notification';

  constructor(private http: HttpClient) {
  }

  sendEmail(email: Email): void {
    this.http.post(this.notificationUrl, email).subscribe();
  }
}
