package com.example.hasnaa.mobilestudiotask.NewPackage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Hasnaa on 30-10-2017.
 */
public class DataTask {
    public static final int JSON_DATA_FORM =100;
    private URL url;

    public DataTask (URL url){
        this.url=url;
    }

    public <T> T getDataParsed ( Class<T> classType ,int type) throws IOException {
        String response = getResponseFromHttpUrl(url);
        if(type==JSON_DATA_FORM)
            return new Parser().parseJson(response ,classType);
        else
            return null; //nt implemented yet
    }


    private static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
