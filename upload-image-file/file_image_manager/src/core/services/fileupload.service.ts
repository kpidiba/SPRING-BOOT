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
    const formData = new FormData();
    formData.append('file', register.get('file')?.value);
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    this.http.post<ApiResponse>(`${this.baseUrl}/upload`, formData,{headers}).subscribe(
      {
        next: (message) => {
          console.log(message);
        }, error: (error) => {
          console.log(error);
        }
      }
    )
  }

}
