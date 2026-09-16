# Intelligent Examination Scheduling System

**Course:** Data Structure and Algorithms – II (CCSE0301)
**Student:** Abhay Pratap Mall
**Project Type:** Individual Assignment
**SDG:** SDG 4 – Quality Education
**Current Progress:** 25% (Conceptual / Design Stage)

> **Note:** This is a B.Tech CSE, second-year, DSA-II academic project — not a commercial or production system. It currently studies and maps DSA concepts to a real-world scheduling problem. Full implementation has **not** been completed yet; this README clearly separates what has been studied/designed from what is planned for later stages.

---

## Table of Contents
1. [Overview](#1-overview)
2. [Problem Statement](#2-problem-statement)
3. [Background](#3-background)
4. [Objectives](#4-objectives)
5. [Target Users / Stakeholders](#5-target-users--stakeholders)
6. [How the System Works](#6-how-the-system-works)
7. [DSA Concepts Used](#7-dsa-concepts-used)
8. [Proposed Scheduling Algorithm](#8-proposed-scheduling-algorithm)
9. [Graph Representation](#9-graph-representation)
10. [Example](#10-example)
11. [Literature Review](#11-literature-review)
12. [Proposed Improvements](#12-proposed-improvements)
13. [Current Progress](#13-current-progress)
14. [Future Scope](#14-future-scope)
15. [Limitations](#15-limitations)
16. [Proposed Project Structure](#16-proposed-project-structure)
17. [Complexity Analysis](#17-complexity-analysis)
18. [Technology](#18-technology)
19. [Conclusion](#19-conclusion)
20. [References](#20-references)

---

## 1. Overview

The **Intelligent Examination Scheduling System** is a DSA-II academic project that studies how core Data Structures and Algorithms — trees, heaps, and graphs — can be applied to the real-world problem of examination timetabling. The goal is to understand how exam records can be organized, how conflicts between exams can be represented, and how a conflict-free (or low-conflict) timetable could eventually be generated.

At this stage, the project is at the **conceptual and design level**. DSA concepts have been studied and mapped to the problem, but the full scheduling system has not yet been implemented in code.

## 2. Problem Statement

Educational institutions conduct examinations for multiple departments, semesters, and elective combinations, often around the same time. When timetables are prepared manually, clashes can occur — the same **student**, **faculty member**, or **room** may end up assigned to more than one examination in the same time slot.

This project studies how Data Structures and Algorithms can be used to organize examination records, represent these conflicts, prioritize exams, and eventually work towards generating a more organized, clash-free examination timetable.

## 3. Background

As the number of courses, electives, and enrolled students grows, checking every exam pairing manually for conflicts becomes difficult and error-prone. Exams are often connected to each other — for example, two courses may share a set of common students, or one faculty member may need to invigilate more than one exam. Representing these relationships properly is key to detecting and reducing scheduling conflicts, which is why this project looks at tree-based and graph-based data structures as a foundation.

## 4. Objectives

- Understand the problem of manual examination scheduling and why it leads to conflicts.
- Study relevant DSA concepts (Trees and Graphs) and identify how they apply to this problem.
- Explore how exam/course records can be stored and searched efficiently.
- Explore how conflicts between exams can be represented using a graph.
- Explore how exams could be prioritized before scheduling.
- Study a graph-coloring-based approach for conflict-free slot assignment.
- Build the conceptual foundation required before implementation begins.

## 5. Target Users / Stakeholders

- Examination Controller / COE Office
- Heads of Department
- Faculty / Invigilators
- Students
- Administrative / Timetabling Staff

## 6. How the System Works

At a conceptual level, the system is expected to work as follows:

```
Input examination data
        ↓
Store/search exam records using trees
        ↓
Identify conflicts between examinations
        ↓
Build a conflict graph
        ↓
Find connected components
        ↓
Prioritize highly conflicting exams
        ↓
Apply graph-coloring-based slot assignment
        ↓
Check room capacity/resources
        ↓
Generate examination timetable
```

Each stage above corresponds to a specific DSA concept, described in the next section.

## 7. DSA Concepts Used

| # | Concept | Purpose in This Project |
|---|---------|--------------------------|
| 1 | **Binary Search Tree (BST)** | Store and search course/exam records by course code. |
| 2 | **AVL Tree** | Maintain a balanced index for fast course/exam lookup as records grow. |
| 3 | **Binary Heap / Priority Queue** | Prioritize exams — e.g., exams with larger batches or higher conflict levels. |
| 4 | **Heap Sort** | Sort exams according to priority when a fully ordered list is needed. |
| 5 | **Tree Traversal** (In-order, Pre-order, Post-order) | Process/export exam records in sorted or hierarchical order. |
| 6 | **Graph** | Represent examinations as vertices/nodes and conflicts as edges. |
| 7 | **BFS / DFS** | Traverse the conflict graph and identify related groups of exams. |
| 8 | **Connected Components** | Identify independent groups of exams that could potentially be processed separately. |
| 9 | **Spanning Tree / Minimum Cost Spanning Tree** | Proposed use — explore efficient allocation of rooms/invigilators. |

Conflicts between exams (represented as graph edges) may occur due to:
- Shared students
- Shared faculty/invigilators
- Room/resource conflicts

## 8. Proposed Scheduling Algorithm

The scheduling approach being explored is based on **graph coloring**:

- Each examination is a **vertex**.
- Each conflict between two examinations is an **edge**.
- Each **color** represents a time slot.
- Two adjacent vertices (conflicting exams) must **not** receive the same color (time slot).

This is a conceptual adaptation of a well-known technique used in university course/exam timetabling (see [Literature Review](#11-literature-review)). At the current stage, this describes the *intended* approach — the coloring logic itself has not yet been implemented.

## 9. Graph Representation

The conflict graph is proposed to be represented using an **adjacency list**, where:
- Each node = one examination.
- Each edge = a conflict between two examinations (shared student, faculty, or room).

```
   A --- B
   |
   C
```
In the diagram above, exam A conflicts with both B and C, but B and C do not conflict with each other.

## 10. Example

**Sample exams:**

| Exam | Course |
|------|--------|
| A | CSE101 |
| B | CSE102 |
| C | CSE103 |

If Exam A and Exam B have common students:

```
A ----- B
```

Then **A and B cannot be assigned the same time slot.**

**Example of graph-coloring-based slot assignment:**

| Exam | Assigned Slot (Color) |
|------|------------------------|
| A | Slot 1 |
| B | Slot 2 |
| C | Slot 1 |

Here, Exam C can share Slot 1 with Exam A **only if there is no conflict between A and C**.

## 11. Literature Review

The following paper was referred to for understanding how graph coloring is applied to conflict-free timetabling in practice:

> Ogunkan, S. K., Idowu, P. O., Omidiora, E. O., & Oyeleye, C. A. (2024). **First Fit Algorithm: A Graph Coloring Approach to Conflict-Free University Course Timetabling.** *Asian Journal of Research in Computer Science, 17*(5), 125–139. DOI: [10.9734/ajrcos/2024/v17i5443](https://doi.org/10.9734/ajrcos/2024/v17i5443)

This paper discusses using the **First Fit graph-coloring algorithm** to assign conflict-free time slots for university course timetabling, where courses are modeled as graph vertices and shared-student conflicts as edges — the same core idea being explored in this project.

## 12. Proposed Improvements

Based on the study of the paper above, the following improvements have been **identified conceptually** for this project (not yet implemented):

1. **Order exams by degree/conflict count before coloring**, so highly conflicting exams are considered first and fewer time slots are wasted.
2. **Split the conflict graph into connected components** before processing, so coloring is applied to smaller sub-graphs instead of one large graph.
3. **Perform room-capacity checking separately**, after the time-slot/color has already been assigned, rather than mixing it into the coloring step.
4. **Use DSA-level time-complexity analysis** (see [Section 17](#17-complexity-analysis)) instead of relying only on software-engineering metrics.

## 13. Current Progress

**Overall Progress: 25%**

| Task | Status |
|------|--------|
| Problem statement identified | ✅ Done |
| Requirements initially identified | ✅ Done |
| Target users/stakeholders identified | ✅ Done |
| DSA Trees concepts studied | ✅ Done |
| DSA Graph concepts studied | ✅ Done |
| DSA concepts mapped to the problem | ✅ Done |
| Initial conceptual design prepared | ✅ Done |
| Literature review completed for the selected paper | ✅ Done |
| **Actual full implementation** | ❌ **Not completed yet** |

## 14. Future Scope

- Refine requirements
- Finalize the data structures to be used
- Design the conflict graph in detail
- Implement tree-based exam record management
- Implement graph-based conflict detection
- Implement exam prioritization
- Implement basic graph-coloring scheduling
- Add room-capacity checking
- Generate timetable output
- Test using sample examination data
- Analyze time and space complexity of the final implementation

## 15. Limitations

- The current version is primarily **conceptual / prototype-level**.
- Real institutional data is **not yet available** for testing.
- Large-scale performance has **not yet been experimentally evaluated**.
- Complete room and invigilator allocation is **planned for future implementation**.
- Scheduling quality will depend on the chosen algorithm and constraints once implemented.

## 16. Proposed Project Structure

> The structure below is a **proposed layout** for future implementation — it has not been built yet.

```
Intelligent-Examination-Scheduling-System/
│
├── README.md
├── src/
│   ├── trees/
│   ├── graphs/
│   ├── scheduling/
│   └── main/
├── data/
├── docs/
└── tests/
```

## 17. Complexity Analysis

| DSA | Purpose | Time Complexity |
|-----|---------|------------------|
| Binary Search Tree (BST) | Store/search exam records by course code | O(log n) avg, O(n) worst |
| AVL Tree | Balanced index for fast lookup | O(log n) |
| Priority Queue / Binary Heap | Prioritize exams for scheduling | O(log n) insert/remove, O(1) peek |
| Heap Sort | Sort exams by priority | O(n log n) |
| Tree Traversal (In/Pre/Post-order) | Process/export exam records | O(n) |
| Graph Traversal (BFS/DFS) | Traverse the conflict graph | O(V + E) |
| Connected Components | Identify independent exam groups | O(V + E) |

*(n = number of records, V = vertices/exams, E = edges/conflicts)*

## 18. Technology

> Technology choices have not been finalized yet and will be confirmed once implementation begins.

- **Programming Language:** [To be finalized]
- **IDE:** [To be finalized]
- **Data Storage:** [If required]
- **Version Control:** Git/GitHub [if used]

## 19. Conclusion

This project explores how fundamental Data Structures and Algorithms — Binary Search Trees, AVL Trees, Heaps, and Graphs — can be applied conceptually to the real-world problem of examination scheduling. At this stage (25% progress), the focus has been on problem understanding, DSA concept mapping, and studying a relevant graph-coloring approach from the literature. Implementation, testing, and complexity evaluation of the actual system are planned for the upcoming stages of this DSA-II project.

## 20. References

1. Ogunkan, S. K., Idowu, P. O., Omidiora, E. O., & Oyeleye, C. A. (2024). First Fit Algorithm: A Graph Coloring Approach to Conflict-Free University Course Timetabling. *Asian Journal of Research in Computer Science, 17*(5), 125–139. https://doi.org/10.9734/ajrcos/2024/v17i5443
2. Course material — Data Structure and Algorithms – II (CCSE0301), Unit 1 (Trees) and Unit 2 (Graphs).

---

*Prepared by Abhay Pratap Mall — B.Tech CSE, Data Structure and Algorithms – II (Individual Assignment)*
