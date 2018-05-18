package test.checkcurrency.connect;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectWithRetrofit {

    private Retrofit client;
    private CurrencyAPI service;


    public ConnectWithRetrofit(String url) {
        this.client = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = client.create(CurrencyAPI.class);
    }

    public Retrofit getClient() {
        return client;
    }

    public CurrencyAPI getService() {
        return service;
    }
}
