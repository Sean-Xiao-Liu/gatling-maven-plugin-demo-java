#!/bin/bash

# Define the Gatling results directory
RESULTS_DIR="target/gatling"

# Check if the directory exists
if [ -d "$RESULTS_DIR" ]; then
  cd "$RESULTS_DIR"
  echo "Current directory: $(pwd)"
  echo "Directories before cleaning:"
  ls -dt */

  # Find all unique simulation class prefixes (excluding the timestamp part)
  for sim_class in $(ls -dt */ | sed 's/-[0-9]\{14\}\///' | uniq); do
    # List directories for each simulation class, sort them by modification time,
    # skip the most recent one, and delete the others
    ls -dt ${sim_class}-*/ | tail -n +2 | xargs rm -rf
  done

  echo "Directories after cleaning:"
  ls -dt */
else
  echo "Directory $RESULTS_DIR does not exist."
fi