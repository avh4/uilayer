#!/bin/bash

mvn -P skip-font-rendering test
EXIT="$?"

script/publish_approval_images.sh

exit $EXIT
