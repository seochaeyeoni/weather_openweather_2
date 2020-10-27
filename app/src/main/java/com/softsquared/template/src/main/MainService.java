package com.softsquared.template.src.main;

import android.util.Log;

import com.softsquared.template.src.ApplicationClass;
import com.softsquared.template.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.template.src.main.interfaces.MainActivityView;
import com.softsquared.template.src.main.models.CurrentWeather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.template.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    int lat = Integer.parseInt(String.valueOf(Math.round(ApplicationClass.lat)));
    int lon = Integer.parseInt(String.valueOf(Math.round(ApplicationClass.lon)));
    void getTest() {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getTest(lat,lon,"9624f5754e9546192d5c78282c452fd3").enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                final CurrentWeather currentWeatherResponse = response.body();
                if (currentWeatherResponse == null) {
                    Log.d("network","null");
                    mMainActivityView.validateFailure(null);
                    return;
                }

                Log.d("network", String.valueOf(lat));
                Log.d("network", String.valueOf(lon));

                mMainActivityView.validateSuccess(currentWeatherResponse);
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Log.d("network","fail");
                mMainActivityView.validateFailure(null);
            }
        });
    }
}
