// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,

  //URLS app
  momokoUrl: 'http://localhost:4200/',
  serverUrl : 'http://localhost:8080/',
  uploadUrl : 'http://localhost:8080/upload',
  urlFiles : 'http://localhost/images/',
  entradasUrl: 'http://localhost:8080/modelo/entradas',
  librosUrl :  'http://localhost:8080/modelo/libros',
  generosUrl :  'http://localhost:8080/modelo/generos',
  addEntradaUrl: 'http://localhost:8080/modelo/entradas/add',
  addLibroUrl :  'http://localhost:8080/modelo/libros/add',
  addGeneroUrl :  'http://localhost:8080/modelo/generos/add',
  informacionGeneralUrl:  'http://localhost:8080/modelo/informacionGeneral',
  singUpURL: 'http://localhost:8080/account/signup',
  oauthTokenUrl: 'http://localhost:8080/oauth/token',
  accountTokenUrl: 'http://localhost:8080/account/token',
  getEntradaUrl: 'http://localhost:8080/public/entrada/',
  indexDataUrl: 'http://localhost:8080/public/indexData',
  obtenerLibroUrl: 'http://localhost:8080/public/libro/',
  addComentarioUrl: 'http://localhost:8080/public/comentario/add',
  obtenerVideoUrl: 'http://localhost:8080/public/video/',
  getGeneroUrl: 'http://localhost:8080/public/genero/',
  initDataUrl: 'http://localhost:8080/public/initData',
  addGaleriaUrl: 'http://localhost:8080/modelo/galerias/add',
  galeriasUrl: 'http://localhost:8080/modelo/galerias',
  getCategoriaUrl: 'http://localhost:8080/public/categoria/',
};
