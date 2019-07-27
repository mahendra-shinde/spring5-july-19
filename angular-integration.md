## Angular Front end for Spring Rest

1. Create new Angular project

    ```yaml
    Project-Name:   myapp
    Stylesheet:     CSS
    Routing:        Yes
    ```

2.  Create new file `models/country.ts`

    ```ts
    export class Country {
        code:string;
        name:string;
        continent:string;
        capital:string;
    }

    ```

3.  Modify `app.module.ts`

    ```ts
    import { AppRoutingModule } from './app-routing.module';
    import { AppComponent } from './app.component';
    //NEW IMPORT
    import { HttpClient, HttpClientModule } from '@angular/common/http';

    @NgModule({
    declarations: [
        AppComponent  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule //NEW MODULE
    ],
    providers: [],
    bootstrap: [AppComponent]
    })
    export class AppModule { }
    ```

4.  Create new service `services/country.service.ts`

    ```ts
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
    ```
5.  Replace all the contents of `app.component.html` with this one:

    ```html
    <div>
        <h2>List of Countries in ASIA</h2>
        <table>
            <tr *ngFor="let c of countries">
            <td>{{c.code}}</td>
            <td>{{c.name}}</td>
            <td>{{c.continent}}</td>

            </tr>
        </table>
    </div>

    <router-outlet></router-outlet>
    ```

6.  Replace all the contents of `app.component.ts` with this one:

    ```ts
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
            },err => {
                console.log("Error Occured:" + err.error);
        });
    }
    }
    ```

7.  Use `ng serve --open` to run angular application.

    NOTE: Initially application would throw an eror with CROS Origins
          To fix the error, modify your REST controller inside spring project
          Use new class-level annotation `@CrossOrigin("http://localhost:4200")`

