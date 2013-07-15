package org.xo.demo.tool.againstcrack;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.xo.demo.R;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.widget.TextView;

/**
 * How to against crack, this demo go through it.
 *
 */
public class AgainstCrackActivity extends Activity {

    private TextView mSignatureText, mCrcText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_againstcrack_activity);
        
        mSignatureText = (TextView) findViewById(R.id.signature);
        mCrcText = (TextView) findViewById(R.id.crc);

        mSignatureText.setText(Integer.toHexString(getSignature("org.xo.demo")));
        mCrcText.setText(String.valueOf(getCRC()));
    }

    public int getSignature(String packageName) {      
        PackageManager pm = this.getPackageManager();
        PackageInfo pi = null;
        int sig = 0;
        try {
            pi = pm.getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
            Signature[] s = pi.signatures;
            sig = s[0].hashCode();  
        } catch (Exception e1) {
            sig = 0;
            e1.printStackTrace();
        }
        return sig;
    }

    private long getCRC() {
        ZipFile zf;
        try {
            zf = new ZipFile(getApplicationContext().
                    getPackageCodePath());
            ZipEntry ze = zf.getEntry("classes.dex");
            return ze.getCrc();
        } catch (IOException e) {
            // Ignore
        }

        return -1;
    }

//    private void checkSignature() {
//        String signature = getString(R.string.signature);
//        return signature.equals(Integer.toHexString(getSignature("org.xo.demo")));
//    }

//    private void checkCRC() {
        //long crc = Long.parseLong(getString(R.string.crc));
        //return crc == getCRC();
//    } 
}
