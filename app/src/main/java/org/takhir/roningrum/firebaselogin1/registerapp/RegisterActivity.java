package org.takhir.roningrum.firebaselogin1.registerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.takhir.roningrum.firebaselogin1.R;
import org.takhir.roningrum.firebaselogin1.loginapp.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.et_email)
    EditText etDaftarEmail;
    @BindView(R.id.et_nama)
    EditText etDaftarNama;
    @BindView(R.id.et_katasandi)
    EditText etDaftarSandi;
    @BindView(R.id.et_konfirmasi)
    EditText etDaftarSandiKonfirmasi;
    @BindView(R.id.bt_reg)
    Button btDaftar;
    @BindView(R.id.tv_login)
    TextView tvMasuk;
    private FirebaseAuth mAuthreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mAuthreg = FirebaseAuth.getInstance();

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuthreg.getCurrentUser();
//        updateUI(currentUser);
//    }
//
//    private void updateUI(FirebaseUser currentUser) {
//    }

    @OnClick({R.id.bt_reg})
    public void ClickbtDaftar(View v) {
        final String name = etDaftarNama.getText().toString().trim();
        final String email = etDaftarEmail.getText().toString().trim();
        final String password = etDaftarSandi.getText().toString().trim();
        final String confirmPass = etDaftarSandiKonfirmasi.getText().toString().trim();
        final String TAG = "Pesan";
        if (name.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "email tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "password tidak noleh kosong", Toast.LENGTH_SHORT).show();
        } else if (confirmPass.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Jangan lupa di isi juga", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuthreg.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                            } else {
                                // If sign in fails, display a message to the user.

                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    @OnClick ({R.id.tv_login})
   public  void ClickLoginpg(View v){
      Intent intent = new Intent (this, LoginActivity.class);
      startActivity(intent);
      finish();

    }
}