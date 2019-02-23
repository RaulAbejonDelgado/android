package com.raul.ipartek.implicitintents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;
    private EditText telText;
    private final int PHONE_CALL_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);

    }

    public void openWebsite(View view) {

        String url = mWebsiteEditText.getText().toString();

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void openLocation(View view) {
        // Get the string indicating a location. Input is not validated; it is
        // passed to the location handler intact.
        String loc = mLocationEditText.getText().toString();
        // Parse the location and create the intent.
        Uri addressUri = Uri.parse("geo:0,0?q="+ loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        intent.setPackage("com.google.android.apps.maps");

        // Find an activity to handle the intent, and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }

    }


    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }


    public void openTel(View view) {

        telText = findViewById(R.id.telText);
        String telephoneNumber = telText.getText().toString();

        if (telephoneNumber != null && !telephoneNumber.isEmpty()) {
            Intent intentTel = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + telephoneNumber));
            //comprobamos la version de android
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //metodo asyncrono
                System.out.println("Api nueva 22 o superior");
                requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);

            } else {
                System.out.println("Api antigua 22 o inferior");
                olderVersions(telephoneNumber);
            }
        }else{
            Toast.makeText(MainActivity.this,"Phone number not valid",Toast.LENGTH_LONG).show();
        }
    }

        //
        private void olderVersions(String telephoneNumber){
            Intent intentTel = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+telephoneNumber));
            if(checkPersimission(Manifest.permission.CALL_PHONE)){
                startActivity(intentTel);
            }else {
                Toast.makeText(MainActivity.this,"You declined the access",Toast.LENGTH_LONG);
            }


        }

    public void openCamera(View view) {
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        telText = findViewById(R.id.telText);
        String telephoneNumber = telText.getText().toString();
       //checkeo de permisos api 22+
        switch (requestCode){
            //Estamos en el caso de verificar los permisos del telefono
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)){
                    //comprobar si ha sido aceptado o denagada la peticion de permiso
                    if(result == PackageManager.PERMISSION_GRANTED){
                        //permission conceded
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ telephoneNumber));
                        startActivity(intentCall);

                    }else{
                        //non permission conceded
                        Toast.makeText(MainActivity.this,"You declined access",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:

                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean checkPersimission(String permission) {

        int result = this.checkCallingOrSelfPermission(permission);

        return result == PackageManager.PERMISSION_GRANTED;
    }
}
