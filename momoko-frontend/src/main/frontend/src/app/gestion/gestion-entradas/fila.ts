import { Columna } from 'app/gestion/gestion-entradas/columna';


export class Fila {
  numFila: number;
  numeroColumnas: number;
  columnas: Columna[];
  colorFondo: number;

  constructor(numFila: number, texto: string) {
      console.log('Creando fila');
      this.numFila = numFila;
      this.columnas = new Array();
      const columna = new Columna(0, texto);
      this.columnas.push(columna);
      this.colorFondo = 1;
      this.numeroColumnas = 1;
  }

}
