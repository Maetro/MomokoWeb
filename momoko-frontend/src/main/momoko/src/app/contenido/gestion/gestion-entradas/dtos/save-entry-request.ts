import { Etiqueta } from "app/dtos/etiqueta";

export interface SaveEntryRequest{

entradaId: number;
tituloEntrada: string;
urlEntrada: string;
etiquetas: Etiqueta[];
entryType: string;
titulosLibrosEntrada: string;
nombresSagasEntrada: string[];
editorNombre: string;
imagenDestacada: string;
permitirComentarios: boolean;
conSidebar: boolean;
enMenu: boolean;
nombreMenuLibro: string;
urlMenuLibro: string;

contenidoEntrada: string;
resumenEntrada: string;
fraseDescriptiva: string;
urlVideo: string;

entryStatusId: number;
publishDate: Date;
}