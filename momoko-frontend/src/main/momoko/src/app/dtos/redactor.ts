import { SocialData } from "./abstract/socialData";

export class Redactor extends SocialData{
    usuarioId: number;
    nombre: string;
    nick: string;
    email: string;
    imagenCabeceraRedactor: string;
    avatarRedactor: string;
    urlRedactor: string;
    descripcion: string;
    cargo: string;
    fechaAlta: Date;
    fechaUltimaEntrada: Date;
    mediaPuntuaciones: number;
  }