package truonghvph35818.fpoly.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import truonghvph35818.fpoly.Adapter.CategoryTongAdapter;
import truonghvph35818.fpoly.Adapter.ProductAdapter;
import truonghvph35818.fpoly.Adapter.SlideAdapter;
import truonghvph35818.fpoly.Handle.List_Product;
import truonghvph35818.fpoly.Model.CategoryTong;
import truonghvph35818.fpoly.Model.Response;
import truonghvph35818.fpoly.Model.SlideItem;
import truonghvph35818.fpoly.R;
import truonghvph35818.fpoly.Services.Request;


public class HomeFragment extends Fragment implements List_Product {

    ViewPager2 viewPager2;
    RecyclerView rc_home;
    CategoryTongAdapter adapter;
    Request request;

    ProductAdapter productAdapter;
    List_Product product;


    //implemeting auto slide facility
    private Handler handler = new Handler();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);

        viewPager2 = view.findViewById(R.id.view_pager2);
        rc_home = view.findViewById(R.id.rc_home);

        request = new Request();




        //Lay danh sach San pham tong
        request.callApi()
                .getListCategory()
                .enqueue(getProductTongApi);





        //SLIDE
        List<SlideItem> slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.drawable.banner_1));
        slideItems.add(new SlideItem(R.drawable.banner_2));
        slideItems.add(new SlideItem(R.drawable.banner_3));

        viewPager2.setAdapter(new SlideAdapter(slideItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(38));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);

            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });


        return view;
    }
    private void getData(ArrayList<CategoryTong> list){
        adapter = new CategoryTongAdapter(list, getContext(), request);
        rc_home.setLayoutManager(new LinearLayoutManager(getContext()));
        rc_home.setAdapter(adapter);
    }


    Callback<Response<ArrayList<CategoryTong>>> getProductTongApi = new Callback<Response<ArrayList<CategoryTong>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<CategoryTong>>> call, retrofit2.Response<Response<ArrayList<CategoryTong>>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    //Laay data
                    ArrayList<CategoryTong> list = response.body().getData();
                    getData(list);
                    getList();
//                    Toast.makeText(getContext(), response.body().getMess(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<CategoryTong>>> call, Throwable t) {
            Log.e("API_CALL", "Failed to get product list: " + t.getMessage());
        }
    };
//    Callback<Response<ArrayList<Product>>> getProductApi = new Callback<Response<ArrayList<Product>>>() {
//        @Override
//        public void onResponse(Call<Response<ArrayList<Product>>> call, retrofit2.Response<Response<ArrayList<Product>>> response) {
//            if(response.isSuccessful()){
//                if(response.body().getStatus() == 200){
//                    //Laay data
//                    ArrayList<Product> list = response.body().getData();
////                    getDataProduct(list);
////                    productAdapter.setData(list);
//                    getDataProduct(list);
//                    Toast.makeText(getContext(), response.body().getMess(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//
//        @Override
//        public void onFailure(Call<Response<ArrayList<Product>>> call, Throwable t) {
//            Log.e("API_CALL", "Failed to get product list: " + t.getMessage());
//
//        }
//    };


    //RecyclerView
//    private List<ProductTong> getList() {
//        List<ProductTong> list = new ArrayList<>();
//        List<Product> list1 = new ArrayList<>();
//
//        list1.add(new Product(R.drawable.anh_coffee_1, "Caffee", "$ 10000"));
//        list1.add(new Product(R.drawable.anh_coffee_1, "Caffee", "$ 20000"));
//        list1.add(new Product(R.drawable.anh_coffee_1, "Caffee", "$ 30000"));
//        list1.add(new Product(R.drawable.anh_coffee_1, "Caffee", "$ 40000"));
//
//
//        list.add(new ProductTong("Sản phẩm nổi bật", list1));
//        list.add(new ProductTong("Sản phẩm nổi bật 1", list1));
//        list.add(new ProductTong("Sản phẩm nổi bật 2", list1));
//        list.add(new ProductTong("Sản phẩm nổi bật 3", list1));
//
//        return list;
//    }




    //SLIDE
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public void getList() {
//        //Lay danh sach san pham
//        request.callApi()
//                .getListProduct()
//                .enqueue(getProductApi);
    }
}
