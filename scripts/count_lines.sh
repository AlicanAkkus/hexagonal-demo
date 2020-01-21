#!/usr/bin/env bash

function count_display() {
    EXT_LIST=($1)
    ALL_COUNT=0
    for EXT in "${EXT_LIST[@]}"; do
        COUNT=$(count ${EXT})
        ALL_COUNT=$((ALL_COUNT+COUNT))
    done

    for EXT in "${EXT_LIST[@]}"; do
        printf "%10s : " ${EXT}
        COUNT=$(count ${EXT})
        PERCENTAGE=$(bc <<<"scale=3; $COUNT * 100 / $ALL_COUNT")
        echo "$COUNT ($PERCENTAGE%)"
    done

    echo "======================"
    printf "%10s : $ALL_COUNT\n" "ALL"
    echo "======================"
}

function count() {
    EXT=$1
    cat loc.txt | grep "\.$EXT" | grep -v "loc.txt" | awk '{s+=$1} END { printf "%d\n", s}'
}

git ls-files | xargs wc -l > loc.txt;

count_display "json java groovy sql gradle js sh md yml jmx gitignore"

find . -type f -name "loc.txt" -exec rm -f {} \;
