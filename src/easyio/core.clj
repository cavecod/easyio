(ns easyio.core
  "library code.
  wrap java.io.File"
  (:gen-class)
  (:use [clojure.tools.logging :only (error)])
  (:require [clojure.java.io :as io]))

(defn- _err 
  [v]
  (error v)
  v)

(defn- create-jf
  [v]
  (let [n (io/as-file (str v))]
    (if (nil? n)
      ((_err "error: to java.io.File fail"))
      n)))  ;; return java.io.File

(defn- to-jf 
  [v]
  (cond
    (nil? v) (_err "error: parameter is nil")      ;; log and return string
    (empty? v) (_err "error: parameter is empty")
    (not (string? v)) (_err "error: parameter not string")
    :else (create-jf v)))



(defn exists 
  "check file or direcroty exists.
  return: success = true/false, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.exists n))))

(defn can-read
  "check file or direcroty can read.
  return: success = true/false, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.canRead n))))

(defn can-write
  "check file or direcroty can write.
  return: success = true/false, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.canWrite n))))

(defn delete
  "delete file or direcroty (and sub direcroty).
  return: success = true/false, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.delete n))))

(defn create-new-file
  "create new file.
  return: success = true/false, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.createNewFile n))))

(defn mkdir
  "create new direcroty.
  return: success = true/false, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.mkdir n))))

(defn mkdirs
  "create new direcroty, many level.
  return: success = true/false, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.mkdirs n))))

(defn get-name
  "get file or direcroty name.
  return: success = string, fail = nil"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      nil
      (.getName n))))

(defn get-path
  "get file or direcroty path.
  return: success = string, fail = nil"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      nil
      (.getPath n))))

(defn get-length
  "get file (can not direcroty) size.
  return: success = num (not exists file return 0), fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.length n))))

(defn is-file
  "check is file.
  return: success = true/false, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.isFile n))))

(defn is-dir
  "check is direcroty.
  return: success = true/false, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.isDirectory n))))

(defn get-absolute-path
  "get absolute path
  return: success = string, fail = nil"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.getAbsolutePath n))))

(defn- execute-get-name [v]
  (.getName v))

(defn list-files
  "get path all file and direcroty name (only current level, not recur sub direcroty).
  return: success = (string list)/nil, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (let [fs (.listFiles n) index (.indexOf (str (type fs)) "java.io.File")]  ;; fs is java.io.File[]
        (if (< index 0 )
          "error: can not get java.io.File"
          (map execute-get-name fs))))))

(defn get-last-modified
  "get last modified time.
  return: success = num, fail = error msg"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      n
      (.lastModified n))))

(defn rename-to
  "rename file or direcroty.
  return: success = true/false, fail = error msg"
  [na nb]
  (let [a (to-jf na) b (to-jf nb)]
    (cond
      (string? a) a
      (string? b) b
      :else (.renameTo a b))))

(defn to-string
  "obj to string.
  return: success = string, fail = nil"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      nil
      (.toString n))))

(defn get-parent
  "get parent.
  return: success = string, fail = nil"
  [v]
  (let [n (to-jf v)]
    (if (string? n)
      nil
      (.getParent n))))
