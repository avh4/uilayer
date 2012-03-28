#!/bin/bash

rsync -vur uilayer-swing/src/test/java/net/avh4/framework/uilayer/scene/testsuite/ uilayer-android/tests/src/net/avh4/framework/uilayer/scene/testsuite/
rsync -vur uilayer-android/tests/src/net/avh4/framework/uilayer/scene/testsuite/ uilayer-swing/src/test/java/net/avh4/framework/uilayer/scene/testsuite/
