package truonghvph35818.fpoly.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import truonghvph35818.fpoly.Handle.Item_Cart_Handle;
import truonghvph35818.fpoly.Model.Cart;
import truonghvph35818.fpoly.Model.CategoryTong;
import truonghvph35818.fpoly.Model.Product;
import truonghvph35818.fpoly.R;


public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    Context context;
    List<Cart> list;
    List<Product> list_pro;
    List<CategoryTong> list_cate;
    Item_Cart_Handle handle;

    public GioHangAdapter(Context context, List<Cart> list, List<Product> list_pro, List<CategoryTong> list_cate, Item_Cart_Handle handle) {
        this.context = context;
        this.list = list;
        this.list_pro = list_pro;
        this.list_cate = list_cate;
        this.handle = handle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gio_hang, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = list.get(position);

        //Ten sp
        Product product = getPro(cart.getId_product());

        if (product == null) {
            return;
        }

        // Hiển thị thông tin sản phẩm
        Glide.with(context)
                .load(product.getImage())
                .into(holder.img_sp);
        holder.price.setText(product.getProductName());
        holder.name_pro.setText(product.getPrice() + "VND");
        //Teen the loai
//        String tenCate = getCate(product.getId_category());
//        if(tenCate == null){
//            return;
//        }
//        holder.category.setText("Category: " + tenCate);
        holder.category.setText("Category" + product.getCategoryTong().getId());
        holder.quantity.setText(cart.getSoLuong());

        //Xóa
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handle.Delete(cart.getId());
            }
        });

        //Mua
        holder.btn_mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        

    }


    //Lấy tên Pro
    private Product getPro(String id) {
        for (Product s : list_pro) {
            if (id.equals(s.getId())) {
                return s;
            }
        }
        return null;
    }
    //Lấy tên Cate
    private String getCate(String id) {
        for (CategoryTong s : list_cate) {
            if (id.equals(s.getId())) {
                return s.getNameCate();
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_sp;
        TextView price, name_pro, category, quantity, btn_mua, btn_xoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_sp = itemView.findViewById(R.id.img_anh_sp_gioHang);
            price = itemView.findViewById(R.id.tv_giasp_giohang);
            name_pro = itemView.findViewById(R.id.tv_tensp_gioHang);
            category = itemView.findViewById(R.id.tv_theloai_gioHang);
            quantity = itemView.findViewById(R.id.tv_soluongsp_giohang);
            btn_mua = itemView.findViewById(R.id.tv_mua_giohang);
            btn_xoa = itemView.findViewById(R.id.tv_xoa_giohang);
        }
    }
}
