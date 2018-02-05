import { Columna } from 'app/gestion/gestion-entradas/columna';


export class Fila {
  numFila: number;
  numeroColumnas: number;
  columnas: Columna[];
  bootstrapcolumn: string;

  constructor(numFila: number, texto: string) {
      console.log('Creando fila');
      this.numFila = numFila;
      this.columnas = new Array();
      const columna = new Columna(0, texto);
      this.columnas.push(columna);
      this.numeroColumnas = 1;
  }

}
