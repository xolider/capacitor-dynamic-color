import type {PermissionState} from "@capacitor/core";

export interface DynamicColorPlugin {
  getDynamicColors() : Promise<{primary: string, secondary: string, tertiary: string}>
}

export interface PermissionStatus {
  storage: PermissionState
}