package com.example.android.fragments.rest;

import android.util.Log;
import com.example.android.fragments.enums.CompletionStatus;
import com.example.android.fragments.model.BCGroup;
import com.example.android.fragments.model.BCResult;
import com.example.android.fragments.model.BCStudent;
import com.example.android.fragments.model.BCUser;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Rick Slot
 */
public class BCConnect {

    //String URL = "http://10.0.2.2:8080/api/";
    String URL = "https://tst-brightcenter.trifork.nl/api/";

    public BCUser getUser(String username, String password) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet(URL + "userDetails");
        get.setHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(username, password),
                "UTF-8", false));
        Log.d("BRIGHTCENTER", "calling...");
        HttpResponse httpResponse = httpClient.execute(get);
        Log.d("BRIGHTCENTER", "call is made1!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
        StringBuilder builder = new StringBuilder();
        for (String line = null; (line = reader.readLine()) != null; ) {
            builder.append(line).append("\n");
        }
        JSONObject object = (JSONObject) new JSONTokener(builder.toString()).nextValue();

        BCUser user = new BCUser();
        user.setId(object.getString("id"));
        user.setFirstName(object.getString("firstName"));
        user.setLastName(object.getString("lastName"));
        return user;
    }





    public List<BCGroup> login(String username, String password) throws IOException, JSONException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet(URL + "groups");
        get.setHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(username, password),
                "UTF-8", false));
        Log.d("BRIGHTCENTER", "calling...");
        HttpResponse httpResponse = httpClient.execute(get);
        Log.d("BRIGHTCENTER", "call is made1!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
        StringBuilder builder = new StringBuilder();
        for (String line = null; (line = reader.readLine()) != null; ) {
            builder.append(line).append("\n");
        }
        JSONTokener tokener = new JSONTokener(builder.toString());
        JSONArray finalResult = new JSONArray(tokener);

        List<BCGroup> groups = new ArrayList<BCGroup>();
        for (int i = 0; i < finalResult.length(); i++) {

            BCGroup group = new BCGroup();
            JSONObject groupJson = finalResult.getJSONObject(i);
            group.setGroupName(groupJson.getString("name"));
            group.setGroupId(groupJson.getString("id"));

            JSONArray students = groupJson.getJSONArray("students");
            List<BCStudent> studentList = new ArrayList<BCStudent>();
            for (int j = 0; j < students.length(); j++) {
                BCStudent student = new BCStudent();
                JSONObject studentJson = students.getJSONObject(j);
                student.setFirstName(studentJson.getString("firstName"));
                student.setLastName(studentJson.getString("lastName"));
                student.setStudentId(studentJson.getString("personId"));
                studentList.add(student);
            }
            group.setBCStudents(studentList);

            groups.add(group);

        }
        return groups;
    }

    public List<BCResult> getResultsOfStudent(String studentId, String assessmentId, String username, String password) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();

        String getString = "assessment/" + assessmentId + "/students/" + studentId + "/assessmentItemResult";
        HttpGet get = new HttpGet(URL + getString);
        get.setHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(username, password),
                "UTF-8", false));
        HttpResponse httpResponse = httpClient.execute(get);

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
        StringBuilder builder = new StringBuilder();
        for (String line = null; (line = reader.readLine()) != null; ) {
            builder.append(line).append("\n");
        }
        JSONTokener tokener = new JSONTokener(builder.toString());
        JSONArray finalResult = new JSONArray(tokener);

        List<BCResult> results = new ArrayList<BCResult>();
        for (int i = 0; i < finalResult.length(); i++) {
            BCResult result = new BCResult();
            JSONObject resultJson = finalResult.getJSONObject(i);


            result.setStudentId(studentId);
            result.setAssessmentId(assessmentId);
            result.setScore(resultJson.getDouble("score"));
            result.setQuestionId(resultJson.getString("questionId"));
            result.setDuration(resultJson.getInt("duration"));
            result.setAttempts(resultJson.getInt("attempts"));
            result.setDate(new Date(resultJson.getLong("date")));

            if (resultJson.getString("completionStatus").equals("COMPLETED")) {
                result.setCompletionStatus(CompletionStatus.COMPLETED);
            } else {
                result.setCompletionStatus(CompletionStatus.INCOMPLETE);
            }

            results.add(result);
        }
        return results;
    }

    public void postResultOfStudent(BCResult result, String username, String password) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        String studentId = result.getStudentId();
        String assessmentId = result.getAssessmentId();
        String questionId = result.getQuestionId();
        Double score = result.getScore();
        int duration = result.getDuration();
        CompletionStatus completionStatus = result.getCompletionStatus();


        String postString = "assessment/" + assessmentId + "/student/" + studentId + "/assessmentItemResult/" + questionId;
        HttpPut post = new HttpPut(URL + postString);
        post.setHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(username, password),
                "UTF-8", false));
        post.addHeader("content-type", "application/json");

        JSONObject resultObject = new JSONObject();
        resultObject.put("score", score);
        resultObject.put("duration", duration);
        resultObject.put("completionStatus", completionStatus);

        post.setEntity(new StringEntity(resultObject.toString()));

        HttpResponse response = httpClient.execute(post);

        Log.d("BRIGHTCENTER", response.toString());
    }

}
