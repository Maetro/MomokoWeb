import { Component, OnInit } from '@angular/core';
import { environment } from 'environments/environment';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import {
  CustomBlock
} from 'app/contenido/comunes/common-dtos/custom-block';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/primeng';
import { CustomBlockService } from '../service/custom-block.service';
import { FileUploadService } from '../../services/file-upload.service';
import { UtilService } from 'app/services/util/util.service';
import { LabelUrlType } from '../dtos/new-custom-block-info';
import { SaveCustomBlockRequest } from '../dtos/save-custom-block-request';

@Component({
  selector: 'app-custom-block-form',
  templateUrl: './custom-block-form.component.html',
  styleUrls: ['./custom-block-form.component.scss']
})
export class CustomBlockFormComponent implements OnInit {
  private log = environment.log;

  customBlockForm: FormGroup;
  customBlockId: string;

  customBlock: SaveCustomBlockRequest;

  booksAndEntries: LabelUrlType[];
  booksAndEntriesSelectable: SelectItem[];

  customURL = false;

  submitted = false;

  types = [
    { label: 'Index', value: 'INDEX' },
    { label: 'Sidebar', value: 'SIDEBAR' }
  ];

  templates = [
    {
      label: 'Solo el bloque central',
      value: "BLOCK_ONLY",
      numberOfLinks: 0
    },
    {
      label: '4 links a los lados y el contenido en el centro',
      value: "FOUR_LINKS_WITH_CONTENT",
      numberOfLinks: 4
    }
  ];
  selectedTemplate: { label: string; value: number; template: string; numberOfLinks: number; };



  constructor(
    private fb: FormBuilder,
    private router: Router,
    private messageService: MessageService,
    private customBlockService: CustomBlockService,
    private fileUploadService: FileUploadService,
    private util: UtilService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    if (this.log) {
      console.log('Builder ListaFilteresComponent');
    }

    this.customBlockId = this.route.snapshot.paramMap.get('id');
    this.getcustomBlockForm();
    if (this.customBlockId) {
      this.route.data.subscribe(data => {
        this.customBlock = data.customBlock;
        this.customBlockForm.patchValue({
          id: +this.customBlock.id,
          active: this.customBlock.active,
          isCode: this.customBlock.isCode,
          customBlockMainImage: this.customBlock.customBlockMainImage,
          content: this.customBlock.content,
          link: this.customBlock.link,
          links: this.customBlock.links,
          type: this.customBlock.type,
          template: this.customBlock.template
        });
      });
    } else {
      this.booksAndEntriesSelectable = [];
      this.route.data.subscribe(data => {
        this.booksAndEntries = data.data.booksAndEntries;
        this.booksAndEntries.forEach(value => {
          let header = "";
          if (value.type === 'Entry'){
            header = "Entrada: ";
          } else if (value.type === 'Book'){
            header = "Libro: ";
          } 
          this.booksAndEntriesSelectable.push({
            label:  header + value.label,
            value: value.type + ' ' + value.url
          })
        });
      });
      this.customBlock = {
        id: null,
        active: null,
        isCode: null,
        customBlockMainImage: null,
        content: null,
        link: null,
        links: null, // Format: book-urlBook entry:urlEntry
        type: null,
        template: null
      };
      this.customBlockForm.patchValue({
        active: true,
        isCode: false,
      })
    }
  }

  private getcustomBlockForm() {
    this.customBlockForm = this.fb.group({
      active: [''],
      isCode: [''],
      customBlockMainImage: [''],
      content: [''],
      link: [''],
      links: this.fb.array([]),
      type: [''],
      template: ['']
    });
  }

  get links() {
    return this.customBlockForm.get('links') as FormArray;
  }

  addLinks() {
    this.links.push(this.fb.control(''));
  }

  get customBlockF() {
    return this.customBlockForm.controls;
  }

  fileChangeCabecera($event): void {
    this.fileUploadService.fileChange($event, 'customblock-images').subscribe(
      urlImagenNueva => {
        // Emit list event
        if (this.log) {
          console.log(urlImagenNueva);
        }
        this.messageService.add({
          severity: 'success',
          summary: 'Imagen guardada correctamente',
          detail: 'Via Servidor'
        });
        this.customBlockForm.patchValue({
          customBlockMainImage: urlImagenNueva
        });
      },
      err => {
        // Log errors if any
        if (this.log) {
          console.log(err);
        }
      }
    );
  }

  onChangeTemplate(data: any){
    console.log(data);
    this.clearFormArray(this.links);
    const cont = this.customBlockForm.controls.template.value.numberOfLinks;
    for (let i = 0; i<cont; i++){
      this.links.push(this.fb.control(''));
    }
  }

  clearFormArray = (formArray: FormArray) => {
    while (formArray.length !== 0) {
      formArray.removeAt(0)
    }
  }

getValue(){
  return JSON.stringify(this.customBlockForm.getRawValue());
}

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.customBlockForm.invalid) {
      return;
    }
    this.customBlockForm.controls;
    const updatecustomBlockRequest = this.customBlockForm.getRawValue();
    updatecustomBlockRequest.template = updatecustomBlockRequest.template.value;
    updatecustomBlockRequest.type = updatecustomBlockRequest.type.value;
    this.customBlockService
      .savecustomBlock(updatecustomBlockRequest)
      .subscribe(res => {
        if (res.id) {
          this.messageService.add({
            severity: 'success',
            summary: 'El bloque se ha guardado correctamente.',
            detail: 'Via MessageService'
          });
          this.router.navigate(['/gestion/general/customblock']);
        } else {
          alert('ERROR!! :-(');
        }
      });
  }

  volver() {
    this.router.navigate(['/gestion/general/customblock']);
  }
}
