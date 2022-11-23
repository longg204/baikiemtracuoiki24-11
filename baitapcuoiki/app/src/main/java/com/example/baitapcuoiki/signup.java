package com.example.baitapcuoiki;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mData;
    private Button register;
    private EditText email;
    private EditText pass;
    private TextView signin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        AnhXa();
        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signup();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this, signin.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void AnhXa() {
        register = (Button) findViewById(R.id.btn_signup);
        email = (EditText) findViewById(R.id.email_signup);
        pass = (EditText) findViewById(R.id.password_signup);
        signin = (TextView) findViewById(R.id.text_signup);
    }

    private void Signup() {
        String Email = email.getText().toString();
        String Pass = pass.getText().toString();
        if (TextUtils.isEmpty(Email)) {
            email.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(Pass)) {
            pass.setError("Enter your password");
            return;
        } else if (Pass.length() < 6) {
            pass.setError("Length should be > 6");
            return;
        }
        mAuth.createUserWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signup.this, "Create Account Success!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(signup.this, "Create Account Fail!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
