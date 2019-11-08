package com.example.hol5dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            progressDialog.dismiss();
        }
    };
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button resetButton = (Button)findViewById(R.id.btnReset);
        resetButton.setOnClickListener(resetButtonListener);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Apakah Anda yakin untuk mereset nilai protein?")
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this,
                                "Tidak jadi reset",Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this,"Melakukan RESET !!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Melakukan sesuatu ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Thread thread = new Thread(new Runnable() 
        {
          public void run() {
              try
              {
                  Thread.sleep(3000);
                  handler.sendEmptyMessage(0);
              }
              catch (InterruptedException e)
              {
                  e.printStackTrace();
              }
          }    
        });
        thread.start();
        progressDialog.show();
    }


}
