package com.stephane_droz.test;

import com.stephane_droz.RestClient;
import com.stephane_droz.Utils;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URI;

import static com.stephane_droz.Utils.prettyPrint;
import static com.stephane_droz.Utils.toJson;
import static org.junit.Assert.*;


public class SimpleRestCallTest {

    static RestClient client = new RestClient();
    private static String token;

    @BeforeClass
    public static void beforeClass() throws Exception {
//        String authEntity = """
//                {
//                    "username":"admin",
//                    "password":"secret"
//                }
//                """;
//        Response response = client.getWebTarget("https://jsonplaceholder.typicode.com/auth").request().post(Entity.json(toJson(authEntity)));
//        token = response.getHeaderString(HttpHeaders.AUTHORIZATION);
    }

    @Test
    public void getOneItem() {
        WebTarget target = client.getWebTarget("https://jsonplaceholder.typicode.com/posts/1");
        JsonObject json = target.request().get(JsonObject.class);

        assertEquals(1, json.getInt("id"));
        System.out.println(prettyPrint(json));
    }

    @Test
    public void getMultipleItems() {
        WebTarget target = client.getWebTarget("http://jsonplaceholder.typicode.com/posts");
        JsonArray json = target.request().get(JsonArray.class);

        assertEquals(100, json.size());
        System.out.println(json);
    }

    @Test
    public void postAnItem() {
        WebTarget target = client.getWebTarget("https://jsonplaceholder.typicode.com/posts");
        Response response = target.request().post(Entity.json(Utils.toJson("""
                {
                    "title": "hello",
                    "body": "Moni",
                    "userId":"S"
                }
                """)));
        URI location = response.getLocation();
        assertNotNull(location);
        String url = location.toString();
        assertTrue(url.startsWith("http://jsonplaceholder.typicode.com/posts/1"));
    }

    @Test
    @Ignore
    public void getOnSecuredEndpoint() {
        WebTarget target = client.getWebTarget(token, "http://jsonplaceholder.typicode.com/posts");
        JsonArray json = target.request().get(JsonArray.class);

        assertEquals(100, json.size());
        System.out.println(json);
    }
}
