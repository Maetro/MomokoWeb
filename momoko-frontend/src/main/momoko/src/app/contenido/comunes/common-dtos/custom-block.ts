import { LibroEntradaSimple } from "app/dtos/simples/libroEntradaSimple";

export interface CustomBlock {
    id: number;
    active: boolean;
    isCode: boolean;
    customBlockMainImage: string;
    content: string;
    link: string;
    links: LibroEntradaSimple[]; // Format: book-urlBook entry:urlEntry
    type: CustomBlockTypeEnum;
    template: CustomBlockTemplateEnum;
}

export enum CustomBlockTypeEnum {
    INDEX,
    SIDEBAR
}

export enum CustomBlockTemplateEnum {
    BLOCK_ONLY,
    FOUR_LINKS_WITH_CONTENT
}