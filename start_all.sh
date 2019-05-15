java -jar out/artifacts/Sztuczna_jar/Sztuczna.jar \
	-k 5 \
       	-p FirstDictionaryWordInArticle \
        LastDictionaryWordInArticle \
        NumberOfWordsInArticle \
        NumberOfDictionaryWordsInFirstPartOfArticle \
        NumberOfDictionaryWordsInLastPartOfArticle \
        MostFrequentDictionaryWord \
        LeastFrequentDictionaryWord \
	-m EukidesMetric \
	-s SimpleStringCompare
