package com.thecrunchycorner.topcoder_translate.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecrunchycorner.topcoder_translate.services.response.Sentences;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TranslationService {
    public static String translate(String endpoint,
                                   String text,
                                   String sourceLang,
                                   String targetLang) throws IOException {

        CloseableHttpClient httpclient = HttpClients
                .custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();

        List<NameValuePair> params = prepParams();
        params.add(new BasicNameValuePair("sl", sourceLang));
        params.add(new BasicNameValuePair("tl", targetLang));
        params.add(new BasicNameValuePair("q", text));

        String responseBody = "";

        try {
            HttpUriRequest request = prepRequest(endpoint, params);
            ResponseHandler<String> responseHandler = prepResponseHandler();

            responseBody = httpclient.execute(request, responseHandler);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return parseTranslation(responseBody);
    }


    private static String parseTranslation(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Sentences sentences = mapper.readValue(response, Sentences.class);
        StringBuilder builder = new StringBuilder();

        sentences.getTranslations().forEach((translationText -> {
            builder.append(translationText.getTranslation());
        }));

        String translation = builder.toString();

        return translation;
    }

    private static List<NameValuePair> prepParams() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client", "at"));
        params.add(new BasicNameValuePair("dt", "t"));
        params.add(new BasicNameValuePair("dt", "ld"));
        params.add(new BasicNameValuePair("dt", "qca"));
        params.add(new BasicNameValuePair("dt", "rm"));
        params.add(new BasicNameValuePair("dt", "bd"));
        params.add(new BasicNameValuePair("dj", "1"));
        params.add(new BasicNameValuePair("hl", "%25s"));
        params.add(new BasicNameValuePair("ie", "UTF-8"));
        params.add(new BasicNameValuePair("oe", "UTF-8"));
        params.add(new BasicNameValuePair("inputm", "2"));
        params.add(new BasicNameValuePair("otf", "2"));
        params.add(new BasicNameValuePair("iid", "1dd3b944-fa62-4b55-b330-74909a99969e"));
        return params;
    }

    static ResponseHandler<String> prepResponseHandler() {
        return response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK || status == HttpStatus.SC_NOT_MODIFIED) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }

    private static HttpUriRequest prepRequest(String endpoint, List<NameValuePair> params) throws UnsupportedEncodingException {
        UrlEncodedFormEntity paparamEntity;
        paparamEntity = new UrlEncodedFormEntity(params);

        return RequestBuilder.post()
                .setUri(endpoint)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .setHeader(HttpHeaders.USER_AGENT, "AndroidTranslate/5.3.0.RC02" +
                        ".130475354-53000263 5.1 phone TRANSLATE_OPM5_TEST_1")
                .setEntity(paparamEntity).build();
    }

}
