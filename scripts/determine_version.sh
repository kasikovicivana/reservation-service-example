#!/bin/bash

# Function to determine the version based on changes
determine_version() {
    # Logic to analyze changes and determine the version
    # For simplicity, let's assume every commit with "[BREAKING CHANGE]" in the message increments the MAJOR version,
    # commits with "feat:" increment the MINOR version, and commits with "fix:" increment the PATCH version.

    # Get the latest tag to determine the previous version
    PREVIOUS_VERSION=$(git describe --tags --abbrev=0)

    # Get the type of changes since the last release
    BREAKING_COMMITS=$(git log ${PREVIOUS_VERSION}..HEAD --grep="\[BREAKING CHANGE\]")
    FEATURE_COMMITS=$(git log ${PREVIOUS_VERSION}..HEAD --grep="feat:")
    FIX_COMMITS=$(git log ${PREVIOUS_VERSION}..HEAD --grep="fix:")

    # Increment the version components
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
        echo "No significant changes found. Keeping the same version: ${PREVIOUS_VERSION}"
        exit 0
    fi

    # Output the new version
    echo "${MAJOR}.${MINOR}.${PATCH}"
}

# Determine the version
VERSION=$(determine_version)
echo "New version: ${VERSION}"
