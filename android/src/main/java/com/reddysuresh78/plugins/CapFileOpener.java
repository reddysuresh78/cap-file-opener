package com.reddysuresh78.plugins;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.FileProvider;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.PluginRequestCodes;
import com.getcapacitor.android.BuildConfig;

import java.io.File;

@NativePlugin(
        permissions={
                Manifest.permission.READ_EXTERNAL_STORAGE
        }
)
public class CapFileOpener extends Plugin {

    static final int REQUEST_READ_EXTERNAL_STORAGE = PluginRequestCodes.FILESYSTEM_REQUEST_READ_FILE_PERMISSIONS;

    @PluginMethod()
    public void open(PluginCall call) {
        String filePath = call.getString("filePath");
        String contentType = call.getString("fileMediaType");

        if (!call.getData().has("filePath")) {
            call.reject("Must provide filePath");
            return;
        }

        if (!call.getData().has("fileMediaType")) {
            call.reject("Must provide fileMediaType");
            return;
        }

        String output = "Received " + filePath + " " + contentType;
        System.out.println(output);

        if(!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            pluginRequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_READ_EXTERNAL_STORAGE);

        }else{
            openFile(call);
        }

    }

    @Override
    protected void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.handleRequestPermissionsResult(requestCode, permissions, grantResults);

        PluginCall savedCall = getSavedCall();
        if (savedCall == null) {
//            log("No stored plugin call for permissions request result");
            return;
        }

        for(int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                savedCall.error("User denied permission");
                return;
            }
        }

        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            openFile(savedCall);
        }
    }

    private void openFile(PluginCall call){
        // We got the permission
        String filePath = call.getString("filePath");
        String contentType = call.getString("fileMediaType");

        File file = new File(filePath);

        Intent intent = new Intent(Intent.ACTION_VIEW);

        System.out.println("Provider " + BuildConfig.APPLICATION_ID + ".fileprovider");
        intent.setDataAndType(FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".fileprovider", file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        getContext().startActivity(intent);


//        File file = new File(filePath);
//
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//
//        intent.setDataAndType(Uri.fromFile(file),"application/pdf");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        getContext().startActivity(intent);

        String output = "Received in openfile: " + filePath + " " + contentType;
        System.out.println(output);

        JSObject ret = new JSObject();
        ret.put("return", output);
        call.success(ret);

    }

}
