import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TimeWord } from './models/timeWord';

@Injectable({
  providedIn: 'root'
})
export class MediaService {
  private urlEndPoint: string= 'http://localhost:8080/archivo/process';

  constructor(
    private http: HttpClient
  ) { }

  uploadFile(formData: FormData): Observable<TimeWord[]>{
    return this.http.post <TimeWord[]>(this.urlEndPoint, formData);
  }
}
