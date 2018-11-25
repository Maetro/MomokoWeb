import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CustomBlockFormComponent } from './custom-block-form/custom-block-form.component';
import { CustomBlockListComponent } from './custom-block-list/custom-block-list.component';
import { CustomBlockListResolverService } from './resolvers/custom-block-list-resolver.service';
import { EditCustomBlockResolverService } from './resolvers/edit-custom-block-resolver.service';
import { NewCustomBlockResolverService } from './resolvers/new-custom-block-resolver.service';
import { GeneralAdministrationComponent } from './general-administration/general-administration.component';

const generalAdministrationRoutes = [
  {
    path: '',
    component: GeneralAdministrationComponent,
    pathMatch: 'full'
  },
  {
    path: 'customblock',
    component: CustomBlockListComponent,
    pathMatch: 'full',
    resolve: { data: CustomBlockListResolverService }
  },
  {
    path: 'customblock/nuevo',
    component: CustomBlockFormComponent,
    pathMatch: 'full',
    resolve: { data: NewCustomBlockResolverService }
  },
  {
    path: 'customblock/editar/:id',
    component: CustomBlockFormComponent,
    pathMatch: 'full',
    resolve: { data: EditCustomBlockResolverService }
  }
];

@NgModule({
  imports: [RouterModule.forChild(generalAdministrationRoutes)],
  exports: [RouterModule]
})
export class GeneralAdministrationRoutingModule {
  constructor() {}
}
