#!/usr/bin/env bash

setUp() {
#    TODO Not Implemented yet!
    echo "Functional test setUp started."
}

tearDown() {
#    TODO Not Implemented yet!
    echo "Functional test tearDown started."
}

case "${1}" in
    "setUp")
        setUp;;
    "tearDown")
        tearDown;;
    *)
        echo Unknown command! ${1}
esac
