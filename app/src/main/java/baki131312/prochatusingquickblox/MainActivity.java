package baki131312.prochatusingquickblox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String APP_ID = "63773";
    static final String AUTH_KEY = "BHGFTVh5fYzNXO8";
    static final String AUTH_SECRET = "4pWrkbUuVfJu7XA";
    static final String ACCOUNT_KEY = "3bz759KSrBzQFYRZC57Z";

    Button button_mainSigup;
    Button button_mainLogin;

    EditText main_editLogin;
    EditText main_editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUIViews();
        intializeFrameworks();
        button_mainSigup.setOnClickListener(this);
        button_mainLogin.setOnClickListener(this);
    }

    private void intializeFrameworks() {
        QBSettings.getInstance().init(getApplicationContext(), APP_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
    }

    public  void setUpUIViews(){

        button_mainLogin = (Button)findViewById(R.id.button_mainLogin);
        button_mainSigup = (Button)findViewById(R.id.button_mainSigup);

        main_editLogin= (EditText) findViewById(R.id.main_editLogin);
        main_editPassword = (EditText) findViewById(R.id.main_editPassword);


    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_mainLogin){

            String  user = main_editLogin.getText().toString();
            String  password = main_editPassword.getText().toString();

            QBUser qbUser = new QBUser(user, password);

            QBUsers.signIn(qbUser).performAsync( new QBEntityCallback<QBUser>() {
                @Override
                public void onSuccess(QBUser user, Bundle args) {
                    // success
                    Toast.makeText(getBaseContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(QBResponseException error) {
                    // error

                    Toast.makeText(getBaseContext(),""+ error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }

        if (view.getId()== R.id.button_mainSigup){
            startActivity(new Intent(MainActivity.this,SignUpActivity.class));
        }
    }
}
