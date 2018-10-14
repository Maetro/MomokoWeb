import { NgModule } from '@angular/core';
import { ComunesModule } from 'app/contenido/comunes/comunes.module';
import { BookTemplateInstanceComponent } from './book-template-instance.component';
import { BookTemplateComponent } from './book-template.component';
import { BookTemplateDirective } from './book-template.directive';
import { RouterModule } from '@angular/router';

@NgModule({
   imports: [
      ComunesModule,
      RouterModule
   ],
   declarations: [
      BookTemplateComponent,
      BookTemplateDirective,
      BookTemplateInstanceComponent
   ],
   entryComponents:[BookTemplateInstanceComponent],
   exports:[BookTemplateComponent]
})
export class BookTemplateModule { }
