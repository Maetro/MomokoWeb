import 'zone.js/dist/zone-node';
import 'reflect-metadata';
import { renderModuleFactory } from '@angular/platform-server';
import { enableProdMode } from '@angular/core';

import * as express from 'express';
import { join } from 'path';
import * as path from 'path';
import { readFileSync } from 'fs';

// Faster server renders w/ Prod mode (dev mode never needed)
enableProdMode();

// Express server
const app = express();

// const ROOT = path.join(path.resolve(__dirname, '..'));
const PORT = process.env.PORT || 4000;
const DIST_FOLDER = join(process.cwd(), 'dist');

// Our index.html we'll use as our template
const template = readFileSync(
  join(DIST_FOLDER, 'browser', 'index.html')
).toString();

// * NOTE :: leave this as require() since this file is built Dynamically from webpack
const {
  AppServerModuleNgFactory,
  LAZY_MODULE_MAP
} = require('./dist/server/main.bundle');

// Express Engine
import { ngExpressEngine } from '@nguniversal/express-engine';
// Import module map for lazy loading
import { provideModuleMap } from '@nguniversal/module-map-ngfactory-loader';
import { AppModule } from './src/app/app.module';

// Our Universal express-engine (found @ https://github.com/angular/universal/tree/master/modules/express-engine)
app.engine(
  'html',
  ngExpressEngine({
    bootstrap: AppServerModuleNgFactory,
    providers: [provideModuleMap(LAZY_MODULE_MAP)]
  })
);

app.set('view engine', 'html');
app.set('views', join(DIST_FOLDER, 'browser'));

/* - Example Express Rest API endpoints -
  app.get('/api/**', (req, res) => { });
*/

// Server static files from /browser
app.get(
  '*.*',
  express.static(join(DIST_FOLDER, 'browser'), {
    maxAge: '1y'
  })
);


// function cacheControl(req, res, next) {
//   // instruct browser to revalidate in 60 seconds
//   res.header('Cache-Control', 'max-age=60');
//   next();
// }

// app.use('/assets', cacheControl, express.static(path.join(__dirname, 'assets'), {maxAge: 30}));
// app.use(cacheControl, express.static(path.join(ROOT, 'dist/client'), {index: false}));

const cache = {};



function ngApp(req, res) {
  let baseUrl = '/';
  let url = req.originalUrl || '/';
  res.setHeader('Cache-Control', 'public, max-age=300');
  if (cache.hasOwnProperty(url)) {
      var hit = cache[url];
      if (hit[0] > Date.now()) {
        res.status(200).send(hit[1]);
        return;
      }
  }

  res.render('index', {
    req,
    res,
    ngModule: AppModule,
    preboot: {
      appRoot: ['app'],
      uglify: true,
      buffer: true
    },
    async: false,
    baseUrl: baseUrl,
    requestUrl: req.originalUrl,
    originUrl: `http://localhost:3000`
  },(err, html) => {
    cache[url] = [Date.now()+180000,html];
    res.status(200).send(html);
  });
}

app.get('*', ngApp);
app.get('/', ngApp);

// Start up the Node server
app.listen(PORT, () => {
  console.log(`Node Express server listening on http://localhost:${PORT}`);
});
