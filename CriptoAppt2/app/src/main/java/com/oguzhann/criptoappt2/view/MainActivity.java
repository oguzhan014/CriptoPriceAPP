package com.oguzhann.criptoappt2.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oguzhann.criptoappt2.MainActivity2;
import com.oguzhann.criptoappt2.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPersonName;
    private EditText editTextKey;
    private EditText editTextEmail;
    private Button buttonGiris;
    private Button buttonKayit;

    private String adi , email,sifre;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private HashMap<String,Object> mData;

    Intent intent;
   private DatabaseReference mRef;

    public class User {
        private String username;
        private String email;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPersonName = findViewById(R.id.personname);
        editTextKey = findViewById(R.id.key);
        editTextEmail = findViewById(R.id.emailbutton);
        buttonGiris = findViewById(R.id.btngir);
        buttonKayit = findViewById(R.id.btnkayit);

        mAuth=FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference();

        buttonGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        buttonKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adi=editTextPersonName.getText().toString();
                email=editTextEmail.getText().toString();
                sifre=editTextKey.getText().toString();

                if(!TextUtils.isEmpty(adi)&& ! TextUtils.isEmpty(email)&&!TextUtils.isEmpty(sifre)){
                    mAuth.createUserWithEmailAndPassword(email,sifre)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(MainActivity.this, "veri tabanına kaydınız yapıldı", Toast.LENGTH_SHORT).show();
                                }
                            });
                }

            }
        });

        buttonGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adi=editTextPersonName.getText().toString();
                email=editTextEmail.getText().toString();
                sifre=editTextKey.getText().toString();

                if(!TextUtils.isEmpty(adi)&& ! TextUtils.isEmpty(email)&&!TextUtils.isEmpty(sifre)){
                    mAuth.createUserWithEmailAndPassword(email,sifre)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    mUser=mAuth.getCurrentUser();
                                    mData=new HashMap<>();
                                    mData.put("kullanıcı adı :",adi);
                                    mData.put("kullanıcı E-mail :",email);
                                    mData.put("kullanıcı şifresi: ",sifre);
                                    mData.put("kullanıcı ID : ",mUser.getUid());
                                    mRef.child("Kullanıcılar").child(mUser.getUid())
                                            .setValue(mData)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(MainActivity.this, "Realtime veri tabanına kaydedildi", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                    Toast.makeText(MainActivity.this, "giriş yapıldı", Toast.LENGTH_SHORT).show();
                                    intent =new Intent(MainActivity.this,MainActivity2.class);
                                    startActivity(intent);
                                }
                            });
                }

            }
        });


    }
}