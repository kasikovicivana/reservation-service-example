#!/bin/bash

# Function to determine the version based on commit messages
determine_version() {
    # Check if a previous version was provided
    if [[ -z "$1" ]]; then
        # No previous version provided, set a default version
        echo "0.1.0"
        return
    fi

    # Get the previous version passed as an argument
    PREVIOUS_VERSION=$1

    # Get the type of changes since the last release
    BREAKING_COMMITS=$(git log ${PREVIOUS_VERSION}..HEAD --grep="\[BREAKING CHANGE\]")
    FEATURE_COMMITS=$(git log ${PREVIOUS_VERSION}..HEAD --grep="feat:")
    FIX_COMMITS=$(git log ${PREVIOUS_VERSION}..HEAD --grep="fix:")

#    # Increment the version components based on commit messages
#    if [[ -z "$(git tag)" ]]; then
#        # No tags found, set a default version
#        echo "0.1.0"
#        return
    if [[ -n "${BREAKING_COMMITS}" ]]; then
        IFS='.' read -r MAJOR MINOR PATCH <<< "${PREVIOUS_VERSION}"
        ((MAJOR++))
        MINOR=0
        PATCH=0
    elif [[ -n "${FEATURE_COMMITS}" ]]; then
        IFS='.' read -r MAJOR MINOR PATCH <<< "${PREVIOUS_VERSION}"
        ((MINOR++))
        PATCH=0
    elif [[ -n "${FIX_COMMITS}" ]]; then
        IFS='.' read -r MAJOR MINOR PATCH <<< "${PREVIOUS_VERSION}"
        ((PATCH++))
    else
        echo "No significant changes found since the last release. Keeping the same version: ${PREVIOUS_VERSION}"
        echo "${PREVIOUS_VERSION}"
        return
    fi

    # Output the new version
    echo "${MAJOR}.${MINOR}.${PATCH}"
}

# Determine the version using the previous version passed as an argument
VERSION=$(determine_version "$1")
echo "${VERSION}"
