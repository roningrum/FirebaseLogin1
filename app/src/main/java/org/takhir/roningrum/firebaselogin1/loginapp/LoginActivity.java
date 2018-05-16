package org.takhir.roningrum.firebaselogin1.loginapp;

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

import org.takhir.roningrum.firebaselogin1.MainActivity;
import org.takhir.roningrum.firebaselogin1.R;
import org.takhir.roningrum.firebaselogin1.ResetPassActivity;
import org.takhir.roningrum.firebaselogin1.registerapp.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_emailmasuk)
    EditText etEmailMasuk;
    @BindView(R.id.et_katasandi_masuk)
    EditText etKataSandi;
    @BindView(R.id.btn_masuk)
    Button BtnLogin;
    @BindView(R.id.tv_daftar)
    TextView tvDaftar;
    @BindView(R.id.tv_lupakatasandi)
    TextView textView;

    private FirebaseAuth mAuthlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuthlogin = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.btn_masuk})
    public void ClickMasuk(View v){
        String email = etEmailMasuk.getText().toString().trim();
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
                        if (!task.isSuccessful()) {
                            Log.d(TAG,"Gagal masuk");
                            if (password.length() < 6) {
                                etKataSandi.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(LoginActivity.this, getString(R.string.gagal_masuk), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d(TAG,"berhasil masuk");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
    @OnClick({R.id.tv_daftar})
    public void ClickDaftarpg(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
    @OnClick({R.id.tv_lupakatasandi})
    public void ClickResetPg(View v){
        Intent intent = new Intent(LoginActivity.this, ResetPassActivity.class);
        startActivity(intent);
    }

}
