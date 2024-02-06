package com.example.copypasteandshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_copy,btn_share,btn_paste;
    private EditText edit_copy,edit_paste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_copy=findViewById(R.id.btn_copy);
        btn_share=findViewById(R.id.btn_share);
        btn_paste=findViewById(R.id.btn_paste);

        edit_copy=findViewById(R.id.edit_copy);
        edit_paste=findViewById(R.id.edit_paste);

        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView",edit_copy.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(MainActivity.this, "Text Copyed", Toast.LENGTH_SHORT).show();

            }
        });

        btn_paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData.Item item =clipboardManager.getPrimaryClip().getItemAt(0);
                edit_paste.setText(item.getText().toString());

                Toast.makeText(MainActivity.this, "Text Pasted", Toast.LENGTH_SHORT).show();


            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,edit_copy.getText().toString());
                intent.setType("text/plain");
                intent=Intent.createChooser(intent,"Share Via ");
                startActivity(intent);
            }
        });
    }
}