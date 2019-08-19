package io.cucumber.skeleton;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.internal.matchers.ThrowableCauseMatcher;

import javax.sound.midi.Soundbank;
import static org.junit.Assert.*;
public class Stepdefs {
    private apiCalls call = new apiCalls();
   JSONObject jsonResponse;
   String response;


    @Given("I make a post with title {string} body {string} and userId {int}")
    public void i_make_a_post_with_title_body_and_userId(String title, String body, Integer userId) throws Throwable{
        jsonResponse = call.makePost(title,body,userId);
        assertEquals("Return ID is not 101", jsonResponse.getInt("id"),101);
    }

    @Given("I make a post with title {string} body {string} and userId ")
    public void i_make_a_post_with_title_body_and_userId(String title, String body) throws Throwable {
        assertTrue("userId is undefined", false);
    }

    @And("I make a comment for id {int}")
    public void i_make_a_comment_for_id(Integer id) throws Throwable{
        response = call.makeAComment(id);
        assertTrue("id 1 does not exist -  name: id labore ex et quam laborum,", response.contains("\"name\": \"id labore ex et quam laborum\""));
    }

    @Then("I look at a list of posts")
    public void i_look_at_a_list_of_posts()throws Throwable{
      response = call.getListOfUsers();
        assertTrue("Leanne Graham is not in the response", response.contains("Leanne Graham"));
        assertTrue("Ervin Howell is not in the response", response.contains("Ervin Howell"));
    }


}
