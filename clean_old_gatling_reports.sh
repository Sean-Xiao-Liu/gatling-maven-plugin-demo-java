#!/bin/bash

# Define the Gatling results directory
RESULTS_DIR="target/gatling"

# Check if the directory exists
if [ -d "$RESULTS_DIR" ]; then
  echo "Gatling results directory exists. Current directory: $(pwd)"
  cd $RESULTS_DIR
  echo "Changed to Gatling results directory: $(pwd)"

  echo "Directories before cleaning:"
  ls -dt */

  # Find all directories, sort them by modification time, and skip the most recent one
  ls -dt */ | tail -n +2 | xargs rm -rf

  echo "Directories after cleaning:"
  ls -dt */
else
  echo "Directory $RESULTS_DIR does not exist."
fi