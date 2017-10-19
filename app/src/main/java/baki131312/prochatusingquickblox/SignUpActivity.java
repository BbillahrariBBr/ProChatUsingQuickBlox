package baki131312.prochatusingquickblox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_signUpCancle;
    Button button_signSigup;

    EditText signup_editLogin;
    EditText signup_editPassword;
    EditText signup_editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUpUISignup();
        resgisterSession();
        button_signUpCancle.setOnClickListener(this);
        button_signSigup.setOnClickListener(this);
    }

    private void resgisterSession() {
        QBAuth.createSession().performAsync(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException error) {
                Log.e("Error",error.getMessage());
            }
        });
    }

    public void setUpUISignup(){
        button_signUpCancle = (Button)findViewById(R.id.button_signUpCancle);
        button_signSigup = (Button)findViewById(R.id.button_signSigup);
        signup_editLogin = (EditText)findViewById(R.id.signup_editLogin);
        signup_editPassword = (EditText)findViewById(R.id.signup_editPassword);
        signup_editEmail = (EditText)findViewById(R.id.signup_editEmail);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_signUpCancle){
            finish();
        }
        if (view.getId()==R.id.button_signSigup){
            String  user = signup_editLogin.getText().toString();
            String  password = signup_editPassword.getText().toString();
            String  email = signup_editEmail.getText().toString();

            QBUser qbUser= new QBUser(user, password,email);

            QBUsers.signUp(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                @Override
                public void onSuccess(QBUser user, Bundle args) {
                    // success
                    Toast.makeText(getBaseContext(),"SignUp Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(QBResponseException error) {
                    // error
                    Toast.makeText(getBaseContext(),""+ error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
