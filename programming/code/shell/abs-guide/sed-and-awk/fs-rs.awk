BEGIN{
	FS=":";
	RS="|";
	ORS="$"
	OFS="#"
}

{$1=$1;print $0}
# ^ rebuild the record; read awk guide for more information.