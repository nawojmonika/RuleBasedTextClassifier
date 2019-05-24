cat $(find ./all -name stdout) > all.csv
cat $(find ./all/ -name stdout) > all_properties.csv
cat $(find ./string/ -name stdout) >> all_properties.csv
cat $(find topics -name stdout) > all_topics.csv
awk -F , 'NF == 9' < all_properties.csv > sprawko_csv/eksperyment_2.1.number8.csv
awk -F , 'NF == 8' < all_properties.csv > sprawko_csv/eksperyment_2.2.all_7.csv
