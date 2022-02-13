# Project 1: Datalonya Student Houses



## Details

The University of Datalonya offers houses for the students. All houses
are for one student only. But they can be in various conditions: old, new,
needs renovation, broken oven, broken shower, new kitchen, new bed, etc.
According to these conditions each house has a rating point between 0 and 10.
Students study at the university for up to 8 semesters. If they are located
at a house, they stay until graduation. But each student has his/her criterion
on house selection. This criterion is a threshold on the house’s
rating. For example, if the student wants a house with a minimum rating of 4,
then he/she is not located at houses with a rating of 2 or 3 (any rating
below 4) even if they are the only available houses.
New allocations are made at the beginning of each semester. House and
student lists are checked for new allocations if possible.
Now you are enrolled at the University of Datalonya Dormitory Office.
The current list of houses and students is transferred to you. From now on,
you are responsible for the allocation of the houses.

You are supposed to arrange the lists of houses and students in collections
of your choice and simulate the allocations until all students in the list
graduate. In your simulation, check for matching houses and students at
every new semester. The output of your simulation is the list of students
who cannot stay at any house.



## Input/Output Format


### Input Format

The input will be given as a file argument. There are two types of input
lines: house lines and student lines. The input lines will be in mixed order.
You can assume that the input file is error-free. You don’t have to check for
data.
A typical line for a house is like this:

```
h <id> <duration> <rating>
```
There are 4 parts which are single-space separated in a line for a house.
The first part, the letter h, is the house indicator. The second part, \<id\>,
is the id of the house. The third part, \<duration\>, is the duration as the
number of semesters that the house is full. If the duration is 3, then this
house will be available after 3 semesters. The last part, \<rating\>, is the
rating which shows the good or bad condition of the house.
A typical line for a student is like this:

```
s <id> <name> <duration> <rating>
```
There are 5 parts which are single-space separated in a line for a student.
The first part, the letter s, is the student indicator. The second part, \<id\>,
is the student id. The third part, \<name\>, is the name of the student. The
fourth part, \<duration\>, is the duration as the number of semesters that
the student will study at the University of Datalonya. For example, if the
duration is 3, then this student will graduate after 3 semesters. Of course,
the max duration can be 8 for a student. The last part, \<rating\>, is the rating
which shows the minimum rating criterion of the student to accept a house.
For example, if the rating parameter for a student is 3.2, then this student
can only stay at houses with a rating equal to or greater than 3.2.
Your simulation program should read the input file and create appropriate
collections for students and houses. Then process these collections until all
students graduate. Your program should process houses and students in
their id order. For example, if there are two houses with ids 1 and 3 that
are available at the moment, first the house with id 1 is allocated if possible.
For students, if there are two students with ids 123 and 126 whose rating
criterion holds for a house, the student with id 123 gets the house.


### Output Format

Your program should end when the waiting student list is empty. The
output of your program is the list of students that couldn’t stay at any of
the houses. You should create a .txt file and print one student's name at a
line. The output student list must be in ascending order of student id but
only names will be printed in the output file.
