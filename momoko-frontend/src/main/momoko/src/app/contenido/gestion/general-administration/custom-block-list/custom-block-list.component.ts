import { Component, OnInit } from '@angular/core';
import { environment } from 'environments/environment';
import { CustomBlock } from 'app/contenido/comunes/common-dtos/custom-block';
import { SelectItem } from 'primeng/primeng';
import { CustomBlockService } from '../service/custom-block.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-custom-block-list',
  templateUrl: './custom-block-list.component.html',
  styleUrls: ['./custom-block-list.component.scss']
})
export class CustomBlockListComponent implements OnInit {
  private log = environment.log;

  selectedCustomBlock: CustomBlock;

  cols: any[];

  loading: boolean;

  options: SelectItem[];

  title = 'Libros';
  customBlocks: CustomBlock[];

  constructor(
    private customBlockService: CustomBlockService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    if (this.log) {
      console.log('Builder CustomBlockListComponent');
    }
    this.customBlocks = [];
  }

  ngOnInit(): void {
    if (this.log) {
      console.log('ngOnInit Lista getFilteres');
    }
    this.loading = true;
    this.route.data.subscribe(response => {
      response.data.forEach(customBlock => {
        this.customBlocks.push(customBlock);
      });
    });
    this.cols = [
      { field: 'id', header: 'Id' },
      { field: 'type', header: 'Tipo' },
      { field: 'template', header: 'Plantilla' },
      { field: 'customBlockMainImage', header: 'Imagen' }
    ];
  }

  newCustomBlock(): void {
    if (this.log) {
      console.log('nuevoFilter');
    }
    this.router.navigate(['/gestion/general/customblock/nuevo']);
  }

  editCustomBlock(customBlock: CustomBlock) {
    this.router.navigate(['/gestion/general/customblock/editar/' + customBlock.id]);
  }

  volver(): void {
    if (this.log) {
      console.log('volver');
    }
    this.router.navigate(['/gestion/general/customBlock']);
  }

  onRowSelect(event) {
    if (this.log) {
      console.log('onRowSelect');
    }
  }

}
