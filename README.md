# conceptual

[![Build Status](https://travis-ci.org/davidbarsky/conceptual.svg?branch=master)](https://travis-ci.org/davidbarsky/conceptual)

conceptual, when given a directed acyclic graph, creates a logical schedule.

## Motivation

Finding an optimal schedule for a set of tasks is an NP-hard problem. The goal of this research is to cheat by searching a smaller subset of schedules. Permutations of the aforementioned schedules would be found using a Birkhoff-von Neumann decomposition.

## Installation

Requirements:
- [`ggen`](https://github.com/perarnau/ggen) (no `brew` formula exists, you'll need to build it yourself.)
- [`sbt`](http://www.scala-sbt.org/download.html)

(Maybe one day, I'll plop this into a container. Today's not that day.)

Then:

1. `git clone git@github.com:davidbarsky/conceptual.git`
2. `cd conceptual`
3. `sbt test`

---

*This library is part of my senior thesis, conducted under the supervision of [Ryan Marcus](rmarcus.info) and [Olga Papaemmanouil](http://www.cs.brandeis.edu/~olga/home.html). The name of the library was taken from [Jenny Hval’s “Conceptual Romance”](https://www.youtube.com/watch?v=EY7eLAVrfK4).*
