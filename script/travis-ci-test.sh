#!/bin/bash

mvn -P skip-android test
EXIT="$?"

script/publish_approval_images.sh

exit $EXIT
