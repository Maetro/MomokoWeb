import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { LazyImageWithLinkComponent } from './lazy-image-with-link/lazy-image-with-link.component';
import { LazyImageComponent } from './lazy-image/lazy-image.component';
import { LazyLoadDirective } from './lazy-load.directive';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [
    LazyImageComponent,
    LazyImageWithLinkComponent,
    LazyLoadDirective],
    exports:[LazyImageComponent,
      LazyImageWithLinkComponent,
      LazyLoadDirective]
})
export class LazyLoadModule { }
