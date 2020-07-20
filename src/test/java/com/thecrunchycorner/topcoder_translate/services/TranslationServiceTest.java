package com.thecrunchycorner.topcoder_translate.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = {"com.thecrunchycorner.topcoder_translate.*", "org.apache" +
        ".http.*"})
public class TranslationServiceTest {
    private static final String URL = "http://no.com";

    private HttpClientBuilder httpClientBuilder = mock(HttpClientBuilder.class);
    private CloseableHttpClient client = spy(mock(CloseableHttpClient.class));
    private RequestBuilder requestBuilder = mock(RequestBuilder.class);
    private HttpUriRequest httpRequest = mock(HttpUriRequest.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setupHttpStuff() throws Exception {
        mockStatic(HttpClientBuilder.class);
        when(HttpClientBuilder.create()).thenReturn(httpClientBuilder);
        when(httpClientBuilder.build()).thenReturn(client);

        mockStatic(RequestBuilder.class);
        when(RequestBuilder.post()).thenReturn(requestBuilder);
        when(requestBuilder.setUri(anyString())).thenReturn(requestBuilder);
        when(requestBuilder.setHeader(eq(HttpHeaders.CONTENT_TYPE), anyString())).thenReturn(requestBuilder);
        when(requestBuilder.setHeader(eq(HttpHeaders.USER_AGENT), anyString())).thenReturn(requestBuilder);
        when(requestBuilder.setEntity(any(HttpEntity.class))).thenReturn(requestBuilder);
        when(requestBuilder.build()).thenReturn(httpRequest);

        when(client.execute(any(HttpUriRequest.class), any(ResponseHandler.class))).thenReturn(getTranslation());
    }

    @Test
    public void shouldCreateValidRequestWithParameters() throws Exception {
        TranslationService.translate(URL, "hello", "en", "jp");

        UrlEncodedFormEntity expectedEntity;
        expectedEntity = new UrlEncodedFormEntity(prepParams());

        ArgumentCaptor<UrlEncodedFormEntity> argumentCaptor = ArgumentCaptor.forClass(UrlEncodedFormEntity.class);
        verify(requestBuilder).setEntity(argumentCaptor.capture());
        UrlEncodedFormEntity actualEntity = argumentCaptor.getValue();

        Assert.assertEquals(expectedEntity.toString(), actualEntity.toString());
    }

    @Test
    public void shouldParseTranslationReturnedFromService() throws Exception {
        String expectedTranslation = getParsedTranslation();
        String actualTranslation = TranslationService.translate(URL, "hello", "en", "jp");

        Assert.assertEquals(expectedTranslation, actualTranslation);
    }

    @Test
    public void shouldThrowExceptionIfItOccursWhileCallingThirddParty() throws Exception {
        thrown.expect(IOException.class);
        when(client.execute(any(HttpUriRequest.class), any(ResponseHandler.class))).thenThrow(IOException.class);

        TranslationService.translate(URL, "hello", "en", "jp");
    }

    @Test
    public void shouldParseResponseAndReturnEntity() throws Exception {
        byte[] data = "abcdef".getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(data);
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContent()).thenReturn(stream);
        when(entity.getContentLength()).thenReturn((long)data.length);

        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);

        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        String serviceResponse =  TranslationService.prepResponseHandler().handleResponse(response);

        Assert.assertEquals(new String(data), serviceResponse);

    }

    @Test
    public void shouldParseResponseAndReturnNullWhenDataIsNull() throws Exception {
        HttpEntity entity = mock(HttpEntity.class);
        when(entity.getContent()).thenReturn(null);

        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);

        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(response.getEntity()).thenReturn(entity);
        String serviceResponse =  TranslationService.prepResponseHandler().handleResponse(response);
        Assert.assertNull(serviceResponse);
    }

    @Test
    public void shouldParseResponseAndReturnExceptionIfUnexpectedHttpStatus() throws Exception {
        thrown.expect(ClientProtocolException.class);

        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(404);

        HttpResponse response = mock(HttpResponse.class);
        when(response.getStatusLine()).thenReturn(statusLine);
        TranslationService.prepResponseHandler().handleResponse(response);
    }


    private List<NameValuePair> prepParams() {
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
        params.add(new BasicNameValuePair("sl", "en"));
        params.add(new BasicNameValuePair("tl", "jp"));
        params.add(new BasicNameValuePair("q", "hello"));

        return params;
    }

    private String getTranslation() {
        return "{\"sentences\":[{\"trans\":\"Estoy bastante entusiasmado con mi primer" +
                " desafío en Top Coder, incluso si es solo un ejercicio de entrenamiento." +
                " \"," +
                "\"orig\":\"I\\u0027m quite excited about my first challenge at Top " +
                "coder, even " +
                "if it\\u0027s just a training exercise.\",\"backend\":3," +
                "\"model_specification\":[{}]," +
                "\"translation_engine_debug_info\":[{\"model_tracking\":{\"checkpoint_md5" +
                "\":\"f16caa8d292961aa55f7ae83b3ec9001\",\"launch_doc\":\"en_es_2019q2" +
                ".md\"}}]}," +
                "{\"trans\":\"No estoy completamente seguro de por qué nunca he hecho " +
                "esto en el " +
                "pasado. \",\"orig\":\"I\\u0027m not entirely sure why I\\u0027ve never " +
                "done this" +
                " in the past.\",\"backend\":3,\"model_specification\":[{}]," +
                "\"translation_engine_debug_info\":[{\"model_tracking\":{\"checkpoint_md5" +
                "\":\"f16caa8d292961aa55f7ae83b3ec9001\",\"launch_doc\":\"en_es_2019q2" +
                ".md\"}}]}," +
                "{\"trans\":\"Demasiado divertido trabajando en mis propios proyectos, " +
                "supongo" +
                ".\",\"orig\":\"Too much fun working on my own projects I guess.\"," +
                "\"backend\":3," +
                "\"model_specification\":[{}]," +
                "\"translation_engine_debug_info\":[{\"model_tracking\":{\"checkpoint_md5" +
                "\":\"f16caa8d292961aa55f7ae83b3ec9001\",\"launch_doc\":\"en_es_2019q2" +
                ".md\"}}]}]," +
                "\"src\":\"en\",\"confidence\":1.0,\"spell\":{}," +
                "\"ld_result\":{\"srclangs\":[\"en\"],\"srclangs_confidences\":[1.0]," +
                "\"extended_srclangs\":[\"en\"]}}";
    }

    private String getParsedTranslation() {
        return "Estoy bastante entusiasmado con mi primer desafío en Top Coder, incluso si es " +
                "solo un ejercicio de entrenamiento. No estoy completamente seguro de por qué nunca he hecho esto en el pasado. Demasiado divertido trabajando en mis propios proyectos, supongo.";
    }

    private ResponseHandler<String> getResponseHandler() {
        return response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }
}
