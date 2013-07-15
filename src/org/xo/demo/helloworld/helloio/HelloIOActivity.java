package org.xo.demo.helloworld.helloio;

import java.io.File;

import org.xo.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Demo to show Android IO operations.
 *
 */
public class HelloIOActivity extends Activity {

    private EditText mEditDir;
    private Button mBtnList;
    private ListView mListFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_io_activity);

        inflateViews();
    }

    private void inflateViews() {
        mEditDir = (EditText) findViewById(R.id.directory);
        mBtnList = (Button) findViewById(R.id.btn_list_file);
        mBtnList.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final String dir = mEditDir.getText().toString();
                new ListFilesTask().execute(new String[]{dir});
            }
        });

        mListFiles = (ListView) findViewById(R.id.files_list);
        mListFiles.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                File file = (File) mListFiles.getAdapter().getItem(position);
                if(file.isFile()) {
                    Intent intent = new Intent(HelloIOActivity.this,
                            FileContentActivity.class);
                    intent.putExtra("path", file.getAbsolutePath());
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Asynchronous task to list files in directory.
     */
    private class ListFilesTask extends AsyncTask<String, Void, File[]> {

        @Override
        protected File[] doInBackground(String... params) {
            File dir = new File(params[0]);
            File[] files = null;

            if(!dir.exists()) {
                // Directory not exists
                files = new File[]{};
            }

            if(dir.isFile()) {
                files = new File[]{dir};
            }

            if(dir.isDirectory()) {
                files = dir.listFiles();
            }

            return files;
        }

        @Override
        protected void onPostExecute(File[] result) {
            if(result == null) {
                Toast.makeText(HelloIOActivity.this,
                        getString(R.string.no_files),
                        Toast.LENGTH_SHORT).show();

                return;
            }

            mListFiles.setAdapter(
                    new ArrayAdapter<File>(HelloIOActivity.this,
                    android.R.layout.simple_expandable_list_item_1,
                    result));
        }
    }
}
