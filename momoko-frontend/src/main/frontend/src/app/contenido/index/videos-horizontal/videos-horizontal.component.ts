
import { VideoYoutube } from 'app/services/youtube-api/youtube';
import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-videos-horizontal',
  templateUrl: './videos-horizontal.component.html',
  styleUrls: ['./videos-horizontal.component.css']
})
export class VideosHorizontalComponent implements OnInit, OnChanges {

  private log = environment.log;

  @Input() videos: VideoYoutube[];

  urlPagina = environment.momokoUrl;

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges() {
    if (this.log) {
      console.log('Los videos');
    }
    if (this.videos != null) {
      this.videos.forEach(video => {
        video.urlMomoko = '/videos/' + this.slugify(video.snippet.title);
      });
    }
  }

  slugify(text): string {
    text = text.replace(/^\s+|\s+$/g, ''); // trim
    text = text.toLowerCase();
    const from = 'ãàáäâẽèéëêìíïîõòóöôùúüûñç';
    const to = 'aaaaaeeeeeiiiiooooouuuunc';
    for (let i = 0, l = from.length; i < l; i++) {
      text = text.replace(new RegExp(from.charAt(i), 'g'), to.charAt(i));
    }

    return text.toString()
      .replace(/\s+/g, '-')           // Replace spaces with -
      .replace(/[^\w\-]+/g, '')       // Remove all non-word chars
      .replace(/\-\-+/g, '-')         // Replace multiple - with single -
      .replace(/^-+/, '')             // Trim - from start of text
      .replace(/-+$/, '');            // Trim - from end of text
  }

}
