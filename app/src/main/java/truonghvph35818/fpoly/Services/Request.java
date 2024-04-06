package truonghvph35818.fpoly.Services;

import static truonghvph35818.fpoly.Services.ApiServices.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Request {
    private ApiServices apiServices;
    public Request(){
        apiServices = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiServices.class);
    }
    public ApiServices callApi(){
        return apiServices;
    }
}
