export interface AuthorContactRequest{
     name :string;
     email:string;
     description:string;
     title:string;
     genre:string;
     isPublished: string;
     acceptedPrivacy: boolean;
}

export interface EditorContactRequest{
    name :string;
    email:string;
    description:string;
    acceptedPrivacy: boolean;
}

export interface PublisherContactRequest{
    name :string;
    email:string;
    publisherName: string;
    description:string;
    acceptedPrivacy: boolean;
}