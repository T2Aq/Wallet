package com.t2aq.wallet.data.remote;


import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient2 {

  private static Retrofit sRetrofit = null;
  private static APIInterface sApi;
  private static String sBASE_URL = "https://nightly-alpha.carrene.com";

  public interface CallBack {

    void internetConnectionError() throws IOException;

  }


  private static Retrofit getClient() {

    if (sRetrofit == null) {

      sRetrofit = new Retrofit.Builder().baseUrl(sBASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return sRetrofit;
  }


  public static APIInterface getService() {

    if (sApi == null) {
      sApi = getClient().create(APIInterface.class);
    }
    return sApi;
  }

  public static APIInterface setService(Retrofit retrofit) {

    if (sApi == null) {
      sApi = retrofit.create(APIInterface.class);
    }
    return sApi;
  }


}
