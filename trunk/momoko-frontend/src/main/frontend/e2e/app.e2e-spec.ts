import { MomokoPage } from './app.po';

describe('momoko App', () => {
  let page: MomokoPage;

  beforeEach(() => {
    page = new MomokoPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
