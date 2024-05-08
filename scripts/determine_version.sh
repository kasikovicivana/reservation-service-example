#!/bin/bash

# Function to determine the version based on commit messages
determine_version() {
    # Check if a previous version was provided
    if [[ -z "$1" ]]; then
        # No previous version provided, set a default version
        echo "0.1.0"
        return
    fi

    # Extract major and minor components from the previous version
    IFS='.' read -r MAJOR MINOR PATCH <<< "$1"

    # Get the type of changes since the last release
    BREAKING_COMMITS=$(git log "$1"..HEAD --grep="\[BREAKING CHANGE\]")
    FEATURE_COMMITS=$(git log "$1"..HEAD --grep="feat:")
    FIX_COMMITS=$(git log "$1"..HEAD --grep="fix:")

    # Increment the version components based on commit messages
    if [[ -n "${BREAKING_COMMITS}" ]]; then
        ((MAJOR++))
        MINOR=0
        PATCH=0
    elif [[ -n "${FEATURE_COMMITS}" ]]; then
        ((MINOR++))
        PATCH=0
    elif [[ -n "${FIX_COMMITS}" ]]; then
        ((PATCH++))
    else
        # echo "No significant changes found since the last release. Keeping the same version: $1"
        echo "$1"
        return
    fi

    # Output the new version
    echo "${MAJOR}.${MINOR}.${PATCH}"
}

# echo "Previous version provided: $1"

# Determine the version using the previous version passed as an argument
VERSION=$(determine_version "$1")
# echo "After determining version"
echo "${VERSION}"
