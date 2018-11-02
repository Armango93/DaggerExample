package com.andrewxa.a36dagger2simpleexample;

import android.app.Activity;
import android.content.Context;

import com.andrewxa.a36dagger2simpleexample.adapter.RecyclerViewAdapter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AndroidInjectionModule.class)
abstract class AppModule {
    @PerActivity
    @Binds
    abstract Context context(MainActivity mainActivity);

    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivityInjector();

    @PerActivity
    @Binds
    abstract MainContract.Presenter presenter(MainPresenterImpl presenter);

    @PerActivity
    @Binds
    abstract MainContract.GetQuoteInteractor iteractor(GetQuoteInteractorImpl getQuoteInteractor);

    @PerActivity
    @Binds
    abstract MainContract.MainView view(MainActivity activity);

    @PerActivity
    @Binds
    abstract RecyclerViewAdapter.ClickListener clickListener(MainActivity mainActivity);

}