import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoginFormShown: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  changeFormVisibility(close:boolean):boolean{
    this.isLoginFormShown = !this.isLoginFormShown;
    console.log("parent " + this.isLoginFormShown);
    return this.isLoginFormShown;
  }
}
