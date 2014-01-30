BrightcenterSDK-Android
=======================

In this repo you'll find the PHP-SDK for Brightcenter. In this file I'll describe how you can use the SDK.

###Download the project
First of all you need to download the project. You can either check it out with git or download the zip. The downloaded folder will actually consist of 2 projects; BrightcenterSDK-Android and ActionBarSherlock.

###Use the SDK in your project
To include the Brightcenter SDK in your app you need to follow the following steps.

1. include the BrightcenterSDK-Android project as a module in your project.
2. include the ActionBarSherlock project as a module in your project.
3. In your project add an dependency on both BrightcenterSDK-Android and ActionBarSherlock
4. Add the android-support-v4.jar to your classpath. (The jar can be found in the 'actionbarsherlock/libs' folder.

To start the Brightcenter SDK use the following piece of code wherever you want to start the login sequence:
```java
Intent intent = new Intent(this, LoginActivity.class);
startActivity(intent);
```
You be guided to the login screen where a teacher can fill in his/her credentials.
