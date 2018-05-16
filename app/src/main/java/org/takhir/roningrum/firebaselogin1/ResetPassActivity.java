package org.takhir.roningrum.firebaselogin1;

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
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPassActivity extends AppCompatActivity {
    @BindView(R.id.et_emailreset)
    EditText etEmailReset;
    @BindView(R.id.bt_reset)
    Button BtReset;
    @BindView(R.id.bt_back)
    Button BtBack;
    private FirebaseAuth mAuthReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        ButterKnife.bind(this);
        mAuthReset = FirebaseAuth.getInstance();
    }
    @OnClick({R.id.bt_reset})
    public void ClickReset(View v){
        String emailReset = etEmailReset.getText().toString().trim();
        final String TAG = "Reset";
        if(emailReset.isEmpty()){
            Toast.makeText(getApplicationContext(), "Email harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuthReset.sendPasswordResetEmail(emailReset).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "Reset Berhasil");
                    Toast.makeText(ResetPassActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ResetPassActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                }
                }
        });
    }
    @OnClick({R.id.bt_back})
    public void ClickBack(View v){
        finish();
    }
}
