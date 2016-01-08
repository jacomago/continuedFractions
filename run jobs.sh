
#!/bin/bash

for i in `seq 3 3`
do 
		java -Xmx900m -cp bin:commons-math3-3.5/commons-math3-3.5.jar org/multiplenumbers/ListPolysMain 10 $i > ${i}polys.csv
		N=$(wc -l ${i}polys.csv)
		echo $N
		#qsub -t 1-$N -v polys=${i}polys.csv polys.job 
done
