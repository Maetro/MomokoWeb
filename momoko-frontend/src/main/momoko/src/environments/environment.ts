// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,

  log: true,

  seguimientoJS: 'seguimiento.js',

  //URLS app
  momokoUrl: 'http://localhost:4200/',
  serverUrl : 'http://localhost:5000/',
  uploadUrl : 'http://localhost/upload.php',
  urlFiles : 'http://localhost/images/',
  entradasUrl: 'http://localhost:5000/modelo/entradas',
  librosUrl :  'http://localhost:5000/modelo/libros',
  generosUrl :  'http://localhost:5000/modelo/generos',
  addEntradaUrl: 'http://localhost:5000/modelo/entradas/add',
  addLibroUrl :  'http://localhost:5000/modelo/libros/add',
  addGeneroUrl :  'http://localhost:5000/modelo/generos/add',
  informacionGeneralUrl:  'http://localhost:5000/modelo/informacionGeneral',
  singUpURL: 'http://localhost:5000/account/signup',
  oauthTokenUrl: 'http://localhost:5000/oauth/token',
  accountTokenUrl: 'http://localhost:5000/account/token',
  getEntradaUrl: 'http://localhost:5000/public/entrada/',
  getEntradaZonaUrl: 'http://localhost:5000/public/zona/',
  getEntradaAdminUrl: 'http://localhost:5000/modelo/entrada/',
  indexDataUrl: 'http://localhost:5000/public/indexData',
  obtenerLibroUrl: 'http://localhost:5000/public/libro/',
  addComentarioUrl: 'http://localhost:5000/public/comentario/add',
  obtenerVideoUrl: 'http://localhost:5000/public/video/',
  getGeneroUrl: 'http://localhost:5000/public/genero/',
  initDataUrl: 'http://localhost:5000/public/initData',
  addGaleriaUrl: 'http://localhost:5000/modelo/galerias/add',
  galeriasUrl: 'http://localhost:5000/modelo/galerias',
  getCategoriaUrl: 'http://localhost:5000/public/categoria/',
  getEtiquetaUrl: 'http://localhost:5000/public/etiqueta/',
  getNoticiasLibroUrl: 'http://localhost:5000/public/noticias-libro/',
  suscripcionUrl: 'http://localhost:5000/public/suscribirse/',
  getBusquedaUrl: 'http://localhost:5000/public/buscar/',
  sagasUrl: 'http://localhost:5000/modelo/sagas',
  obtenerSagaAdmin: 'http://localhost:5000/modelo/saga/',
  addSagaUrl: 'http://localhost:5000/modelo/sagas/add',
  getSagaUrl: 'http://localhost:5000/public/saga/'
};
