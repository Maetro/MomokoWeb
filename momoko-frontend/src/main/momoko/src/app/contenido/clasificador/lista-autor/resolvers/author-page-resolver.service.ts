import { Injectable } from "@angular/core";
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { AuthorPageResponse } from "../dtos/author-page-response";
import { Observable } from "rxjs";
import { AuthorPageService } from "../service/author-page.service";


@Injectable({
    providedIn: "root"
  })
  export class AuthorPageResolverService implements Resolve<AuthorPageResponse> {
    constructor(
      private authorPageService: AuthorPageService,
      private router: Router,
    ) {}
  
    resolve(
      route: ActivatedRouteSnapshot,
      state: RouterStateSnapshot
    ): Observable<AuthorPageResponse> {
      const authorUrl = route.paramMap.get("url_author");
  
      return this.authorPageService.getAuthorPage(authorUrl);
    }
  }