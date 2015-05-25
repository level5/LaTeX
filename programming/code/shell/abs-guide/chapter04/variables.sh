#! /bin/bash



variable1=23
echo variable1 # variable1
echo $variable1 # 23





a=375
hello=$a   # no whitespace between '=' and '$a'


echo hello        # hello
echo $hello       # 375
echo ${hello}     # 375, same as above, in certain context, only this form works.


# ""    weak quoting  or partial quoting, no interfere with variable substitution
# ''    strrong quoting or full quoting, no variable substitution will take place.
echo "$hello"     # 375

echo

hello="A B  C    D"

echo $hello # A B C D
echo "$hello" # A B  C    D 
