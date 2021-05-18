package com.stephane_droz.system.test;

import com.stephane_droz.system.RestClient;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Test;


public class SimpleRestCallTest {

    RestClient client = new RestClient();

    @Test
    public void getOnTwitterEndpoint() {
        WebTarget target = client.getWebTarget("https://jsonplaceholder.typicode.com/posts/1");
        Response response = target.request().get();

        System.out.println(response);

        Assert.assertEquals(200, response.getStatus());

        Object entity = response.getEntity();

        System.out.println(entity);
    }
}
