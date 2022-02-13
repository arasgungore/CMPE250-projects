# Project 5: Time is Money


## Introduction

“The time is the most valuable thing.” -Vinay Chhabra & Manish Dewan. Time is much more
valuable for engineers since it is a natural constraint that can not be stopped and what
engineers do is to fight with constraints.

A food processing factory processes the raw materials sent by customers and turns them
into the desired final product. There are two machines in the factory. These are the A and
B machines. For a raw material to be converted into the desired final product, it must be
processed in A and B machines at certain predetermined times depending on the properties
of the product. This process takes place sequentially. Machine A takes the raw material and
turns it into an intermediate product, while machine B transforms this intermediate product
into the final product. The raw material is perishable before entering machine A, so it must
be sent to the factory by the customer exactly at the time it should enter the machine A. The
intermediate product produced by machine A is not perishable and can be sent to the machine
B at any time to be processed.

The factory has received an order. In this order, the names of the requested products, the
time required for the raw material to be spent in the A and B machines to convert the products
into the final product, and the profits to be obtained from the products are written respectively.
Factory engineer is a smart person with good time management. S/he listed all these jobs as
the shortest time from the beginning of the first job to the end of the last job. According to
this list, s/he determined the start time of each job. S/he also determined the time when all
jobs will be done. S/he sent these start times to the customer so that they could send the
required raw materials exactly at that time, and the end time was the deadline for the contract
to be made. The customer found these deadlines appropriate, and the contract was made. If
the factory cannot grow a product within the specified deadline, it cannot receive the money
it should receive for this product.

After the contract was signed, inspectors from the Ministry of Environment and Urbaniza-
tion came to the factory. Investigators inspected machines A and B and sealed them as they
were not environmentally friendly. The factory, which will no longer use these two machines,
has bought a new machine that performs the work of these two machines on a single belt.
This machine takes the raw material and turns it directly into the final product. The time
taken for this process is equal to one of the times that the product must spend in machine
A or B in the order list. If the product is a solid type, the total time taken for the new
machine to produce the product is equal to the time the product must spend in machine A in
the order list; if the product is a liquid type, the total time it takes for the new machine to
produce the product is equal to the time the product must spend in machine B in the order list.

The engineer examined the new machine and predicted that under these conditions, some
of the products in the contract may not be grown within the specified deadline. Therefore, if
it cannot grow all of them, it has decided to produce some of the products in a way that will
maximize the profit of the factory. In this project, you are asked to calculate the maximum
profit that the company can achieve in this case by looking at the order list given to you by
the factory engineer.

## Details

- For each case, output consists of one integer, int1. It is is the maximum profit that the
    factory can gain after machine A and machine B are banned and new machine starts to
    process.
- Customers sends raw material on the day in the contract. Dates are determined according
    to times that products started to be processed on machine A. Raw material can not be
    sent before or after that time because of its perishable properties.
- Start and end times of the products that may be processed on the new machine are
    determined based on the type of products. Start time is always the time when raw
    material is reached to the factory.(It is actually starting time of the process on machine
    A for each product). End time is start time + X where X is machine A’s process time of
    the product if the product is solid otherwise machine B’s process time of the product.

## Input/Output

### Input

- The first line represents the type of products respectively.
- The second line represents the processing times of the products on machine A respectively.
- The third line represents the processing times of the products on machine B respectively.
- The fourth line represents the profit gained by factory by processing products respectively.
- The fifth line represents the receiving times of each products according to the signed
    contract.

### Output

1. An integer representing the potential maximum profit of the factory.
