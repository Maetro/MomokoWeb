// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,

  log: true,

  seguimientoJS: 'seguimiento.js',

  //URLS app
  momokoUrl: 'http://192.168.0.168:4200/',
  serverUrl : 'http://192.168.0.168:5000/',
  uploadUrl : 'http://192.168.0.168/upload.php',
  urlFiles : 'http://192.168.0.168/images/',
  entradasUrl: 'http://192.168.0.168:5000/modelo/entradas',
  librosUrl :  'http://192.168.0.168:5000/modelo/libros',
  generosUrl :  'http://192.168.0.168:5000/modelo/generos',
  addEntradaUrl: 'http://192.168.0.168:5000/modelo/entradas/add',
  addLibroUrl :  'http://192.168.0.168:5000/modelo/libros/add',
  addGeneroUrl :  'http://192.168.0.168:5000/modelo/generos/add',
  informacionGeneralUrl:  'http://192.168.0.168:5000/modelo/informacionGeneral',
  singUpURL: 'http://192.168.0.168:5000/account/signup',
  oauthTokenUrl: 'http://192.168.0.168:5000/oauth/token',
  accountTokenUrl: 'http://192.168.0.168:5000/account/token',
  getEntradaUrl: 'http://192.168.0.168:5000/public/entrada/',
  getEntradaAdminUrl: 'http://192.168.0.168:5000/modelo/entrada/',
  indexDataUrl: 'http://192.168.0.168:5000/public/indexData',
  obtenerLibroUrl: 'http://192.168.0.168:5000/public/libro/',
  addComentarioUrl: 'http://192.168.0.168:5000/public/comentario/add',
  obtenerVideoUrl: 'http://192.168.0.168:5000/public/video/',
  getGeneroUrl: 'http://192.168.0.168:5000/public/genero/',
  initDataUrl: 'http://192.168.0.168:5000/public/initData',
  addGaleriaUrl: 'http://192.168.0.168:5000/modelo/galerias/add',
  galeriasUrl: 'http://192.168.0.168:5000/modelo/galerias',
  getCategoriaUrl: 'http://192.168.0.168:5000/public/categoria/',
  getEtiquetaUrl: 'http://192.168.0.168:5000/public/etiqueta/',
  getNoticiasLibroUrl: 'http://192.168.0.168:5000/public/noticias-libro/',
  suscripcionUrl: 'http://192.168.0.168:5000/public/suscribirse/',
  getBusquedaUrl: 'http://192.168.0.168:5000/public/buscar/'
};
