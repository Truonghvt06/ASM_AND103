package truonghvph35818.fpoly.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;

import java.util.List;

import truonghvph35818.fpoly.Fragment.GioHangFragment;
import truonghvph35818.fpoly.Handle.Item_Cart_Handle;
import truonghvph35818.fpoly.MainActivity;
import truonghvph35818.fpoly.Model.Cart;
import truonghvph35818.fpoly.Model.Product;
import truonghvph35818.fpoly.R;


public class ChiTietSPActivity extends AppCompatActivity {

    ImageView img_tim, img_anh, img_back;
    TextView tv_price, tv_name, tv_moTa;
    TextView ed_soLuong;
    Button btn_add;
    AppCompatButton btn_cong, btn_tru;
    Item_Cart_Handle handle;
    List<Cart> list_cart;
    Cart cart;
    int soluong = 1;
    final int MIN_SO_LUONG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_spactivity);

        btn_add = findViewById(R.id.btn_add_chi_tiet);
        btn_cong = findViewById(R.id.btn_cong);
        btn_tru = findViewById(R.id.btn_tru);
        img_tim = findViewById(R.id.img_tim);
        img_anh = findViewById(R.id.img_anhSP_chitiet);
        img_back = findViewById(R.id.img_back);
        tv_name = findViewById(R.id.tv_name_chtiet);
        tv_price = findViewById(R.id.tv_price_chitiet);
        ed_soLuong = findViewById(R.id.tv_soLuong);
        tv_moTa = findViewById(R.id.tv_mota);



        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String price = intent.getStringExtra("price");
        String image = intent.getStringExtra("image");
        String description = intent.getStringExtra("description");

        tv_price.setText("$ " + price + "đ");
        tv_name.setText(productName);
        tv_moTa.setText(description);

        Glide.with(this)
                .load(image)
                .thumbnail(Glide.with(this).load(R.drawable.ic_launcher_background))
                .into(img_anh);

        btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soluong++;
                ed_soLuong.setText("     " + soluong + "    ");
            }
        });
        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(soluong > MIN_SO_LUONG){
                    soluong--;
                    ed_soLuong.setText("     " + soluong + "    ");
                }
            }
        });
//        ed_soLuong.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                // Kiểm tra nếu EditText không còn được focus
//                if (!hasFocus) {
//                    // Lấy số lượng từ EditText và cập nhật lại biến soLuong
//                    String input = ed_soLuong.getText().toString().trim();
//                    if (!input.isEmpty()) {
//                        soluong = Integer.parseInt(input);
//                        // Kiểm tra nếu số lượng nhỏ hơn số lượng tối thiểu, đặt lại số lượng là số lượng tối thiểu
//                        if (soluong < MIN_SO_LUONG) {
//                            soluong = MIN_SO_LUONG;
//                            ed_soLuong.setText(String.valueOf(MIN_SO_LUONG));
//                        }
//                    } else {
//                        // Nếu người dùng không nhập gì, đặt lại số lượng là số lượng tối thiểu
//                        soluong = MIN_SO_LUONG;
//                        ed_soLuong.setText(String.valueOf(MIN_SO_LUONG));
//                    }
//                }
//            }
//        });


        //Thêm
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một đối tượng Product và gán các giá trị từ UI cho nó
                Product product = new Product();
                product.setImage(img_anh.getDrawable().toString());
                product.setProductName(tv_name.getText().toString());
                product.setPrice(Integer.parseInt(tv_price.getText().toString()));

                Cart cart = new Cart();
                cart.getSoLuong();

// Truyền dữ liệu vào Intent để chuyển sang GioHangActivity
                Intent intent = new Intent(ChiTietSPActivity.this, GioHangFragment.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietSPActivity.this, MainActivity.class));
            }
        });

        // Thiết lập ảnh trái tim mặc định
        img_tim.setImageResource(R.drawable.yeu_thich);


        img_tim.setOnClickListener(new View.OnClickListener() {
            // Tạo một biến để kiểm tra trạng thái của ảnh
            boolean isRedHeart = false;
            @Override
            public void onClick(View view) {
//                holder.img_tim.setColorFilter(Color.RED);
                // Nếu ảnh trái tim hiện tại không phải là ảnh trái tim màu đỏ, chuyển sang ảnh trái tim màu đỏ, ngược lại chuyển sang ảnh trái tim mặc định
                if (!isRedHeart) {
                    img_tim.setImageResource(R.drawable.tim_nau);
                } else {
                    img_tim.setImageResource(R.drawable.yeu_thich);
                }
                // Đảo ngược trạng thái của biến kiểm tra
                isRedHeart = !isRedHeart;

            }
        });
    }
}