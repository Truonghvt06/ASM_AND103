package truonghvph35818.fpoly.Services;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import truonghvph35818.fpoly.Model.Bill;
import truonghvph35818.fpoly.Model.Cart;
import truonghvph35818.fpoly.Model.Category;
import truonghvph35818.fpoly.Model.CategoryTong;
import truonghvph35818.fpoly.Model.Product;
import truonghvph35818.fpoly.Model.Response;
import truonghvph35818.fpoly.Model.User;


public interface ApiServices {

    public static String BASE_URL = "http://192.168.0.105:3000/api/";

    @GET("get-user")
    Call<Response<ArrayList<User>>> getListUser();
    @GET("get-category")
    Call<Response<ArrayList<CategoryTong>>> getListCategory();
    @GET("get-product-by-category/{id_category}")
    Call<Response<ArrayList<Product>>> getListProductByIdCategory(@Path("id_category") String id_category);
    @GET("get-product")
    Call<Response<ArrayList<Product>>> getListProduct();
    @GET("get-bill")
    Call<Response<ArrayList<Bill>>> getListBill();
    @GET("get-cart")
    Call<Response<ArrayList<Cart>>> getListCart();

    @POST("add-user")
    Call<Response<User>> addUser(@Body User user);
    @POST("add-category")
    Call<Response<Category>> addCategory(@Body Category category);
    @POST("add-product")
    Call<Response<Product>> addProduct(@Body Product product);
    @POST("add-bill")
    Call<Response<Bill>> addBill(@Body Bill bill);
    @POST("add-cart")
    Call<Response<Cart>> addCart(@Body Cart cart);

    @PUT("update-user/{id}")
    Call<Response<User>> updateUser(@Path("id") String id, @Body User user);
    @PUT("update-category/{id}")
    Call<Response<Category>> updateCategory(@Path("id") String id, @Body Category category);
    @PUT("update-product/{id}")
    Call<Response<Product>> updateProduct(@Path("id") String id, @Body Product product);
    @PUT("update-bill/{id}")
    Call<Response<Bill>> updateBill(@Path("id") String id, @Body Bill bill);

    @DELETE("delete-user/{id}")
    Call<Response<User>> deleteUser(@Path("id") String id);
    @DELETE("delete-category/{id}")
    Call<Response<Category>> deleteCategory(@Path("id") String id);
    @DELETE("delete-product/{id}")
    Call<Response<Product>> deleteProduct(@Path("id") String id);
    @DELETE("delete-bill/{id}")
    Call<Response<Bill>> deleteBill(@Path("id") String id);
    @DELETE("delete-cart/{id}")
    Call<Response<Cart>> deleteCart(@Path("id") String id);


    @Multipart
    @POST("register-email")
    Call<Response<User>> register(
                                  @Part("password") RequestBody password,
                                  @Part("email") RequestBody email,
                                  @Body User user
                                  );

    @POST("login")
    Call<Response<User>> login(@Body User user);

//    @POST("remember-password")
//    Call<Response<User>> rememberPassword(@Body User user);


}
