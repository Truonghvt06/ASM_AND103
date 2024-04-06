package truonghvph35818.fpoly.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import truonghvph35818.fpoly.Activity.ChiTietSPActivity;
import truonghvph35818.fpoly.Model.Product;
import truonghvph35818.fpoly.R;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    List<Product> listSP;
    Context context;

    public ProductAdapter(List<Product> listSP, Context context) {
        this.listSP = listSP;
        this.context = context;
    }

    public void setData(List<Product> list) {
        this.listSP = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp_home, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product sanPham = listSP.get(position);
        if(sanPham == null){
            return;
        }

//        if (sanPham.getImage() != null) {
//            // Sử dụng Glide để tải và hiển thị hình ảnh từ URL
//            Glide.with(context)
//                    .load(sanPham.getImage())
//                    .thumbnail(Glide.with(context).load(R.drawable.ic_launcher_background))
//                    .into(holder.img_anh);
//        }
        Glide.with(context)
                .load(sanPham.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.img_anh);

//        Picasso.get().load(sanPham.getImage()).into(holder.img_anh);

        holder.tv_name.setText(sanPham.getProductName());
        holder.tv_price.setText("$" + String.valueOf(sanPham.getPrice()) + "đ");
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSPActivity.class);

                // Đính kèm dữ liệu sản phẩm vào Intent
                intent.putExtra("productName", sanPham.getProductName());
                intent.putExtra("price", String.valueOf(sanPham.getPrice()));
                intent.putExtra("image", sanPham.getImage());
                intent.putExtra("description", sanPham.getDescription());



                context.startActivity(intent);
            }
        });


        // Thiết lập ảnh trái tim mặc định
        holder.img_tim.setImageResource(R.drawable.yeu_thich);


        holder.img_tim.setOnClickListener(new View.OnClickListener() {
            // Tạo một biến để kiểm tra trạng thái của ảnh
            boolean isRedHeart = false;
            @Override
            public void onClick(View view) {
//                holder.img_tim.setColorFilter(Color.RED);
                // Nếu ảnh trái tim hiện tại không phải là ảnh trái tim màu đỏ, chuyển sang ảnh trái tim màu đỏ, ngược lại chuyển sang ảnh trái tim mặc định
                if (!isRedHeart) {
                    holder.img_tim.setImageResource(R.drawable.tim_nau);
                } else {
                    holder.img_tim.setImageResource(R.drawable.yeu_thich);
                }
                // Đảo ngược trạng thái của biến kiểm tra
                isRedHeart = !isRedHeart;

            }
        });

    }

    @Override
    public int getItemCount() {
//        return listSP.size();
        if (listSP != null) {
            return listSP.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name, tv_price;
        ImageView img_tim, img_anh;
        Button btn_add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.name_sp);
            tv_price = itemView.findViewById(R.id.price_sp);
            img_anh = itemView.findViewById(R.id.image_sp);
            img_tim = itemView.findViewById(R.id.img_tim_sp);
            btn_add = itemView.findViewById(R.id.btn_add_home);



        }
    }

}
