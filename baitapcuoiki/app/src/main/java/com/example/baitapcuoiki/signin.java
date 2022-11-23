package com.example.baitapcuoiki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {

    private TextInputEditText email, pass;
    private Button btn_signin;
    private TextView signin, forgot;
    private ProgressDialog progressDialog;
    private FirebaseAuth Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        Auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_signin);
        pass = findViewById(R.id.password_signin);
        btn_signin = findViewById(R.id.btn_signin);
        forgot = (TextView) findViewById(R.id.forgotpassword);
        progressDialog = new ProgressDialog(this);
        signin = findViewById(R.id.text_signin);
        signin.setOnClickListener(view -> {
            startActivity(new Intent(signin.this, signup.class));
            finish();
        });
        btn_signin.setOnClickListener(view -> {
            Login();
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signin.this, forgotpassword.class));
            }
        });
    }
    private void Login(){
        String Email = email.getText().toString();
        String Pass = pass.getText().toString();
        if(TextUtils.isEmpty(Email)){
            email.setError("Enter your email");
            return;
        }else if(TextUtils.isEmpty(Pass)){
            pass.setError("Enter your password");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        Auth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signin.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signin.this, home.class));
                    finish();
                }else{
                    Toast.makeText(signin.this, "Sign in fail!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}