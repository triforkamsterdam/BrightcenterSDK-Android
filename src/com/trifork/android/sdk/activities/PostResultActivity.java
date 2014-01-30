package com.trifork.android.sdk.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.trifork.android.sdk.GlobalVars;
import com.trifork.android.sdk.R;
import com.trifork.android.sdk.enums.CompletionStatus;
import com.trifork.android.sdk.model.BCResult;
import com.trifork.android.sdk.rest.BCConnect;

/**
 * @author Rick Slot
 */
public class PostResultActivity extends Activity {

    BCConnect connect = null;

    GlobalVars vars;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_result);
        connect = new BCConnect();
        Button button = (Button) findViewById(R.id.bPost);
        vars = ((GlobalVars) getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BCResult result = new BCResult();
                result.setStudentId(vars.getSelectedStudent().getStudentId());
                result.setAssessmentId("123-456-789");
                result.setCompletionStatus(CompletionStatus.COMPLETED);
                result.setDuration(10);
                result.setQuestionId("u");
                result.setScore(5.0);

                new PostTask(result).execute();
            }
        });
    }





    private class PostTask extends AsyncTask<String, String, String> {

        BCResult result;

        public PostTask(BCResult result){
            this.result = result;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            BCConnect connector = new BCConnect();
            try {
                connector.postResultOfStudent(result, vars.getUsername(), vars.getPassword());
                connector.getResultsOfStudent("bf267990-1626-4469-9f9b-bc3efd8704b4", "123-456-789", vars.getUsername(), vars.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String nop){
            super.onPostExecute(nop);
        }
    }
}