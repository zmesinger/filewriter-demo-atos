package com.mesinger.filewriterdemoatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mesinger.filewriterdemoatos.databinding.ActivityMainBinding;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    private static final String TEST_FILE_NAME = "testfile.txt";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        writeListener();
        readListener();
    }

    private void readListener() {
        binding.readFromFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFromFile();
            }
        });
    }

    private void readFromFile() {
        String read="";
        try {
            InputStream inputStream = openFileInput(TEST_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String text = "";
            StringBuilder stringBuilder = new StringBuilder();
            while((text = bufferedReader.readLine()) != null){
                stringBuilder.append("\n").append(text);
            }
            inputStream.close();
            read = stringBuilder.toString();

            binding.outputTextView.setText(read);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeListener() {
        binding.writeToFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile(binding.inputTextField.getText().toString());
            }
        });
    }

    private void writeToFile(String input) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(TEST_FILE_NAME, MODE_PRIVATE));
            outputStreamWriter.write(input);
            outputStreamWriter.close();

            binding.inputTextField.setText(null);
            binding.outputTextView.setText("Press button to read data");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}