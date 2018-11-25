export interface SaveCustomBlockRequest {
  id: number;
  active: boolean;
  isCode: boolean;
  customBlockMainImage: string;
  content: string;
  link: string;
  links: string[]; // Format: book-urlBook entry:urlEntry
  type: string;
  template: string;
}
