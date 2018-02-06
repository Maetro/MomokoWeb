export class Columna {
  numcolumna: number;
  anchura: number;
  texto: string;
  bootstrapcolumn: string;
  constructor(numcolumna: number, texto: string) {
      this.texto = texto;
      this.numcolumna = numcolumna;
      this.anchura = 12;
      this.bootstrapcolumn = 'col-sm-12';
  }

}
