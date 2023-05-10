package dev.vicart.capacitordynamiccolor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.WallpaperColors;
import android.app.WallpaperManager;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(name = "DynamicColor", permissions = {
        @Permission(alias = "storage", strings = {Manifest.permission.READ_EXTERNAL_STORAGE})
})
public class DynamicColorPlugin extends Plugin {

    @PluginMethod
    public void getDynamicColors(PluginCall call) {
        if(getPermissionState("storage") != PermissionState.GRANTED) {
            requestPermissionForAlias("storage", call, "storagePerms");
        }
        else {
            getColors(call);
        }
    }

    @PermissionCallback
    private void storagePerms(PluginCall call) {
        if(getPermissionState("storage") == PermissionState.GRANTED) {
            getColors(call);
        }
        else {
            call.reject("You must allow storage permission");
        }
    }

    @SuppressLint("MissingPermission")
    private void getColors(PluginCall call) {
        WallpaperManager manager = WallpaperManager.getInstance(getContext());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O_MR1) {
            WallpaperColors colors = WallpaperColors.fromDrawable(manager.getDrawable());
            JSObject ret = new JSObject();
            ret.put("primary", Integer.toHexString(colors.getPrimaryColor().toArgb()));
            ret.put("secondary", Integer.toHexString(colors.getSecondaryColor().toArgb()));
            ret.put("tertiary", Integer.toHexString(colors.getTertiaryColor().toArgb()));
            call.resolve(ret);
        }
        else {
            call.unavailable("Cannot get infos from Wallpaper");
        }
    }
}
