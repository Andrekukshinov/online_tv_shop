import { Injectable } from '@angular/core';
import {HttpServiceService} from "../http/http-service.service";
import {Observable} from "rxjs";
import {User} from "../../models/User";

@Injectable({
  providedIn: 'root'
})
export class UsersServiceService {

  constructor(private httpService:HttpServiceService) { }

  public saveNewUser(user:User):Observable<User>{
    return this.httpService.saveUser(user);
  }
  public login(user:User):Observable<User>{
    return this.httpService.login(user);
  }
}
