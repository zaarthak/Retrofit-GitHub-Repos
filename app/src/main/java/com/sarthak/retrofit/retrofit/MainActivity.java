package com.sarthak.retrofit.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements Callback<List<GithubRepo>> {

    private ListView reposList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reposList = findViewById(R.id.github_repos_list);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        GithubClient client = retrofit.create(GithubClient.class);
        Call<List<GithubRepo>> call = client.getGithubRepos("zaarthak");

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {

        List<GithubRepo> repos = response.body();

        reposList.setAdapter(new GithubRepoAdapter(MainActivity.this, repos));
    }

    @Override
    public void onFailure(Call<List<GithubRepo>> call, Throwable t) {

        Toast.makeText(this, "Error retrieving repositories.", Toast.LENGTH_SHORT).show();
    }
}
