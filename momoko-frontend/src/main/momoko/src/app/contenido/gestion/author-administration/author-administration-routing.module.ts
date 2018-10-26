import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { AuthorFormComponent } from "./author-form/author-form.component";
import { AuthorListComponent } from "./author-list/author-list.component";
import { AuthorListResolverService } from "./resolvers/author-list-resolver.service";
import { EditAuthorResolverService } from "./resolvers/edit-author-resolver.service";
import { NewAuthorResolverService } from "./resolvers/new-author-resolver.service";

const administrationUsersRoutes = [
    {
      path: '',
      component: AuthorListComponent,
      pathMatch: 'full',
      resolve: { roles: AuthorListResolverService }
    },
    {
      path: 'nuevo',
      component: AuthorFormComponent,
      pathMatch: 'full',
      resolve: { data: NewAuthorResolverService }
    },
    {
      path: 'editar/:url',
      component: AuthorFormComponent,
      pathMatch: 'full',
      resolve: { data: EditAuthorResolverService }
    },
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(administrationUsersRoutes)],
    exports: [RouterModule]
  })
  export class AuthorAdministrationRoutingModule {
    constructor() {
    }
  }