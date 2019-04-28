package com.lesnyg.test2movieinfoproject;

import com.lesnyg.test2movieinfoproject.models.Result;
import com.lesnyg.test2movieinfoproject.models.Search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewModel extends ViewModel {
    public MutableLiveData<List<Result>> filteredResult = new MutableLiveData<>();
    public MutableLiveData<List<Result>> searchResult = new MutableLiveData<>();

    private static final String MY_KEY = "0882850438bd0da4458576be7d4a447c";
    private static final String MY_COUNTRY = "ko-KR";
    public MutableLiveData<List<Result>> favoritList = new MutableLiveData<>();
    private List<Result> mResults = new ArrayList<>();
     private Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
     private MovieService service = retrofit.create(MovieService.class);

    public MovieViewModel() {
        favoritList.setValue(new ArrayList<>());
    }

    public void fetchUpComing(){
         service.getUpComing(MY_KEY, MY_COUNTRY).enqueue(new Callback<Search>() {
             @Override
             public void onResponse(Call<Search> call, Response<Search> response) {
                 if(response.body() != null) {
                     filteredResult.setValue(response.body().getResults());
                 }
             }
             @Override
             public void onFailure(Call<Search> call, Throwable t) {

             }
         });
     }
     public  void fetchFavorite(){
        service.getPopular(MY_KEY,MY_COUNTRY).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                favoritList.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
     }
     public  void fetchPopular(){
        service.getPopular(MY_KEY,MY_COUNTRY).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if(response.body() != null) {
                    filteredResult.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
     }

     public void fetchSearch(String quary){
        service.getSearch(MY_KEY,quary,MY_COUNTRY).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if(response.body() != null) {
                    filteredResult.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
     }

     //즐겨찾기 추가하기
     public void addFavorit(Result item){
         List<Result> list = favoritList.getValue();
         list.add(item);

         favoritList.setValue(list);
     }
     //즐겨찾기 삭제하기
     public void removeFavorit(Result item){
         favoritList.getValue().remove(item);
         favoritList.setValue(favoritList.getValue());
     }
}
