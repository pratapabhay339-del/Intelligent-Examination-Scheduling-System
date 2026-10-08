# Intelligent Examination Scheduling System (Java)

A Data Structures and Algorithms II prototype for examination scheduling.

## Features

- Conflict graph using an adjacency list
- DFS connected components
- BFS conflict-hop analysis
- Greedy graph colouring for exam slots
- 0/1 Knapsack for slot capacity
- Dynamic-programming invigilator allocation
- Final clash-free timetable verification

## Project Structure

```
ExamScheduler/
├── README.md
├── output.txt
└── src/
    ├── Exam.java
    ├── ConflictGraph.java
    ├── SlotColoring.java
    ├── CapacityKnapsack.java
    ├── InvigilatorAllocation.java
    └── Main.java
```

## Run

From the `ExamScheduler` directory:

```bash
cd src
javac -d ../out *.java
java -cp ../out Main
```

Sample output is available in `output.txt`.

## DSA Concepts

| Component | DSA / Algorithm |
|---|---|
| ConflictGraph | Graph + adjacency list |
| Connected components | DFS |
| Conflict distance | BFS |
| SlotColoring | Greedy graph colouring |
| CapacityKnapsack | 0/1 Knapsack DP |
| InvigilatorAllocation | Resource allocation DP |

The included student and exam data is illustrative sample data, not real institutional data.
