#!/bin/bash

set -e
set -x


LAMBDA_FUNCTION=$1
PACKAGE=$2
FILE_NAME=$3
PROFILE=$4

aws lambda update-function-code \
  --function-name $LAMBDA_FUNCTION \
  --region eu-west-1 \
  --s3-bucket $PACKAGE \
  --s3-key $FILE_NAME \
  --profile=$PROFILE
