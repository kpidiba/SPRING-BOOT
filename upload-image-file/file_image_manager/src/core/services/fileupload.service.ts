import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiResponse } from '../interfaces/ApiResponse';
import { environment } from 'src/environments/environment.development';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root',
  
})
export class FileuploadService {
  baseUrl = environment.apiUrl + "images";
  constructor(private http: HttpClient) {

  }

  uploadFile(register: FormGroup) {
    const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }
    this.http.post<ApiResponse>(`${this.baseUrl}/upload`,JSON.stringify(register),httpOptions).subscribe(
      {
        next:()=>{
          console.log("ca marche");
        },error:(error)=>{
          console.log(error);
        }
      }
    )
  }

}
