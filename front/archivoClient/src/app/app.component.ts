import { Component } from '@angular/core';
import { MediaService } from './media.service';
import { TimeWord } from './models/timeWord';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  timeResponse?: TimeWord[];
  time: TimeWord = new TimeWord()
  visible: boolean = false;

  constructor(
    private mediaService: MediaService
  ){}

  uploadFile(event: any) {
    const file = event.target.files[0];

    if (file){
      const formData = new FormData();
      formData.append('file', file);

      this.mediaService.uploadFile(formData)
      .subscribe((response: TimeWord[]) => {
        this.timeResponse = response;
        if(this.timeResponse.length >0){
          this.visible = true;
        }
        //console.log('response',response);
        //console.log('tamanio:',this.timeResponse.length);
      })
    }
  }
}
