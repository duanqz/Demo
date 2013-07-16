package org.xo.demo.helloworld.helloio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import org.xo.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class FileContentActivity extends Activity {

    private TextView mFileContentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_io_file_content_activity);
        mFileContentText = (TextView) findViewById(R.id.file_content);

        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");

        new AsyncFileReader().execute(new String[]{path});
    }

    private class AsyncFileReader extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            File file = new File(params[0]);
            
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            mFileContentText.setText(result);
        };

    }

    private static String loadNormal(File file) {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(
                    new FileInputStream(file));
            int length = bis.available();
            byte[] buffer = new byte[length];
            bis.read(buffer);

            return new String(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return null;
    }

    private static String loadX509Cert(File pulbicKeyFile) {
        FileInputStream fis = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            fis = new FileInputStream(pulbicKeyFile);

            Certificate cert = cf.generateCertificate(fis);

            return cert.toString();

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return null;
    }

    private static String loadKeystoreCert(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            KeyStore ks = KeyStore.getInstance("JKS");
//            ks.getCertificate(alias)
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
