#!/bin/bash

# Get the short hash passed from the workflow or fallback to git
GIT_HASH=$(git rev-parse --short HEAD)
echo "Starting upload process for commit: $GIT_HASH"

# Navigate to the versions directory
cd versions || exit 1

for dir in */; do
    # Remove trailing slash for the name
    version_name=${dir%/}

    # Skip the dependencies folder
    if [ "$version_name" == "dependencies" ]; then
        echo "Skipping dependencies directory..."
        continue
    fi

    echo "Processing $version_name..."

    # Define the path where Gradle puts the jars
    LIB_PATH="$version_name/build/libs"

    if [ -d "$LIB_PATH" ]; then
        # Create a temporary staging directory to get the naming right
        # This ensures the zip file name matches your request
        STAGING_DIR="${GIT_HASH}+${version_name}"
        mkdir -p "$STAGING_DIR"

        # Copy all jars (libs and sources) into the staging area
        cp "$LIB_PATH"/*.jar "$STAGING_DIR/"

        # Upload the entire folder as a single zip artifact
        # The artifact name will be "hash+1.20.1-fabric"
        echo "Uploading artifact: $STAGING_DIR"
        gh run upload-artifact --name "$STAGING_DIR" --path "$STAGING_DIR"

        # Clean up staging dir
        rm -rf "$STAGING_DIR"
    else
        echo "No build/libs found for $version_name, skipping..."
    fi
done
