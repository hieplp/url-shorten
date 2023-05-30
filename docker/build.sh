#!/bin/sh

# List of project directories
projects=(
#         "gateway-services"
#         "auth-services"
#         "shorten-services"
#         "user-services"
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

# Loop through the projects and build each one
#echo "---------------------------------"
#echo "Docker building..."
#echo "---------------------------------"
## shellcheck disable=SC2039
#for project in "${projects[@]}"
#do
#  echo "Docker building $project..."
#  docker build -t "url-shortener/${project}" "../${project}"
#  echo "Done docker building $project"
#  echo "---------------------------------"
#done

