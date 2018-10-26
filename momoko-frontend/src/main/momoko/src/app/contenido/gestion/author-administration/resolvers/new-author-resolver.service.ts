import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from "@angular/router";
import { AuthorService } from "../service/author.service";

@Injectable({
  providedIn: "root"
})
export class NewAuthorResolverService implements Resolve<String> {
  constructor(
    private authorService: AuthorService,
    private router: Router,
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): String {
    return 'Done';
  }
}