import { VideoYoutube } from 'app/services/youtube-api/youtube';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-videos-horizontal',
  templateUrl: './videos-horizontal.component.html',
  styleUrls: ['./videos-horizontal.component.css']
})
export class VideosHorizontalComponent implements OnInit {

  @Input() videos: VideoYoutube[];

  constructor() { }

  ngOnInit() {
  }


}
