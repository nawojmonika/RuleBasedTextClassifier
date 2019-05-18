#/bin/bash
declare -a available_properties=("FirstDictionaryWordInArticle" "LastDictionaryWordInArticle" "NumberOfWordsInArticle" "DictionaryWordsInArticle" "NumberOfDictionaryWordsInFirstPartOfArticle" "NumberOfDictionaryWordsInLastPartOfArticle" "MostFrequentDictionaryWord" "LeastFrequentDictionaryWord")

declare -a available_metrices=( "ChebyshevMetric" "EukidesMetric" "ManhattanMetric")
declare -a available_similarities=( "HandleDifference" "JaccardSimilarity" "NGramSimilarity" "SimpleStringCompare" )
declare -a available_k=("1" "2" "3" "4" "5" "6" "7" "8" "9" "10");

echo "Each property individually"
echo "K;Properties;metric;similarity;num of articles;num of testing set; num of learing set;usa;usa-good;france;france-good;canada;canada-good;west-germany;west-germany-good;uk;uk-good;japan;japan-good;overal"
for property in "${available_properties[@]}"; do
	for metric in "${available_metrices[@]}"; do
		for similarity in "${available_similarities[@]}"; do
			for k in "${available_similarities[@]}"; do
			java -jar out/artifacts/Sztuczna_jar/Sztuczna.jar \
				-k $k \
				-p $property \
				-m $metric \
				-s $similarity;
			done
		done;
	done;
done
