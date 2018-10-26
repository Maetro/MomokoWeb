import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from "@angular/router";
import { Author } from "app/dtos/autor";
import { Observable } from "rxjs";
import { AuthorService } from "../service/author.service";

@Injectable({
  providedIn: "root"
})
export class EditAuthorResolverService implements Resolve<Author> {
  constructor(
    private authorService: AuthorService,
    private router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Author> {
    const authorUrl = route.paramMap.get("url");
    return this.authorService.getAuthorByUrl(authorUrl);
  }
}