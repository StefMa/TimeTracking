#!/bin/bash

echo -n "Set project id: "
read project

sed -i '' "s/\[PROJECTID\]/$project/g" restapi/build.gradle

echo -n "Set user token: "
read token

sed -i '' "s/\[USERTOKEN\]/$token/g" app/build.gradle
