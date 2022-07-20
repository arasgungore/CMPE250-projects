# Project 4: Help the Santa Claus!



## Run on Terminal

```sh
javac src/*.java -d bin --release 16
java -cp bin project4main testcases/input_1.txt output_1.txt
```



## Introduction

Hi, there! Firstly, thank you all in advance for helping me on distributing the gifts. But it
won’t be that straightforward because I have various gifts to distribute to different
regions. I will explain the properties of gifts and how we will distribute them. Santa
Claus Expresses and Santa Claus’s reindeers will help us but unfortunately, they can
carry gifts as much as their capacities. Therefore, in some cases, it will be impossible to
deliver all gifts to their owners. At this point, I need your help. Can you help me to figure
out the minimum number of gifts that can’t be delivered?



## Details

There are two kinds of regions, which we call the green region and the red region.
Therefore, trains and reindeers can be classified as those going to the green region and
the red region.

Our gifts are in the bags, and we should distribute them to trains and reindeers
according to the properties of the bag. You can consider that the same type of gifts is in
the same bag, and we want to distribute them properly.

Properties of bags are as follow, remark that one bag has more than one property:
- a: Each of the gifts in this bag type should be distributed through different vehicles,
i.e. there are no 2 gifts from the same bag on the same train or reindeer.
- b: Each of the gifts in this bag type should only be distributed to green regions.
- c: Each of the gifts in this bag type should only be distributed to red regions.
- d: Each of the gifts in this bag type should only be distributed by train.
- e: Each of the gifts in this bag type should only be distributed by reindeer.
If it’s not specified, assume that gifts can be distributed to all regions, both by train
and reindeer.

Let’s look at some examples of bag types:
- bd can be distributed only by trains which go to green regions.
- ace can be distributed only by reindeers which go to red regions and there are
no 2 gifts from this bag on the same reindeer.
- c can be distributed only to red regions, both by trains and reindeers.
- d can be distributed only by trains, to both the red and the green regions.
- a only constraint is that there are no 2 gifts from this bag on the same vehicle.
- bc invalid, it won’t be given as an input to you because it is a contradictory.
- de invalid, it won’t be given as an input to you because it is a contradictory.



## Input/Output


### Input

1. The first line represents the number of Santa Claus Expresses that are going to
    the green region.
2. The second line will give the capacities of each of these trains.
3. The third line represents the number of Santa Claus Expresses that are going to
    the red region.
4. The fourth line will give the capacities of each of these trains.
5. The fifth line represents the number of Santa Claus’s reindeers that are going to
    the green region.
6. The sixth line will give the capacities of each of these reindeers.
7. The seventh line represents the number of Santa Claus’s reindeers that are going
    to the red region.
8. The eighth line will give the capacities of each of these reindeers.
9. The ninth line represents the number of bags.
10. The tenth line will give the type of bags and the number of gifts in them.


### Output

1. For each test case, there will be one line output that gives the minimum possible
    number of gifts **that can’t** be distributed.
