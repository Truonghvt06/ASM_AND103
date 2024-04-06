package truonghvph35818.fpoly.TaiKhoan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import truonghvph35818.fpoly.AdminActivity;
import truonghvph35818.fpoly.MainActivity;
import truonghvph35818.fpoly.Model.Response;
import truonghvph35818.fpoly.Model.User;
import truonghvph35818.fpoly.R;
import truonghvph35818.fpoly.Services.Request;


public class LoginActivity extends AppCompatActivity {
    TextInputEditText ed_email, ed_pass;
    Button btn_login;
    TextView tv_register;
    CheckBox cb_nho;
    private Request request;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_email = findViewById(R.id.ed_email_login);
        ed_pass = findViewById(R.id.ed_pass_login);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
        cb_nho = findViewById(R.id.cb_remember);

        request = new Request();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        //CHECK BOX
        // Kiểm tra và thiết lập trạng thái của CheckBox và mật khẩu dựa trên SharedPreferences
        boolean rememberChecked = sharedPreferences.getBoolean("rememberChecked", false);
        cb_nho.setChecked(rememberChecked);

        // Nếu CheckBox đã được chọn và có dữ liệu mật khẩu được lưu, điền dữ liệu vào trường nhập liệu
        if (rememberChecked) {
            String savedEmail = sharedPreferences.getString("email", "");
            String savedPassword = sharedPreferences.getString("password", "");
            ed_email.setText(savedEmail);
            ed_pass.setText(savedPassword);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                String email = ed_email.getText().toString().trim();
                String pass = ed_pass.getText().toString().trim();
                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    user.setEmail(email);
                    user.setPassword(pass);

                    request.callApi().login(user)
                            .enqueue(responseUser);
                }
            }
        });

        // Xử lý sự kiện khi nhấn vào CheckBox "Nhớ mật khẩu"
        cb_nho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Lưu trạng thái của CheckBox vào SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("rememberChecked", isChecked);
                editor.apply();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    Callback<Response<User>> responseUser = new Callback<Response<User>>() {
        @Override
        public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    User loginPosition = response.body().getData();
                    int userPosition = loginPosition.getPosition();

                    // Kiểm tra giá trị của position và chuyển hướng tương ứng
                    if (userPosition == 2) {
                        // Chuyển hướng người dùng đến MainActivity
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else if (userPosition == 1) {
                        // Chuyển hướng người dùng đến AdminActivity
                        startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                    }


                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", response.body().getToken());
                    editor.putString("refreshToken", response.body().getRefreshToken());
                    editor.putString("id", response.body().getData().getId());

                    // Lưu email và mật khẩu vào SharedPreferences nếu CheckBox "Nhớ mật khẩu" được chọn
                    if (cb_nho.isChecked()) {
                        String email = ed_email.getText().toString().trim();
                        String password = ed_pass.getText().toString().trim();
                        editor.putString("email", email);
                        editor.putString("password", password);
                    } else {
                        // Xóa email và mật khẩu đã lưu nếu CheckBox "Nhớ mật khẩu" không được chọn
                        editor.remove("email");
                        editor.remove("password");
                    }

                    editor.apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<User>> call, Throwable t) {
            Log.e("Lỗi", "Fail" + t.getMessage());
            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
        }
    };
}

