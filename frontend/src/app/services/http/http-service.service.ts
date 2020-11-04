import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/User";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http:HttpClient) { }
  saveUser(user:User):Observable<User> {
    return this.http.post<User>("/user/save", user);
  }
  login(user:User):Observable<User> {
    return this.http.post<User>("/user/login", user);
  }
}
