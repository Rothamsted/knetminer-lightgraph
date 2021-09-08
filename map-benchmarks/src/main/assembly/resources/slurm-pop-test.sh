report=slurm-pop.tsv
echo -e "Mem\tData\tAvg Store" >"$report"
for mem in 8 16 32 64 128
do
	export JAVA_TOOL_OPTIONS="-Xms2G -Xmx${mem}G"
  for data_size in 10000 100000 1000000 10000000
  do
    echo -e "\n\n\t---- MEMORY: ${mem}G  DATA_SIZE: $data_size\n"
    rm -f file.db
    out=/tmp/slurm-test.out
    srun --mem=${mem}G `pwd`/run.sh string-population -s $data_size -c 1000 -l 3 -x 250 \
      | tee "$out"
    avg_time=`awk -F ' ' '/Average:/{print $2}' $out`
    echo -e "$mem\t$data_size\t$avg_time" >>$report
  done
done

echo -e "\n\tThe End, report is at '$report'"
