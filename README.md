# TimeTracking
This is an Android Client for time tracking.

## How to Build
### Server side
First you need to setup the [server side](https://github.com/StefMa/TimeTrackingServer) for this project properly.

### Configure the `build.gradle` files
When do you have your App Engine project id and made a account for this app you can change the `gradle` files.
The [restapi gradle file](restapi/build.gradle) needs your project id.
You can find a `buildConfigField` which declares the api url. Replace `[PROJECTID]` with your App Engine id.

Another [`build.gradle`](app/build.gradle) file is located in the app module.
There you can declare a `resValue` named `USER_TOKEN`. Here you need to replace the `[USERTOKEN]` with your created account user token.

You can also use the `setup.sh` script in the root path to make this happen.

That's all. Now run the app and track your working time!

## Crashlytics and Beta Distribution
To distribute the App for Beta-Testing we have implement [Crashlytics from Farbic](https://docs.fabric.io/android/fabric/overview.html).
If you aren't similar with Crashlytics read [this](https://fabric.io/kits/android/crashlytics) first. It's a crash logging tool for many platforms. However. You don't need a Fabric Account to run this App.

On default **Crashlytics is disabled**. Just run this App as any other Android App.

If you want to distribute this App with Crashlytics you have to enabled it first.
Search in the `build.gradle` for:
```
def crashlyticsEnabled = false
```
Set it to `true`.
Now you have to register theses App with the Fabric backend. Make a `fabric.properties` inside the [`app/`](app/) directory.
Setup the file like this:
```
apiSecret=YOUR_API_SECRET
apiKey=YOUR_API_KEY
```
The `secret` and `key` can be found in your Fabric backend.
After your first successfully start you have enabled your App with Crashlytics.

If you want to distribute your App with Crashlytics Beta you have to run the following task:
```
./gradlew assembleDevDebug crashlyticsUploadDistributionDevDebug
```
You can change the distribution parameters inside the `build.grade`:
```
ext.betaDistributionEmails = ""               // E-Mails of Beta-Testers
ext.betaDistributionNotifications = true      // Send Notification E-Mail
ext.betaDistributionReleaseNotes = ""         // Release Notes
```
