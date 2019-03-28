package com.cuncisboss.asynctaskdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnClick;
    private EditText etTime;
    private TextView tvFinalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTime = (EditText) findViewById(R.id.in_time);
        btnClick = (Button) findViewById(R.id.btn_run);
        tvFinalResult = (TextView) findViewById(R.id.tv_result);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = etTime.getText().toString();
                runner.execute(sleepTime);
            }
        });

    }


    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "ProgressDialog",
                    "Wait for " + etTime.getText().toString() + " seconds");
        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Sleeping...");
            try {
                int time = Integer.parseInt(strings[0])* 1000;
                Thread.sleep(time);
                resp = "Slept for " + strings[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            tvFinalResult.setText("onProgressDialog: " + values[0]);
        }

        @Override
        protected void onPostExecute(String s) {    // hasil
            progressDialog.dismiss();
            tvFinalResult.setText("onPostExecute: " + s);
        }
    }

}





















