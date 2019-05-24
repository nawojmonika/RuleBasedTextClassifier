all_string_combinations=( "MostFrequentDictionaryWord,LeastFrequentDictionaryWord,FirstDictionaryWordInArticle,LastDictionaryWordInArticle,NumberOfWordsInArticle,DictionaryWordsInArticle,NumberOfDictionaryWordsInFirstPartOfArticle,NumberOfDictionaryWordsInLastPartOfArticle")
declare -a available_metrices=( "ChebyshevMetric" "EukidesMetric" "ManhattanMetric")
declare -a available_similarities=( "ExtendedNGramSimilarity" "JaccardSimilarity" "NGramSimilarity")
declare -a available_k=("1" "5" "10" "15" "20" "25" "30");

parallel --eta -j+0 --results outputs/topics/ java -jar out/artifacts/Sztuczna_jar/Sztuczna.jar \
	-k {1} \
	-p {2} \
	-m {3} \
	-s {4} \
	-le topics \
	-l trade,grain,coconut,cocoa \
	::: ${available_k[@]} ::: ${all_string_combinations[@]} ::: ${available_metrices[@]} ::: ${available_similarities[@]};
