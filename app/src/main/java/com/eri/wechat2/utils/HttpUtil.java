package com.eri.wechat2.utils;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class HttpUtil {



    public String getStringDataOfGet(String url)
            throws ClientProtocolException, IOException, URISyntaxException {
        String result = null;
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
        HttpConnectionParams.setSoTimeout(httpParameters, 30000);
        DefaultHttpClient client = new DefaultHttpClient(httpParameters);
        HttpGet httpGet = new HttpGet(new URI(url));
        HttpResponse res = client.execute(httpGet);
        if (res.getStatusLine().getStatusCode() == 200) {
            result = EntityUtils.toString(res.getEntity(), "UTF-8");
            return result;
        } else {

        }
        return null;
    }

    public String getStringDataOfPost(String url, Map<String, String> valuePair
    ) throws ClientProtocolException, IOException,
            URISyntaxException {
        HttpPost httpPost = new HttpPost(new URI(url));
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        defaultHttpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
        defaultHttpClient.getParams().setParameter(
                CoreConnectionPNames.SO_TIMEOUT, 30000);
        if (valuePair == null)
            valuePair = new HashMap<String, String>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : valuePair.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
        int status = httpResponse.getStatusLine().getStatusCode();
        if (status == 200) { // success
            return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        } else {
            Log.v("wrong", "server exception");
        }
        return null;

    }

    public String InputStreamTOString(InputStream in) throws IOException {
        if (in == null)
            return null;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return new String(outStream.toByteArray(), "UTF-8");

    }

    private XmlPullParser getXmlPullParser(InputStream inputStream) throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parse = factory.newPullParser();
        parse.setInput(inputStream, "utf-8");
        return parse;
    }

    public InputStream getUrlDataOfPost(String url, Map<String, String> valuePair)
            throws ClientProtocolException, IOException, URISyntaxException {
        HttpPost httpPost = new HttpPost(new URI(url));
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
        if (valuePair == null)
            valuePair = new HashMap<String, String>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : valuePair.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
        int status = httpResponse.getStatusLine().getStatusCode();
        if (status == 200) { // success
            return httpResponse.getEntity().getContent();
        }
        return null;
    }

    public InputStream getUrlDataOfGet(String url)
            throws ClientProtocolException, IOException, URISyntaxException {
        InputStream is = null;
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
        HttpConnectionParams.setSoTimeout(httpParameters, 30000);
        DefaultHttpClient client = new DefaultHttpClient(httpParameters);
        HttpGet httpGet = new HttpGet(new URI(url));
        HttpResponse res = client.execute(httpGet);
        if (res.getStatusLine().getStatusCode() == 200) {
            is = res.getEntity().getContent();
        }
        return is;
    }


}
