import { Component, OnInit } from '@angular/core';
import { Country } from './models/country';
import { CountryService } from './services/country.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'myapp';

  constructor(private service:CountryService){ }

  countries: Country[];

  ngOnInit(){
    this.service.findByContinent('asia').
    subscribe((result:Country[])=>{
      this.countries = result;
      console.log("Fetched records for countries: "+result.length);
    },err=>{
      console.log("Error Occured:" + err.error);
    });
  }
}
