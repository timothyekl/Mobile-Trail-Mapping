#!/bin/bash
# Android Build Script
EXPECTED_ARGS=1
if [ ! -f "password.txt" ]; then
    echo "Missing: password.txt";
    exit;
fi
read -r PASSWORD < "password.txt"
if [ $# -ne $EXPECTED_ARGS ]; then
  echo "Usage: ./build_android.sh VERSION_NUM";
  exit;
fi

if [ ! -f "brousalis.keystore" ]; then
    echo "Missing: brousalis.keystore";
    exit;
fi

if [ -d $1 ]; then
    echo "Found ${1} Directory...";
    if [ -f "${1}/mtmBETA_unsigned.${1}.apk" ]; then
        echo "Found ${1}/mtmBETA_unsigned.${1}.apk"
        cp "${1}/mtmBETA_unsigned.${1}.apk" "${1}/mtmBETA_unsigned.apk"
        
        echo "Signing..."
        jarsigner -storepass $PASSWORD -keystore brousalis.keystore "${1}/mtmBETA_unsigned.apk" brousalis
        mv "${1}/mtmBETA_unsigned.apk" "${1}/mtmBETA_signed.apk"
        if [ -f "${1}/mtmBETA.${1}.apk" ]; then
            echo "Removing old build of same version from local machine..."
            rm "${1}/mtmBETA.${1}.apk"
        fi
        echo "Compressing..."
        /Applications/android-sdk-mac_86/tools/zipalign 4 "${1}/mtmBETA_signed.apk" "${1}/mtmBETA.${1}.apk"
        rm "${1}/mtmBETA_signed.apk"
        echo "Build Completed."
        if [ -d "/Volumes/eric-stokes.com" ]; then
            if [ -f "/Volumes/eric-stokes.com/public_html/mtmbeta/downloads/mtmBETA.${1}.apk" ]; then
                echo "Removing old build of same version from server..."
                rm "/Volumes/eric-stokes.com/public_html/mtmbeta/downloads/mtmBETA.${1}.apk"
            fi
            echo "Uploading..."
            cp "${1}/mtmBETA.${1}.apk" "/Volumes/eric-stokes.com/public_html/mtmbeta/downloads/mtmBETA.${1}.apk"
            echo "Upload COMPLETE."
        else
            echo "Could NOT upload."
        fi
        
    else
        echo "Missing ${1}/mtmBETA_unsigned.${1}.apk";
        exit;
    fi
else
    echo "Sorry, version not found...";
    exit;
fi