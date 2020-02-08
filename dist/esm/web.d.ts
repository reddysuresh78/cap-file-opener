import { WebPlugin } from '@capacitor/core';
import { CapFileOpenerPlugin } from './definitions';
export declare class CapFileOpenerWeb extends WebPlugin implements CapFileOpenerPlugin {
    constructor();
    open(options: {
        filePath: string;
        fileMediaType: string;
    }): Promise<{
        value: string;
    }>;
}
declare const CapFileOpener: CapFileOpenerWeb;
export { CapFileOpener };
