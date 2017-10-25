import {
  Component,
  OnDestroy,
  AfterViewInit,
  EventEmitter,
  Input,
  Output
} from '@angular/core';

@Component({
  selector: 'app-simple-tiny',
  template: `<textarea id="{{elementId}}"></textarea>`
})
export class SimpleTinyComponent implements AfterViewInit, OnDestroy {
  @Input() elementId: String;
  @Input() initialContent: String;
  @Output() onEditorKeyup = new EventEmitter<any>();

  editor;

  ngAfterViewInit() {

    tinymce.init({
      selector: '#' + this.elementId,
      browser_spellcheck: true,
      plugins: ['link', 'paste', 'table', 'code', 'jsplus_bootstrap_show_blocks, jsplus_bootstrap_button',
    'jsplus_bootstrap_row_add_up, jsplus_bootstrap_gallery'],
      // plugins: ['link', 'paste', 'table',  'jsplus_bootstrap_editor,jsplus_bootstrap_gallery',
      // 'jsplus_bootstrap_icons,jsplus_bootstrap_table_new,jsplus_bootstrap_alert',
      // 'jsplus_bootstrap_row_add_down,jsplus_bootstrap_row_add_up,jsplus_bootstrap_templates',
      // 'jsplus_bootstrap_table_cell_conf,jsplus_bootstrap_include, jsplus_bootstrap_table_col_conf',
      // 'jsplus_bootstrap_table_row_conf,jsplus_bootstrap_table_conf,jsplus_bootstrap_block_conf',
      // 'jsplus_bootstrap_button,jsplus_bootstrap_breadcrumbs,jsplus_bootstrap_table_col_move_right',
      // 'jsplus_bootstrap_table_col_move_left,jsplus_bootstrap_table_row_move_down,jsplus_bootstrap_table_row_move_up',
      // 'jsplus_bootstrap_label,jsplus_bootstrap_show_blocks,jsplus_bootstrap_badge,jsplus_bootstrap_col_move_left',
      // 'jsplus_bootstrap_delete_col,jsplus_bootstrap_delete_row,jsplus_bootstrap_col_move_right,jsplus_bootstrap_row_move_down',
      // 'jsplus_bootstrap_row_move_up'],
      toolbar1: 'undo redo | bold italic | alignleft aligncenter alignright | code',
      // tslint:disable-next-line:max-line-length
      toolbar2: 'jsplus_bootstrap_show_blocks, jsplus_bootstrap_templates, jsplus_bootstrap_button, jsplus_bootstrap_row_add_up, jsplus_bootstrap_gallery',
      skin_url: '/assets/skins/lightgray',
      extended_valid_elements : 'div[*],span[*],i[*]',
      language_url : '/assets/languages/tinymce/es.js',
      language: 'es',
      content_css: '/assets/style/css/bootstrap.min.css',
      external_plugins: {
        'contentJS' : '/assets/style/js/bootstrap.min.js',
        'code': '/assets/plugins/code/plugin.js',
        'jsplus_bootstrap_button_es' : '/assets/plugins/jsplus_bootstrap_button/langs/en.js',
        'jsplus_bootstrap_button': '/assets/plugins/jsplus_bootstrap_button/plugin.min.js',
        'jsplus_bootstrap_row_add_up_es' : '/assets/plugins/jsplus_bootstrap_row_add_up/langs/en.js',
        'jsplus_bootstrap_row_add_up': '/assets/plugins/jsplus_bootstrap_row_add_up/plugin.min.js',
        'jsplus_bootstrap_gallery_es': '/assets/plugins/jsplus_bootstrap_gallery/langs/en.js',
        'jsplus_bootstrap_gallery': '/assets/plugins/jsplus_bootstrap_gallery/plugin.min.js',
        'jsplus_bootstrap_show_blocks_es': '/assets/plugins/jsplus_bootstrap_show_blocks/langs/es.js',
        'jsplus_bootstrap_show_blocks': '/assets/plugins/jsplus_bootstrap_show_blocks/plugin.min.js'
      },
      jsplus_bootstrap_include : {
        version: 4,
        includeJQuery: true
      },
      jsplus_uploader_url: 'http://localhost/images/uploader.php',
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
