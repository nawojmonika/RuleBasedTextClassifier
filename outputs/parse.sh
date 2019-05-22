LC_NUMERIC=en_US.UTF-8
cat all.csv | cut -d';' -f 1,2,3,4,20 | sort -n -r -t';' -k5 > overal.csv
echo "K;Properties;usa;france;canada;west-germany;uk;japan" > countries.csv
awk -F';' 'BEGIN{OFS=";"} FNR > 1 {print $1,$2,($9*100)/$8,($11*100)/$10,($13*100)/$12,($15*100)/$14,($17*100)/$16,($19*100)/$18}' all.csv >> countries.csv
awk -F';' 'BEGIN{OFS=";"} FNR > 1 {print $1,$2,$3,$4,$5,$6,$7,$8,(($3+$4+$5+$6+$7+$8))}' countries.csv > countries_counted.csv
echo "K;Properties;usa;france;canada;west-germany;uk;japan;counted" > countries_counted_all.csv
sort countries_counted.csv -t';' -k9,9 >> countries_counted_all.csv
