package com.example.bsce20040_lab07;

import android.Manifest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AdView mAdView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Button buttonCreateFile;
    TextView textViewFilePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCreateFile = findViewById(R.id.buttonCreateFile);
        textViewFilePath = findViewById(R.id.textViewFilePath);

        buttonCreateFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndWriteToFile();
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void createAndWriteToFile() {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(dir, "test.txt");

        try {
            FileWriter writer = new FileWriter(file);
            writer.append("Data written successfully ....");
            writer.flush();
            writer.close();
            String filePath = "File Path: " + file.getAbsolutePath();
            textViewFilePath.setText(filePath);
            Toast.makeText(this, "File created successfully .... ", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}