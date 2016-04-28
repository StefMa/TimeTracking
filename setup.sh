#!/bin/bash

echo -n "Set project id: "
read project
sed -i '' "s/\[PROJECTID\]/$project/g" restapi/build.gradle

echo -n "Set user token: "
read token
sed -i '' "s/\[USERTOKEN\]/$token/g" app/build.gradle

echo -n "Set basic auth username: "
read authUsername
sed -i '' "s/\[USERNAME\]/$authUsername/g" restapi/build.gradle

echo -n "Set basic auth password: "
read authPassword
sed -i '' "s/\[PASSWORD\]/$authPassword/g" restapi/build.gradle
