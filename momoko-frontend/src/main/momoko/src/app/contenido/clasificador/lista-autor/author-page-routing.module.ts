import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { AuthorPageComponent } from "./author-page.component";
import { AuthorPageResolverService } from "./resolvers/author-page-resolver.service";


const authorPageRoutes = [
    {
      path: ':url_author',
      component: AuthorPageComponent,
      resolve: { authorPage: AuthorPageResolverService }
    },
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(authorPageRoutes)],
    exports: [RouterModule]
  })
  export class AuthorPageRoutingModule {
    constructor() {
    }
  }