// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,

  //URLS app
  uploadUrl : 'http://localhost:8080/upload',
  urlFiles : 'http://localhost/images/',
  librosUrl : 'http://localhost:8080/doctor/libros',
  addLibroUrl : 'http://localhost:8080/doctor/libros/add',
  addGeneroUrl : 'http://localhost:8080/doctor/generos/add',
  generosUrl : 'http://localhost:8080/doctor/generos',
  informacionGeneralUrl: 'http://localhost:8080/doctor/informacionGeneral'
};
