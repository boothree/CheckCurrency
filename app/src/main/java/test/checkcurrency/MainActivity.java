package test.checkcurrency;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.checkcurrency.connect.ConnectWithRetrofit;
import test.checkcurrency.data.FullCurrency;
import test.checkcurrency.data.Stock;


public class MainActivity extends AppCompatActivity {
    private String url = "http://phisix-api3.appspot.com";
    private ListView lv;
    private Parcelable state; //save scroll position listView

    private ConnectWithRetrofit connectWithRetrofit;

    ArrayList<HashMap<String, String>> currencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.list);
        connectWithRetrofit = new ConnectWithRetrofit(url);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                state = lv.onSaveInstanceState();
                callServer();
            }
        }, 0, 15000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh) {
            callServer();
            return true;
        }

        return false;
    }

    public void callServer() {
        Call<FullCurrency> call = connectWithRetrofit.getService().getCurrencys();
        call.enqueue(new Callback<FullCurrency>() {
            @Override
            public void onResponse(Call<FullCurrency> call, Response<FullCurrency> response) {
                if (response.isSuccessful()) {
                    FullCurrency fullCurrency = response.body();
                    Stock[] stocks = fullCurrency.getStock();
                    for (Stock st : stocks) {
                        HashMap<String, String> currency = new HashMap<>();
                        currency.put("name", st.getName());
                        currency.put("amount", st.getPrice().getAmount());
                        currency.put("volume", st.getVolume());

                        currencyList.add(currency);
                    }
                    Log.e("retro = ", "ok");
                    fillListView();
                } else {
                    Log.e("retro = ", "bad response");
                }
            }

            @Override
            public void onFailure(Call<FullCurrency> call, Throwable t) {
                Log.e("retro = ", "fail");
            }
        });
    }

    public void fillListView() {
        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, currencyList,
                R.layout.list_item, new String[]{"name", "amount",
                "volume"}, new int[]{R.id.name,
                R.id.amount, R.id.volume});

        lv.setAdapter(adapter);
        lv.onRestoreInstanceState(state);
    }

}
