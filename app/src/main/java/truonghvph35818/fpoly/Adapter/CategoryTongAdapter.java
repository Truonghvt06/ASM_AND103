package truonghvph35818.fpoly.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import truonghvph35818.fpoly.Model.CategoryTong;
import truonghvph35818.fpoly.Model.Product;
import truonghvph35818.fpoly.Model.Response;
import truonghvph35818.fpoly.R;
import truonghvph35818.fpoly.Services.Request;


public class CategoryTongAdapter extends RecyclerView.Adapter<CategoryTongAdapter.ViewHolder>{

    private List<CategoryTong> sanPhamTongList;
    private Context context;
    private Request request;


    public CategoryTongAdapter(List<CategoryTong> sanPhamTongList, Context context, Request request) {
        this.sanPhamTongList = sanPhamTongList;
        this.context = context;
        this.request = request;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp_home_tong, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryTong sanPhamTong = sanPhamTongList.get(position);
        if(sanPhamTong == null){
            return;
        }
        holder.tv_name.setText(sanPhamTong.getNameCate());

        // Lấy id_category của danh mục tổng
        String idCategory = sanPhamTong.getId();

        // Gọi API để lấy danh sách sản phẩm theo id_category
        request.callApi()
                .getListProductByIdCategory(idCategory)
                .enqueue(new Callback<Response<ArrayList<Product>>>() {
            @Override
            public void onResponse(Call<Response<ArrayList<Product>>> call, retrofit2.Response<Response<ArrayList<Product>>> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 200){
                        //Laay data
                        ArrayList<Product> list = response.body().getData();
//                        getDataProduct(list);
                        ProductAdapter sanPhamAdapter = new ProductAdapter(list, context);
                        holder.rc_sanPham.setAdapter(sanPhamAdapter);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
                        holder.rc_sanPham.setLayoutManager(layoutManager);
//                        Toast.makeText(context, response.body().getMess(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<ArrayList<Product>>> call, Throwable t) {
                Log.e("API_CALL", "Failed to get product list: " + t.getMessage());

            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamTongList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        RecyclerView rc_sanPham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name_cate);
            rc_sanPham = itemView.findViewById(R.id.rc_sp_home_tong);

        }
    }
}
