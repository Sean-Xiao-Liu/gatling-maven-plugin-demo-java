#!/bin/bash

# Define the Gatling results directory
RESULTS_DIR="target/gatling"

# Check if the directory exists
if [ -d "$RESULTS_DIR" ]; then
  cd $RESULTS_DIR
  echo "Current directory: $(pwd)"
  echo "Directories before cleaning:"
  ls -dt */

  # Find all directories, sort them by modification time, and skip the most recent one
  ls -dt */ | tail -n +2 | xargs rm -rf

  echo "Directories after cleaning:"
  ls -dt */
else
  echo "Directory $RESULTS_DIR does not exist."
fi
