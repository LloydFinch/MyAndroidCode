package com.example.android_day0626_homework.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherHttpUtil
{
    public static String getJsonString(String path, String encode)
            throws MalformedURLException, IOException
    {
        HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(100000);
        connection.setDoInput(true);
        if (connection.getResponseCode() == 200)
        {
            return changeString(connection.getInputStream(), encode);
        }
        return null;
    }

    private static String changeString(InputStream inputStream, String encode)
            throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(data)) != -1)
        {
            baos.write(data, 0, length);
        }
        String string = new String(baos.toByteArray(), encode);
        string = string.substring(17, string.length() - 1);
        return string;
    }
}
