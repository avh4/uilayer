#!/bin/bash

adb shell "ls /sdcard/*_test__*.png" | tr -d '\r' | while read; do
	echo -n "$REPLY: "
	adb pull "$REPLY"
	adb shell "rm '$REPLY'"
done

