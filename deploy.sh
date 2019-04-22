#!/bin/bash
set -e
set -x

ARTIFACT=lambdalala
PACKAGE=$2
TARGET=$1
DATE_TIME=$(date +%F_%H%I)
VERSION=$(cat VERSION.txt)
SHASUM=$(shasum $1 | awk '{ print $1; }')
FILE_NAME='release/'$ARTIFACT'/latest/'$ARTIFACT'_'$VERSION'_'$SHASUM'_'$DATE_TIME'.jar'

PROFILE=$3
FUNCTION=$4
# gzip $TARGET
aws s3 cp $TARGET s3://$PACKAGE/$FILE_NAME --profile=$PROFILE
# aws s3 cp $TARGET s3://$PACKAGE/$FILE_NAME --profile=$4
if [ -z "$FUNCTION" ]
then
      echo "\$var is empty"
else
      echo "\$var is NOT empty"
      ./release_lambda.sh $FUNCTION $PACKAGE $FILE_NAME $PROFILE
fi
