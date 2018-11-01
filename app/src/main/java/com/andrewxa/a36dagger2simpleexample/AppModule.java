package com.andrewxa.a36dagger2simpleexample;

import android.app.Activity;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

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
}