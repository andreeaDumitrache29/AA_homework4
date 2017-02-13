#!/bin/bash

input_dir=./input/
output_dir=./output/
ref_dir=./ref/

tests_nr=$(ls $input_dir | wc -w)
points_per_test=$((100/tests_nr))

make build

if [ $? -ne 0 ]; then
    "Makefile failed."
else
    total_points=0

    for ((i=1; i<=$tests_nr; i++)); do
        printf "Test $i: "
        
        cp $input_dir/test${i}.in ./test.in

        make run &>/dev/null

        if [ $? -eq 0 ]; then
            mv test.out $output_dir/test${i}.out

            equiv=$(python3 test.py $output_dir/test${i}.out $ref_dir/test${i}.ref)

            if [ "$equiv" == "1" ]; then
            	printf "Success"
            	total_points=$((total_points+points_per_test))
            else
            	printf "Fail"
            fi
        else
        	printf "Run target failed"
        fi

        rm ./test.in
        printf "\n"
    done
fi

echo "Total: " $total_points
make clean