# Clojure Collection Examples

Show use of Clojure Collection functions using examples problems

## Running REPL
The REPL uses the [Clojure CLI](https://clojure.org/guides/deps_and_cli). To run the REPL enter in `clj -Asocket` in the command line. A [socket REPL](https://clojure.org/reference/repl_and_main#_launching_a_socket_server) will start on port `50505`

## Examples
Each examples has a link to the example file. Every line in a code block file shows the results of running that code block

[Transpose](./src/collection/demo/transpose.clj) for example has the line
```clojure
(interleave [:one :list :of :things] [:a :list :of :stuff] [:maybe :another :list :perhaps])
;; => (:one :a :maybe :list :list :another :of :of :list :things :stuff :perhaps)
```   

### Shifting
[Shifting](./src/collection/demo/shift.clj) shifts a clojure collection to the left or right with wraparound 

### Transpose
[Transpose](./src/collection/demo/transpose.clj) flips rows to columns and columns to rows

### File Operations
[File Operations](./src/collection/demo/file_operations.clj) simulates a file protocol and validates operation correctness

### Queue
[Queue](./src/collection/demo/queue_diffs.clj) simulates actions on a dropping queue and show the data difference between different sequences of actions

### Game of Life
[Game of Life](./src/collection/demo/shift.clj) with an infinite grid and getting the dying and born cells of each step

### Song Shuffle
[Song Shuffle](./src/collection/demo/song_shuffle.clj) shuffles a list of songs

### SQL Rank
[SQL Rank](./src/collection/demo/sql_rank.clj) simulates SQL windowing functions and rank over a group of employees