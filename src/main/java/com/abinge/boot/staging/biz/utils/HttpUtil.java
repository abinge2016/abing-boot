//package com.abinge.boot.staging.biz.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import com.guahao.opensaas.gateway.biz.handler.ObjectResponseHandler;
//import org.apache.commons.collections4.MapUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class HttpUtil {
//    public static String sendPostJSON(CloseableHttpClient httpClient, String url, Map<String, String> headers, String bodyStr) throws IOException {
//        HttpPost httpPost = new HttpPost(url);
//        if (!MapUtils.isEmpty(headers)) {
//            headers.forEach(httpPost::addHeader);
//        }
//        StringEntity stringEntity = new StringEntity(bodyStr, Charset.forName("UTF-8"));
//        stringEntity.setContentType(ContentType.APPLICATION_JSON.getMimeType());
//        httpPost.setEntity(stringEntity);
//        ResponseHandler<JSONObject> responseHandler = new ObjectResponseHandler();
//        JSONObject result = httpClient.execute(httpPost, responseHandler);
//        return result.toJSONString();
//    }
//
//    public static String sendFormEntity(CloseableHttpClient httpClient, String url, Map<String, String> headers, Map<String, String> formParameters) throws IOException {
//        HttpPost httpPost = new HttpPost(url);
//        if (!MapUtils.isEmpty(headers)) {
//            headers.forEach(httpPost::addHeader);
//        }
//        List<NameValuePair> nameValuePairList = new ArrayList<>();
//        formParameters.forEach((k, v) -> nameValuePairList.add(new BasicNameValuePair(k, v)));
//        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, Charset.forName("UTF-8")));
//        ResponseHandler<JSONObject> responseHandler = new ObjectResponseHandler();
//        JSONObject result = httpClient.execute(httpPost, responseHandler);
//        return result.toJSONString();
//    }
//
//    public static String sendGet(CloseableHttpClient httpClient, String url, Map<String, String> headers, Map<String, String> pathParameters, MultiValueMap<String, String> queryParameters) throws IOException {
//        String getUrl = buildUrlParameters(url, pathParameters, queryParameters);
//        HttpGet httpGet = new HttpGet(getUrl);
//        if (!MapUtils.isEmpty(headers)) {
//            headers.forEach(httpGet::addHeader);
//        }
//        ResponseHandler<JSONObject> responseHandler = new ObjectResponseHandler();
//        JSONObject result = httpClient.execute(httpGet, responseHandler);
//        return result.toJSONString();
//    }
//
//    public static String uploadKanoImage(CloseableHttpClient httpClient, String url,
//                                         Map<String, String> headers,
//                                         String originFileName,
//                                         String bizCode,
//                                         File file) throws Exception {
//        HttpPost httpPost = new HttpPost(url);
//        if (!MapUtils.isEmpty(headers)) {
//            headers.forEach(httpPost::addHeader);
//        }
//        HttpEntity reqEntity = MultipartEntityBuilder.create()
//                .addPart("file", new FileBody(file))
//                .addPart("orginFileName", new StringBody(originFileName,ContentType.MULTIPART_FORM_DATA))
//                .addPart("bizCode", new StringBody(bizCode,ContentType.MULTIPART_FORM_DATA))
//                .build();
//        httpPost.setEntity(reqEntity);
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        HttpEntity resEntity = null;
//        try {
//            resEntity = response.getEntity();
//            if (resEntity != null) {
//                return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
//            }
//        } catch (Exception e) {
//            try {
//                EntityUtils.consume(resEntity);
//            } catch (Exception io) {
//                throw io;
//            }
//        }
//        return null;
//    }
//
//    private static String buildUrlParameters(String url, Map<String, String> pathParameters,
//                                             MultiValueMap<String, String> queryParameters) throws IOException {
//        if (MapUtils.isEmpty(pathParameters)) {
//            pathParameters = new HashMap<>();
//        }
//        if (MapUtils.isEmpty(queryParameters)) {
//            queryParameters = new LinkedMultiValueMap<>();
//        }
//        return UriComponentsBuilder.fromHttpUrl(url).queryParams(queryParameters).buildAndExpand(pathParameters)
//                .encode(Charset.forName("UTF-8").name()).toUriString();
//    }
//}
