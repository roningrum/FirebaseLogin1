package org.takhir.roningrum.firebaselogin1.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.takhir.roningrum.firebaselogin1.MainActivity;
import org.takhir.roningrum.firebaselogin1.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_emailmasuk)
    EditText etEmailMasuk;
    @BindView(R.id.et_katasandi_masuk)
    EditText etKataSandi;
    @BindView(R.id.btn_masuk)
    Button BtnLogin;

    private FirebaseAuth mAuthlogin;
    private FirebaseAuth.AuthStateListener mAuthloginListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuthlogin = FirebaseAuth.getInstance();
        mAuthloginListener= new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();
               final String TAG = "Sukses";
               if(user!=null){
                   Log.d(TAG, "OnAuthStateChanged: Sign in" +user.getUid());
               } else{
                   Log.d(TAG, "OnAuthStateChange: Sign out");
               }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuthlogin.addAuthStateListener(mAuthloginListener);
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthloginListener!= null) {
            mAuthlogin.removeAuthStateListener(mAuthloginListener);
        }
    }
    @OnClick({R.id.btn_masuk})
    public void ClickMasuk(View v){
        final String email = etEmailMasuk.getText().toString().trim();
        final String password = etKataSandi.getText().toString().trim();
        final String TAG = "masuk";
        if(email.isEmpty()){
            Toast.makeText(getApplicationContext(),"Masukkan email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Masukkan kata sandi", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuthlogin.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
