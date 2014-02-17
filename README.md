BrightcenterSDK-Android
=======================

In this repo you'll find the PHP-SDK for Brightcenter. In this file I'll describe how you can use the SDK.

###Download the project
First of all you need to download the project. You can either check it out with git or download the zip. The downloaded folder will actually consist 2 projects; BrightcenterSDK-Android and ActionBarSherlock.

###Use the SDK in your project
To include the Brightcenter SDK in your app you need to follow the following steps.

1. include the `BrightcenterSDK-Android` project as a module in your project.
2. include the `ActionBarSherlock` project as a module in your project.
3. In your project add an dependency on both `BrightcenterSDK-Android` and `ActionBarSherlock`
4. Add the android-support-v4.jar to your classpath. (The jar can be found in the 'actionbarsherlock/libs' folder.

You alsno need to add the following things to your AndroidManifest.xml:
```xml
<activity android:name="nl.trifork.brightcenter.androidsdk.activities.LoginActivity" android:theme="@android:style/Theme.Holo.Light"/>

<activity android:name="nl.trifork.brightcenter.androidsdk.activities.MainActivity" android:theme="@android:style/Theme.Holo.Light"/>

<activity android:name="nl.trifork.brightcenter.androidsdk.activities.PostResultActivity" android:theme="@android:style/Theme”/>

<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```
add the folowing to your '<application>' tag to use the globalvars:
```xml
android:name="nl.trifork.brightcenter.androidsdk.GlobalVars"
```

###Use the cool overlay button
if you want to use the brightcenter overlay button to start the login sequence you need to add the following code to the activity where you want to start it:

```java
public void createBrightcenterButton(RelativeLayout layout, int position, int color) {
        Button button = new Button(this);

        button.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        OverlayButton overlayButton = new OverlayButton();
        button = overlayButton.setParams(button, position, color, getResources());
        switch (position){
            case 1:
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            case 2:
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                break;
            case 3:
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            case 4:
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                break;
            default:
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                break;
        }
        layout.addView(button, params);
    }
```
in your activity onCreate() you can put to following line to activate the button:

```java
RelativeLayout layout = (RelativeLayout) findViewById([IDOFYOURLAYOUT]);
createBrightcenterButton(layout, [POSITION], [COLOR]);
```
position can be 1, 2, 3 or 4. 1 is top left, 2 is top right, 3 is bottom left and 4 is bottom right. color can be 1, 2 or 3. 1 is orange, 2 is blue and 3 is gray.

You should also create a intent that will be loaded after the login sequence, like this:
```java
Intent intentToLoad = new Intent(this, ACTIVITYTOLOADAFTERSEQUENCE.class);
GlobalVars vars = (GlobalVars) getApplication();
vars.setIntentForStudentSelected(intentToLoad);
```
If you've done this correctly, you should have a nice button in one of your screen's corners!

###If you don't want to use the brightcenter button


To start the Brightcenter SDK use the following piece of code wherever you want to start the login sequence:
```java
    GlobalVars vars = (GlobalVars) getApplication();
    vars.setIntentForStudentSelected(new Intent(this, THE_ACTIVITY_YOU_WANT_TO_START.class));
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
```
You'll be guided to the login screen where a teacher can fill in his/her credentials. When a student is picked the Intent that you gave will be started

###How to use the selected student
In your code you can use the folowing code snippet to get the id of the student:
```java
GlobalVars vars = ((GlobalVars) getApplicationContext());
String studentId = vars.getSelectedStudent().getStudentId();
```

###How to retrieve results of a student
To retrieve results of a student you can use the following code:
```java
BCConnect connector = new BCConnect();
List<BCResult> results = connector.getResultsOfStudent(String studentId, String assessmentId, String username, String password);
```
The username and password can be retrieved by using GlobalVars again:
```java
String username = vars.getUsername();
String password = vars.getPassword();
```

###How to post a result of a student
To post a result of a student the following code can be used:
```java
connector.postResultOfStudent(BCResult result, String username, String password)
```
BCResult should contain the following variables:
`score` the score of the student
`studentId` the id of the student
`assessmentId` the id of the assessment
`questionId` the id of the question
`duration` the duration in seconds
`CompletionStatus` a completionStatus enum

###Notes
-when a student logs out all variables in GlobalVars are set to `null`.

-All BCConnect tasks should be put in a asynchronous task if you use them.

-if you have problems using the sdk you can create an issue on github or with the jira issue tracker on tst-brightcenter.trifork.nl 





