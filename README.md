# easyio

make easy to use io on clojure, wrap java.io.File and other

## Installation

Leiningen/Boot

  [org.clojars.cavecod/easyio "0.1.5-SNAPSHOT"]

## Documentation
* [API Doc](https://cljdoc.org/versions/org.clojars.cavecod/easyio)

## Examples
```clojure
(ns demo.core-test
  (:use [clojure.tools.logging :only (info error)])
  (:require [clojure.test :refer :all]
            [org.clojars.cavecod/easyio.core :refer :all]))

;; var
(def not-exists-fn "test-dir/not-exists-file.txt")
(def test-fn "test-dir/test-file.txt")
(def test-fn2 "test-dir/test-file2.txt")
(def test-dirn "test-dir/a")
(def test-dirn2 "test-dir/b/c")

(defn width-test [f]
  (delete not-exists-fn)
  (f))


(deftest test-exists
  (testing
    (is (= (exists ".") true))
    (is (= (exists not-exists-fn) false))))

(deftest test-can-read
  (testing
    (is (= (can-read ".") true))
    (is (= (can-read not-exists-fn) false))))

(deftest test-can-write
  (testing
    (is (= (can-write ".") true))
    (is (= (can-write not-exists-fn) false))))

(deftest test-get-name
  (testing
    (create-new-file test-fn)
    (is (= (get-name ".") "."))
    (is (string? (get-name not-exists-fn)))
    (is (string? (get-name test-fn)))))

(deftest test-get-path
  (testing
    (is (= (get-path ".") "."))
    (is (= (get-path not-exists-fn) not-exists-fn))))

(deftest test-delete
  (testing
    (let [fn test-fn]
      (delete fn)
      (create-new-file fn)
      (is (= (delete fn) true))
      (is (= (delete nil) "error: parameter is nil"))
      (is (= (delete ()) "error: parameter is empty"))
      (is (= (delete []) "error: parameter is empty")))
      (is (= (delete [1 2 3]) "error: parameter not string"))))

(deftest test-create-new-file
  (testing
    (let [fn test-fn]
      (delete fn)
      (is (= (create-new-file fn) true))
      (is (= (create-new-file not-exists-fn ) true))
      (is (= (create-new-file nil) "error: parameter is nil"))
      (is (= (create-new-file ()) "error: parameter is empty"))
      (is (= (create-new-file []) "error: parameter is empty"))
      (is (= (create-new-file [1 2 3]) "error: parameter not string")))))

(deftest test-mkdir
  (testing
    (delete test-dirn)
    (is (= (mkdir test-dirn) true))))

(deftest test-mkdirs
  (testing
    (delete test-dirn2)
    (is (= (mkdirs test-dirn2) true))))

(deftest test-get-length
  (testing
    (create-new-file test-fn)
    (is (= (get-length ".") 4096))  ;; dir always return 4096
    (is (= (get-length not-exists-fn) 0))
    (is (= (get-length test-fn) 0))))

(deftest test-is-file
  (testing
    (create-new-file test-fn)
    (is (= (is-file ".") false))
    (is (= (is-file not-exists-fn) false))
    (is (= (is-file test-fn) true))))

(deftest test-is-dir
  (testing
    (create-new-file test-fn)
    (is (= (is-dir ".") true))
    (is (= (is-dir not-exists-fn) false))
    (is (= (is-dir test-fn) false))))

(deftest test-get-absolute-path
  (testing
    (create-new-file test-fn)
    (let [p (get-absolute-path ".")]
      (info "get-absolute-path:" p)
      (is (string? p)
          (is (= (.indexOf p "error") -1))))
    (let [p (get-absolute-path test-fn)]
      (info "get-absolute-path:" p)
      (is (.endsWith p test-fn)))))

(deftest test-list-files
  (testing
    (create-new-file test-fn)
    (let [v (list-files ".")]
      (info "file list" v " --- type:" (type v))
      (is (seq? v)))
    (is (= (list-files not-exists-fn) "error: can not get java.io.File"))
    (is (= (list-files test-fn) "error: can not get java.io.File"))))

(deftest test-last-modified
  (testing
    (create-new-file test-fn)
    (is (= (get-last-modified not-exists-fn) 0))
    (is (>= (get-last-modified test-fn) 9999999))))

(deftest test-rename-to
  (testing
    (delete test-fn)
    (delete test-fn2)
    (create-new-file test-fn)
    (is (= (rename-to test-fn test-fn2) true))))

(deftest test-to-string
  (testing
    (create-new-file test-fn)
    (is (string? (to-string "."))
    (is (= (to-string test-fn) test-fn)))))

(deftest test-get-parent
  (testing
    (let [p (get-parent "/home/share")]
      (info "get-parent" p)
      (is (string? p)))))

;; each test
(use-fixtures :each width-test)
```

## License

Copyright © 2021 by cavecod

Distributed under the GPL3 License.

https://www.gnu.org/licenses/gpl-3.0.txt
            
