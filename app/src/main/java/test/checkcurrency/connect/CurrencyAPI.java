package test.checkcurrency.connect;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import test.checkcurrency.data.FullCurrency;

public interface CurrencyAPI {
    @GET("/stocks.json")
    Call<FullCurrency> getCurrencys();


}
