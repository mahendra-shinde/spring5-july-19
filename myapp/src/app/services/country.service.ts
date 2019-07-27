import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Country } from '../models/country';

let URL = "http://localhost:5000/countries/";

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private http:HttpClient) { 

  }

  findByContinent(continent:String):Observable<any>{
    let options = { "headers": 
                  new HttpHeaders({ "Content-Type": "application/json" }) };
    return this.http.get(URL+"continent-"+continent,options);
  }
}
