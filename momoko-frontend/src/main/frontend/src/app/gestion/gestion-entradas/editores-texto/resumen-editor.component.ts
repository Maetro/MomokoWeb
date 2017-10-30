import {
  Component,
  OnDestroy,
  AfterViewInit,
  EventEmitter,
  Input,
  Output
} from '@angular/core';

@Component({
  selector: 'app-resumen-editor',
  template: `<textarea id="{{elementId}}"></textarea>`
})
export class ResumenEditorComponent implements AfterViewInit, OnDestroy {
  @Input() elementId: String;
  @Input() initialContent: String;
  @Output() onEditorKeyup = new EventEmitter<any>();

  editor;

  ngAfterViewInit() {

    tinymce.init({
      selector: '#' + this.elementId,
      browser_spellcheck: true,
      plugins: ['link', 'paste', 'table', 'code'],
      toolbar1: 'undo redo | bold italic | alignleft aligncenter alignright | code',
      // tslint:disable-next-line:max-line-length
      skin_url: '/assets/skins/lightgray',
      language_url : '/assets/languages/tinymce/es.js',
      menubar: false,
      language: 'es',
      content_css: '/assets/style/css/bootstrap.min.css',
      setup: editor => {
        this.editor = editor;
        editor.on('keyup', () => {
          const content = editor.getContent();
          this.onEditorKeyup.emit(content);
        });
      },
      init_instance_callback: (editor: any) => {
        editor && this.initialContent && this.editor.setContent(this.initialContent)
      }
    });
  }

  ngOnDestroy() {
    tinymce.remove(this.editor);
  }
}
