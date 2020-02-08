declare module "@capacitor/core" {
  interface PluginRegistry {
    CapFileOpener: CapFileOpenerPlugin;
  }
}

export interface CapFileOpenerPlugin {
  open(options: { filePath: string, fileMediaType: string }): Promise<{value: string}>;
}
