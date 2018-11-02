package com.andrewxa.a36dagger2simpleexample;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.andrewxa.a36dagger2simpleexample.RetrofitDependency.RetrofitDependency;
import com.andrewxa.a36dagger2simpleexample.adapter.RecyclerViewAdapter;
import com.andrewxa.a36dagger2simpleexample.pojo.StarWars;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public final class MainActivity extends Activity implements MainContract.MainView, RecyclerViewAdapter.ClickListener {

    private TextView textView;
    private ProgressBar progressBar;
    RecyclerView recyclerView;
    @Inject
    MainContract.Presenter presenter;

    @Inject
    AppDependency appDependency; // same object from App

    @Inject
    ActivityDependency activityDependency;

    @Inject
    RetrofitDependency retrofitDependency;

    @Inject
    public MainActivity() {
    }

    @Inject
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
//        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        activityDependency.helloMethod();
        APIInterface apiInterface = retrofitDependency.getApiInterface();

        apiInterface.getPeople("json").enqueue(new Callback<StarWars>() {
            @Override
            public void onResponse(Call<StarWars> call, Response<StarWars> response) {
//                populateRecyclerView(response.body().results);
                System.out.println(response.body());
                recyclerViewAdapter.setData(response.body().results);
            }

            @Override
            public void onFailure(Call<StarWars> call, Throwable t) {

            }
        });

        textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        presenter = new MainPresenterImpl(this, new GetQuoteInteractorImpl());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonClick();
            }
        });
    }

    @Override
    public void launchIntent(String filmName) {
        Toast.makeText(this, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(GONE);
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setQuote(String string) {
        textView.setText(string);
    }

}