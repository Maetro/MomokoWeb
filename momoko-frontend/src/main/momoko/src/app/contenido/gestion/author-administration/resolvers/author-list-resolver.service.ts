import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from "@angular/router";
import { Author } from "app/dtos/autor";
import { Observable } from "rxjs";
import { AuthorService } from "../service/author.service";
@Injectable({
  providedIn: "root"
})
export class AuthorListResolverService implements Resolve<Author[]> {
  constructor(
    private authorService: AuthorService,
    private router: Router
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Author[]> {
    return this.authorService.getAuthors();
  }
}