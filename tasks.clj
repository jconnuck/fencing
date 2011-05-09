(use
 ['cake.tasks.compile :only ['compile-java 'compile-clojure 'source-dir]]
 ['cake.file :only ['file]])

(deftask pre-compile #{deps compile-native}
  (doseq [path (:pre-compile *project*)]
    (compile-java (file path)))
  (compile-clojure (source-dir) (file "classes") (:aot *project*)))

(deftask compile-java #{pre-compile})

(deftask compile #{compile-java})