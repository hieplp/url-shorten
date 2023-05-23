#!/bin/sh

# List of project directories
projects=(
         "gateway-services"
         "auth-services"
         "shorten-services"
         "user-services"
         "statistic-services"
)

# Loop through the projects and build each one
# shellcheck disable=SC2039
for project in "${projects[@]}"
do
    echo "Building $project..."
    (cd "../$project" && sh build.sh)
    echo "Done building $project"
    echo "---------------------------------"
done
