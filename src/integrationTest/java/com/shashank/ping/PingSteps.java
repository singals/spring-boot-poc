package com.shashank.ping;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hamcrest.core.Is;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PingSteps {

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private CloseableHttpResponse httpResponse;

    @When("^I invoke the \"([^\"]*)\" api$")
    public void iInvokeTheApi(String api) throws Throwable {
        HttpGet httpGet = new HttpGet("http://localhost:8090/" + api);
        httpResponse = httpClient.execute(httpGet);
    }

    @Then("^I shall get \"([^\"]*)\" as response$")
    public void iShallGetAsResponse(String expectedResponse) throws Throwable {
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.OK.value()));
        assertThat(EntityUtils.toString(httpResponse.getEntity(), "UTF-8"), is("pong"));
    }
}
