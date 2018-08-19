import { throwError as observableThrowError, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { VideosYoutube } from './youtube-api/youtube';
import { map, catchError } from 'rxjs/operators';

@Injectable()
export class YoutubeService {
  private log = environment.log;

  // tslint:disable-next-line:max-line-length
  youtubeURL =
    'https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCJxqau4eHsx-pzYDdylD2PA&maxResults=*max_results*&order=date&type=video&key=AIzaSyCJrWHmLXtIAri-uhpJzOh30jdtZl03dgA';

  constructor(private http: HttpClient) {}

  getMomokoFeed(num: number): Observable<VideosYoutube> {
    const urlFinal = this.youtubeURL.replace('*max_results*', String(num));
    return this.http.get<VideosYoutube>(urlFinal).pipe(
      map(this.extractVideosYoutube),
      catchError(error => observableThrowError(error || 'Server error'))
    );
  }

  private extractVideosYoutube(res: VideosYoutube) {
    return res;
  }
}
