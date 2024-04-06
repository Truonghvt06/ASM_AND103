package truonghvph35818.fpoly.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import truonghvph35818.fpoly.Adapter.GioHangAdapter;
import truonghvph35818.fpoly.Handle.Item_Cart_Handle;
import truonghvph35818.fpoly.Model.Cart;
import truonghvph35818.fpoly.Model.CategoryTong;
import truonghvph35818.fpoly.Model.Product;
import truonghvph35818.fpoly.Model.Response;
import truonghvph35818.fpoly.R;
import truonghvph35818.fpoly.Services.Request;


public class GioHangFragment extends Fragment implements Item_Cart_Handle {
    RecyclerView rcv_gio_hang;
    TextView tv_tong_gia;
    Button btn_mua;
    Request request;
    GioHangAdapter adapter;
    List<Product> list_pro;
    List<CategoryTong> list_cate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_gio_hang, container, false);

        rcv_gio_hang = view.findViewById(R.id.rc_giohang);
        tv_tong_gia = view.findViewById(R.id.tv_tongtien);
        btn_mua = view.findViewById(R.id.btn_mua_giohang);

        request = new Request();

        request.callApi()
                .getListCart()
                .enqueue(getCartAPI);





        return view;
    }

    Callback<Response<ArrayList<Cart>>> getCartAPI = new Callback<Response<ArrayList<Cart>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Cart>>> call, retrofit2.Response<Response<ArrayList<Cart>>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200){
                    ArrayList<Cart> list = response.body().getData();

                    getData(list);
                    Toast.makeText(getContext(), response.body().getMess(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Cart>>> call, Throwable t) {
            Log.d(">>> GetListDistributor", "onFailure" + t.getMessage());

        }
    };

    Callback<Response<Cart>> responseCartAPI = new Callback<Response<Cart>>() {
        @Override
        public void onResponse(Call<Response<Cart>> call, retrofit2.Response<Response<Cart>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200){
                    request.callApi()
                            .getListCart()
                            .enqueue(getCartAPI);
                    Toast.makeText(getContext(), response.body().getMess(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<Cart>> call, Throwable t) {
            Log.d(">>> GetListDistributor", "onFailure" + t.getMessage());

        }
    };

    private void getData(ArrayList<Cart> list) {
        adapter = new GioHangAdapter(getContext(), list,list_pro, list_cate, this);
        rcv_gio_hang.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_gio_hang.setAdapter(adapter);
    }


    @Override
    public void Add(Cart cart) {
        request.callApi()
                .addCart(cart)
                .enqueue(responseCartAPI);
    }

    @Override
    public void Delete(String id) {
        request.callApi()
                .deleteCart(id)
                .enqueue(responseCartAPI);
    }
}
