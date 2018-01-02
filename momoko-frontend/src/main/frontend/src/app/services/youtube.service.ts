import { Injectable } from '@angular/core';
import { VideosYoutube } from 'app/services/youtube-api/youtube';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';

@Injectable()
export class YoutubeService {

  private log = environment.log;

  // tslint:disable-next-line:max-line-length
  youtubeURL = 'https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCJxqau4eHsx-pzYDdylD2PA&maxResults=*max_results*&order=date&type=video&key=AIzaSyCJrWHmLXtIAri-uhpJzOh30jdtZl03dgA';

  constructor(private http: HttpClient) { }

  getMomokoFeed(num: number): Observable<VideosYoutube> {
    const urlFinal = this.youtubeURL.replace('*max_results*', String(num));
    return this.http.get<VideosYoutube>(urlFinal).map(this.extractVideosYoutube)
    .catch(error => Observable.throw(error || 'Server error'));
  }

  private extractVideosYoutube(res: VideosYoutube) {
    return res;
  }

}
