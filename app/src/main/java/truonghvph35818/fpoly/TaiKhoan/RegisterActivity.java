package truonghvph35818.fpoly.TaiKhoan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import truonghvph35818.fpoly.Model.Response;
import truonghvph35818.fpoly.Model.User;
import truonghvph35818.fpoly.R;
import truonghvph35818.fpoly.Services.Request;


public class RegisterActivity extends AppCompatActivity {
    TextInputEditText ed_email, ed_pass;
    Button btn_register;
    TextView tv_login;
    File file;
    private Request request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed_email = findViewById(R.id.ed_email_register);
        ed_pass = findViewById(R.id.ed_pass_register);
        btn_register = findViewById(R.id.btn_register);
        tv_login = findViewById(R.id.tv_login);

        request = new Request();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ed_email.getText().toString();
                String password = ed_pass.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Nhập thông tin đầy đủ!", Toast.LENGTH_SHORT).show();

                }else {
                    // Tạo một đối tượng User mới và gán giá trị position
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setPassword(password);
                    newUser.setPosition(2);

//                    RequestBody _password = RequestBody.create(MediaType.parse("multipart/form-data"), ed_pass.getText().toString());
//                    RequestBody _email = RequestBody.create(MediaType.parse("multipart/form-data"), ed_email.getText().toString());
//                    request.callApi()
//                            .register( _password, _email, newUser)
//                            .enqueue(responseUser);
                    request.callApi()
                            .addUser(newUser)
                            .enqueue(responseUser);
                }
            }
        });


        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }


    Callback<Response<User>> responseUser = new Callback<Response<User>>() {
        @Override
        public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200) {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<User>> call, Throwable t) {
            Log.e("Lỗi", t.getMessage());

        }
    };
 }