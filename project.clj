(defproject final_project "0.0.1-SNAPSHOT"
  :description "TODO: add summary of your project"
  :aot [final_project.model.DataStore
        final_project.input.XmlReader]
  :main final_project.view.SetupWindow
  :dependencies [[clojure "1.2.0"]
                 [clojure-contrib "1.2.0"]
                 [junit "4.8.2"]]
  :dev-dependencies [[swank-clojure "1.2.1"]])
