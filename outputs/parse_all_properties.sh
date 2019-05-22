available_properties=("FirstDictionaryWordInArticle" "LastDictionaryWordInArticle" "NumberOfWordsInArticle" "DictionaryWordsInArticle" "NumberOfDictionaryWordsInFirstPartOfArticle" "NumberOfDictionaryWordsInLastPartOfArticle" "MostFrequentDictionaryWord" "LeastFrequentDictionaryWord")
declare -a available_metrices=( "ChebyshevMetric" "EukidesMetric" "ManhattanMetric")
declare -a available_similarities=("ExtendedNGramSimilarity" "JaccardSimilarity" "NGramSimilarity")
declare -a available_k=("1" "5" "10" "15" "20" "25" "30");

# DLA PROPERTIES
echo "avg;avg-time;property" > sprawko_csv/eksperyment_2.1.number8.avg.csv
awk -F';' '{OFS=";"} {sum += $20} {time += $21 } END { if (NR > 0) print sum / NR, time / NR }' sprawko_csv/eksperyment_2.1.number8.csv >> sprawko_csv/eksperyment_2.1.number8.avg.csv
	truncate --size -1 sprawko_csv/eksperyment_2.1.number8.avg.csv
echo ";all" >> sprawko_csv/eksperyment_2.1.number8.avg.csv

for to_look_for in ${available_properties[@]}; do
	cat sprawko_csv/eksperyment_2.2.all_7.csv | grep -v  $to_look_for > sprawko_csv/eksperyment_2.without_$to_look_for;
	awk -F';' '{OFS=";"} {sum += $20 } {time += $21} END { if (NR > 0) print sum / NR, time / NR }' sprawko_csv/eksperyment_2.without_$to_look_for > sprawko_csv/eksperyment_2.without_$to_look_for.avg.csv
	truncate --size -1 sprawko_csv/eksperyment_2.without_$to_look_for.avg.csv 
	echo ";$to_look_for" >> sprawko_csv/eksperyment_2.without_$to_look_for.avg.csv 
done


# DLA KRAJÓW
cat sprawko_csv/eksperyment_2.*avg* > sprawko_csv/all_avgs.csv

LC_NUMERIC=en_US.UTF-8
for to_look_for in ${available_properties[@]}; do
	awk -F';' 'BEGIN{OFS=";"} FNR > 1 {print $1,$2,($9*100)/$8,($11*100)/$10,($13*100)/$12,($15*100)/$14,($17*100)/$16,($19*100)/$18}' sprawko_csv/eksperyment_2.without_$to_look_for >> sprawko_csv/eksperyment_2.countries_norm_without_$to_look_for.csv
	awk -F';' '{OFS=";"} {sum_usa += $3} {sum_france += $4} {sum_canada += $5} {sum_westgermany += $6} {sum_uk += $7} {sum_japan += $8} END { if (NR > 0) print sum_usa/NR, sum_france/NR, sum_canada/NR, sum_westgermany/NR, sum_uk/NR, sum_japan/NR }' sprawko_csv/eksperyment_2.countries_norm_without_$to_look_for.csv > sprawko_csv/eksperyment_2.countries_without_$to_look_for.avg.csv
	truncate --size -1 sprawko_csv/eksperyment_2.countries_without_$to_look_for.avg.csv 
	echo ";$to_look_for" >> sprawko_csv/eksperyment_2.countries_without_$to_look_for.avg.csv 
done

echo "avg-usa;avg-france;avg-canada;avg-westgermany;avg-uk;avg-japan;property" > sprawko_csv/all_countries_avg.csv
cat sprawko_csv/eksperyment_2.countries*avg* >> sprawko_csv/all_countries_avg.csv

# PORÓWNYWANIE METRYK
LC_NUMERIC=en_US.UTF-8
for metric in ${available_metrices[@]}; do
	cat sprawko_csv/eksperyment_2.1.number8.csv | grep $metric | sort -n -k 1.1 > sprawko_csv/eksperyment_4.metric_$metric.csv;
	for similarity in ${available_similarities[@]}; do
		cat sprawko_csv/eksperyment_4.metric_$metric.csv | grep $similarity > sprawko_csv/eksperyment_4.metric_$metric\_similarity_$similarity.csv;
		echo "K;overal;time" > sprawko_csv/eksperyment_4.metric_$metric\_similarity_$similarity\_parsed.csv
		awk -F';' 'BEGIN{OFS=";"} FNR > 1 {print $1, $20, $21}' sprawko_csv/eksperyment_4.metric_$metric\_similarity_$similarity.csv >> sprawko_csv/eksperyment_4.metric_$metric\_similarity_$similarity\_parsed.csv;
	done
done
