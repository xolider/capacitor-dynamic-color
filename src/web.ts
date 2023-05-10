import { WebPlugin } from '@capacitor/core';

import type { DynamicColorPlugin } from './definitions';

export class DynamicColorWeb extends WebPlugin implements DynamicColorPlugin {
  async getDynamicColors(): Promise<{ primary: string; secondary: string; tertiary: string }> {
    throw this.unimplemented("Dynamic Color: Not implemented on the web.")
  }

}
