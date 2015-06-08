#!/bin/bash

# awk intenal variables

awk -f internal-variables.awk note.dat note.copy.dat



# 测试 FS， OFS， ORS， RS
echo "1:2:3:4:5:6|a:b:c:d:e:f" | awk -f fs-rs.awk