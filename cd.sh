run_service(){
	mvn exec:java -Dexec.mainClass="mediaserver.MediaService" -Dexec.args="server /etc/medis-service/server.yml" & echo $! > ./ci/pid
}

check(){
    	status=`curl -s -o /dev/null -I -w "%{http_code}" http://localhost:40405/ping`
    	echo $status
}



is_latest(){
	NOW=git log --format="%H" -n 1
	PRE=$(cat ./ci/commit_id)
	if [ $NOW == $PRE ]
	then
		echo true
	else
		echo false
	fi
}

kill(){
	kill $(cat ./ci/pid)
}

pull {
	git pull origin master
}

build {
	mvn compile
}

while [ true ]
do

	if [ is_latest == false ]
	then 
		echo Killing...
		kill
		echo Pulling...
		pull
		echo Building
		build
		echo deploy
		run_service
	fi
	
	sleep 10;
done
