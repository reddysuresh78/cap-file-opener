import { WebPlugin } from '@capacitor/core';
import { CapFileOpenerPlugin } from './definitions';

export class CapFileOpenerWeb extends WebPlugin implements CapFileOpenerPlugin {
  constructor() {
    super({
      name: 'CapFileOpener',
      platforms: ['web']
    });
  }

  async open(options: { filePath: string, fileMediaType: string }): Promise<{value: string}> {
    console.log('FILEOPENER', options);
	
    return {value: 'Output'};
  }
}

const CapFileOpener = new CapFileOpenerWeb();

export { CapFileOpener };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(CapFileOpener);
