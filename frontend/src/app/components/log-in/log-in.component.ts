import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UsersServiceService} from "../../services/User/users-service.service";
import {User} from "../../models/User";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  private user:User;
  @Input()
  public isLoginFormShown: boolean = false;

  public loginForm: FormGroup = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  })

  constructor(private userService:UsersServiceService) {
  }

  ngOnInit(): void {
  }

  @Output()
  close:EventEmitter<boolean> = new EventEmitter<boolean>();

  closeModal():void{
    this.close.emit(this.isLoginFormShown);
    console.log("child " + this.isLoginFormShown);
  }

  authorize():void{
    this.user.password = this.loginForm.get('password').value;
    this.user.email = this.loginForm.get('password').value;
    this.userService.login(this.user);
  }

}
