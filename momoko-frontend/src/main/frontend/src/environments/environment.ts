// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,

  //URLS app
  momokoUrl: 'http://192.168.43.117:4200/',
  serverUrl : 'http://192.168.43.117:8080/',
  uploadUrl : 'http://192.168.43.117:8080/upload',
  urlFiles : 'http://192.168.43.117/images/',
  entradasUrl: 'http://192.168.43.117:8080/modelo/entradas',
  librosUrl :  'http://192.168.43.117:8080/modelo/libros',
  generosUrl :  'http://192.168.43.117:8080/modelo/generos',
  addEntradaUrl: 'http://192.168.43.117:8080/modelo/entradas/add',
  addLibroUrl :  'http://192.168.43.117:8080/modelo/libros/add',
  addGeneroUrl :  'http://192.168.43.117:8080/modelo/generos/add',
  informacionGeneralUrl:  'http://192.168.43.117:8080/modelo/informacionGeneral',
  singUpURL: 'http://192.168.43.117:8080/account/signup',
  oauthTokenUrl: 'http://192.168.43.117:8080/oauth/token',
  accountTokenUrl: 'http://192.168.43.117:8080/account/token',
  getEntradaUrl: 'http://192.168.43.117:8080/public/entrada/',
  getEntradaAdminUrl: 'http://192.168.43.117:8080/modelo/entrada/',
  indexDataUrl: 'http://192.168.43.117:8080/public/indexData',
  obtenerLibroUrl: 'http://192.168.43.117:8080/public/libro/',
  addComentarioUrl: 'http://192.168.43.117:8080/public/comentario/add',
  obtenerVideoUrl: 'http://192.168.43.117:8080/public/video/',
  getGeneroUrl: 'http://192.168.43.117:8080/public/genero/',
  initDataUrl: 'http://192.168.43.117:8080/public/initData',
  addGaleriaUrl: 'http://192.168.43.117:8080/modelo/galerias/add',
  galeriasUrl: 'http://192.168.43.117:8080/modelo/galerias',
  getCategoriaUrl: 'http://192.168.43.117:8080/public/categoria/',
};
