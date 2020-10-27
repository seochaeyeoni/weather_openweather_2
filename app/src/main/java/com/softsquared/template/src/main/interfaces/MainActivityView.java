package com.softsquared.template.src.main.interfaces;

import com.softsquared.template.src.main.models.CurrentWeather;

import java.util.List;

public interface MainActivityView {

    void validateSuccess(CurrentWeather currentWeatherResponse);

    void validateFailure(String message);
}
