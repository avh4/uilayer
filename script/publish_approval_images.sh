#!/bin/bash

echo "====== Publishing approval images ======"
for image in uilayer-swing/*.png; do
	if [ -e "$image" ]; then
		echo "===> $image" >&2
		script/imgur.sh $image
	fi
done > tmp_grab_approval_images.sh
echo "====== Script to grab approval images ======"
cat tmp_grab_approval_images.sh
rm tmp_grab_approval_images.sh
