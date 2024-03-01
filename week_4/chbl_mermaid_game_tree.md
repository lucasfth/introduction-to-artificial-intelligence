``` mermaid
flowchart TD
  max(0 0
  0 0)
  max -- H --> max0
  max -- H --> max1
  max -- H --> max2
  style max fill:#100099,stroke:#333,stroke-width:2px;
  style max0 fill:#006699,stroke:#333,stroke-width:2px;
  style max00 fill:#006699,stroke:#333,stroke-width:2px;
  style max01 fill:#006699,stroke:#333,stroke-width:2px;
  style max02 fill:#006699,stroke:#333,stroke-width:2px;
  max0(
  H 0
  0 0
  0.375
  )
  
  min00(
  H T
  0 0
  -0.25
  )
  max0 -- T --> min00
  max0 -- H --> min01
  max0 -- H --> min02

  min01(
  H 0
  H 0
  0.25
  )

  min01 -- T --> max011(
    H T
    H 0
    +1 
  )
  style max011 fill:#006699,stroke:#333,stroke-width:2px;

  min01 -- H --> max012(
    H 0
    H H
    -0.5
  )
  style max012 fill:#006699,stroke:#333,stroke-width:2px;

  max012 -- T --> min01200(
    H T
    H H
    -1 
    
  )
  max012 -- H --> min0121(
    H 0
    H H
    0
  )

  min02(
  H 0
  0 H
  1
  )
  min02 -- T --> max002(
    H T
    0 H
    +1
  )
  style max002 fill:#006699,stroke:#333,stroke-width:2px;
  min02 -- H --> max003(
    H 0
    H H
    +1
  )
  style max003 fill:#006699,stroke:#333,stroke-width:2px;
  max00(
  H T
  0 0
  0
  
  )
  max01(
  H T
  H 0 
  +1
  )
  max02(
  H T
  0 H
  -0.5
  )
  min000(
  H T
  0 H
  0
  )
  min001(
   H T
  H H
  -1
  )
  
  min00 -- T --> max00
  min00 -- H --> max01
  min00 -- H --> max02
  max02 -- T --> min000
  max02 -- H --> min001
  

  style max1 fill:#006699,stroke:#333,stroke-width:2px;
  style max10 fill:#006699,stroke:#333,stroke-width:2px;
  style max11 fill:#006699,stroke:#333,stroke-width:2px;
  style max12 fill:#006699,stroke:#333,stroke-width:2px;
  style max110 fill:#006699,stroke:#333,stroke-width:2px;
  style max111 fill:#006699,stroke:#333,stroke-width:2px;
  style max120 fill:#006699,stroke:#333,stroke-width:2px;
  style max121 fill:#006699,stroke:#333,stroke-width:2px;
  max1(
  0 0
  H 0
  0.75
  )
  min10(
  0 T
  H 0
  0.5
  )
  min11(
  H 0
  H 0 
  1
  )
  min12(
  0 0
  H H
  1
  )
  max1 -- T --> min10
  max1 -- H --> min11
  max1 -- H --> min12

  max10(
  0 T
  H 0 
  0
  )
  max11(
  H T
  H 0 
  +1
  )
  max12(
  0 T
  H H 
  +1
  )

  min10 -- T --> max10
  min10 -- H --> max11
  min10 -- H --> max12

  max110(
  H T
  H 0  
  +1
  )
  max111(
  H 0
  H H 
  +1 
  )
  min11 -- T --> max110
  min11 -- H --> max111

  max120(
  0 T
  H H
  +1
  )
  max121(
  H 0
  H H
  +1
  )
  min12 -- T --> max120
  min12 -- H --> max121

  style max2 fill:#006699,stroke:#333,stroke-width:2px;
  style max200 fill:#006699,stroke:#333,stroke-width:2px;
  style max201 fill:#006699,stroke:#333,stroke-width:2px;
  style max202 fill:#006699,stroke:#333,stroke-width:2px;
  style max220 fill:#006699,stroke:#333,stroke-width:2px;
  style max221 fill:#006699,stroke:#333,stroke-width:2px;
  max2(
  0 0
  0 H
  0.375
  )
  min20(
  0 T
  0 H
  -0.25
  )
  min21(
  0 0
  H H
  0.25
  )

  min21 -- T --> max2100(
    0 T
    H H
    +1
  )
   style max2100 fill:#006699,stroke:#333,stroke-width:2px;

  min21 -- H --> max2101(
    H 0
    H H
    -0.5
  )
   style max2101 fill:#006699,stroke:#333,stroke-width:2px;

  max2101 -- T --> min21010(
    H T
    H H
    -1
  )

  max2101 -- H --> min21011(
    H 0
    H H
    0
  )

  min22(
  H 0
  0 H
  1
  )

  max2 -- T --> min20
  max2 -- H --> min21
  max2 -- H --> min22
  
  max200(
  0 T
  0 H 
  0
  )
  max201(
  0 T
  H H
  +1
  )
  max202(
  H T
  0 H
  -0.5
  )
  min20 -- T --> max200
  min20 -- H --> max201
  min20 -- H --> max202

  min2020(
  H T
  0 H
  0
  )
  min2021(
  H T
  H H
  -1
  )
  max202 -- T --> min2020
  max202 -- H --> min2021

  max220(
  H T
  0 H
  +1)
  max221(
  H 0
  H H
  +1
  )
  min22 -- T --> max220
  min22 -- H --> max221
```