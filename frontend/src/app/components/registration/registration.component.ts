import {AfterViewChecked, Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/User";
import {Observable} from "rxjs";
import {UsersServiceService} from "../../services/User/users-service.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit,  AfterViewChecked{

  private newUser: User;
  private doPasswordsDiffer: boolean = true;

  userForm:FormGroup;
  constructor(private formBuilder:FormBuilder, private userService:UsersServiceService) { }


  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      name: new FormControl('',[Validators.required, Validators.minLength(3)]),
      surname: new FormControl('', Validators.required),
      lastname: new FormControl(''),
      email: new FormControl('',[Validators.required, Validators.email]),
      password: new FormControl('', [Validators.minLength(5), Validators.required]),
      confirmPass: new FormControl('', [Validators.minLength(5), Validators.required ]),
      phone: new FormControl('')
    })
  }
  doPassDiffer():boolean{
    if(this.userForm.value.password !=='') {
      return this.doPasswordsDiffer = this.userForm.value.password !== this.userForm.value.confirmPass
        && this.userForm.value.password !== '';
    }
    this.doPasswordsDiffer = false;
    return this.doPasswordsDiffer;
  }

  sendData():void{
    this.newUser = new User();
    this.newUser.name = this.userForm.get('name').value;
    this.newUser.surname = this.userForm.get('surname').value;
    this.newUser.lastname = this.userForm.get('lastname').value;
    this.newUser.email = this.userForm.get('email').value;
    this.newUser.password = this.userForm.get('password').value;
    this.newUser.phone = this.userForm.get('phone').value;
    this.userService.saveNewUser(this.newUser).subscribe(user => this.newUser = user);
  }
  ngAfterViewChecked(): void {
    this.doPassDiffer();
  }


}
