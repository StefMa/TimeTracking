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
