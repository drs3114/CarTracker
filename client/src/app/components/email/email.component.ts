import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Car} from '../../models/car';
import {Email} from '../../models/email';
import {EmailService} from '../../services/email.service';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit {

  public from: string;
  public to: string;
  public subject: string;
  public body: string;

  constructor(public emailDialogRef: MatDialogRef<EmailComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Car,
              private emailService: EmailService) {
  }

  email = new FormControl('', [Validators.required, Validators.email]);

  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
      this.email.hasError('email') ? 'Not a valid email' :
        '';
  }

  close(): void {
    this.emailDialogRef.close();
  }

  prepare(): void {
    this.subject = `Alert notification for ${this.data.make} ${this.data.model}`;
    this.body = `Hello!

This is to Notify you about the the car you are driving.
There has been an issue recorded and it needs your immediate attention.
Please call us at +1-555-555-5555 for further assistance

Regards,
Support Team`;
  }

  sendEmail(): void {
    const emailObj = new Email();
    emailObj.fromEmail = this.from;
    emailObj.toEmail = this.to;
    emailObj.subject = this.subject;
    emailObj.body = this.body;
    emailObj.vehicle = this.data;

    this.emailService.sendEmail(emailObj);
  }


  ngOnInit() {
    this.prepare();
  }

}
