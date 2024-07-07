#!/bin/bash

# Define the Gatling results directory
RESULTS_DIR="target/gatling"

# Find all directories in the Gatling results directory, sort them by modification time, and skip the most recent one
cd $RESULTS_DIR
ls -dt */ | tail -n +2 | xargs rm -rf