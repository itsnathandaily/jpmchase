package io.cucumber.skeleton;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;
import static io.cucumber.skeleton.utils.loadURL;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class apiCalls {
    public static String response_body;

public  JSONObject makePost(String title, String bod, int userId)throws IOException {
    String json = "{\"userId\":\""+userId+"\",\"title\":\""+title+"\",\"body\":\""+bod+"\"}";

    OkHttpClient client = new OkHttpClient();
    RequestBody body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"), json);

    Request request = new Request.Builder()
            .url(loadURL() + "/posts")
            .post(body)
            .build();

    Call call = client.newCall(request);
    Response response = call.execute();
    assertThat(response.code(), equalTo(201));
    response_body = response.body().string();
    JSONObject postData = new JSONObject(response_body);

    if(!response.isSuccessful()) {
        throw new IOException("Unexpected response " + response);
    }else {
        assertTrue("body of post response is not " +bod, response_body.contains(bod));
        assertTrue("title of post response is not "+title, response_body.contains(title));
        return postData;
    }


}

public String makeAComment(int id) throws IOException{
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .get()
            .url(loadURL()+"/comments?postId="+id)
            .addHeader("Accept", "application/json")
            .addHeader("cache-control", "no-cache")
            .build();
    Call call = client.newCall(request);
    Response response = call.execute();
    assertThat(response.code(), equalTo(200));
    response_body = response.body().string();
    if(!response.isSuccessful() & response_body.length() != 0) {
        throw new IOException("Unexpected response " + response);
    }else {
        return response_body;
    }
}


    public String getListOfUsers() throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(loadURL()+"/users")
                .addHeader("Accept", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        assertThat(response.code(), equalTo(200));
        assertTrue(response.isSuccessful());
        response_body = response.body().string();
        if(!response.isSuccessful()) {
            throw new IOException("Unexpected response " + response);
        }else {

            return response_body;
        }

    }




}
