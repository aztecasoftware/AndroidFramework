package com.aztecasoftware.framework;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aztecasoftware.kernel.security.SessionInfo;
import com.aztecasoftware.kernel.security.UsuarioService;

import io.reactivex.functions.Consumer;

public class LoginActivity extends Activity {
    //Services
    UsuarioService usuarioSvc=new UsuarioService();
    //Views
    ProgressDialog progressDialog;
    EditText txtUser, txtPassword;
    Button btnLogin;

    View.OnClickListener btnLogin_Click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            progressDialog=ProgressDialog.show(LoginActivity.this, "Trabajando", "Iniciando sesión", true, true);
            usuarioSvc.login(txtUser.getText().toString(), txtPassword.getText().toString(), "mobile")
                    .subscribe(sessionInfo -> {
                                progressDialog.cancel();
                            },
                            throwable -> progressDialog.cancel());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser=(EditText)findViewById(R.id.txtUsuario);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        //
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(btnLogin_Click);
    }
}
