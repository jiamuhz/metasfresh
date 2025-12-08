#!/bin/bash

set -e
set -u

#
# This script does things that the user metasfresh is not allowed to do.
# The "normal" minor_remote.sh script might ask the user to run this script as super user if it encounters a problem 
#

#Thanks to http://stackoverflow.com/questions/6643853/how-to-convert-in-path-names-to-absolute-name-in-a-bash-script for the readlink tip
LOCAL_DIR=$(readlink -m $(dirname $0))
#Note: ROLLOUT_DIR can be overridden from cmdline using -d
ROLLOUT_DIR=$(readlink -m ${LOCAL_DIR}/..)

echo $LOCAL_DIR
echo $ROLLOUT_DIR

DEFAULT_LOCAL_SETTINGS_FILE=../../configs/local_settings.properties

prepare_service_superuser()
{
	local service_name=$1

	local SYSTEM_DEPLOY_SOURCE_FOLDER=${ROLLOUT_DIR}/deploy/services
	local SYSTEM_DEPLOY_TARGET_FOLDER=${METASFRESH_HOME}/${service_name}
  local service_isrunning=NOTSET

  echo "Copying start_metasfresh-webui-api.sh"
  cp -v ${ROLLOUT_DIR}/install/start_metasfresh-webui-api.sh ${METASFRESH_HOME}
  chmod -v 700 ${METASFRESH_HOME}/start_metasfresh-webui-api.sh

	local SYSTEM_SERVICE_FILE=/etc/systemd/system/${service_name}.service
	echo "Checking if $SYSTEM_SERVICE_FILE exists"
	if [[ ! -f $SYSTEM_SERVICE_FILE ]]; 
	then
		echo "!!! Installing service unit file !!!"
		cd $(pwd)

		echo ${SYSTEM_DEPLOY_SOURCE_FOLDER}/${service_name}-configs.zip
		
		# gh #1640: a service's artifact name might end with "-service" and we need to acomodate for that unzipping the configs file
		local CONFIGS_ZIP_FILENAME=NOTSET
		if [[ -f ${SYSTEM_DEPLOY_SOURCE_FOLDER}/${service_name}-configs.zip ]];
		then
			CONFIGS_ZIP_FILENAME=${SYSTEM_DEPLOY_SOURCE_FOLDER}/${service_name}-configs.zip
		elif [[ -f  ${SYSTEM_DEPLOY_SOURCE_FOLDER}/${service_name}-service-configs.zip ]];
		then
			CONFIGS_ZIP_FILENAME=${SYSTEM_DEPLOY_SOURCE_FOLDER}/${service_name}-service-configs.zip
		else
			echo "Unable to find a zip configs file!"
		fi
		
		unzip $CONFIGS_ZIP_FILENAME -d ./${service_name}-configs
		cp -v ./${service_name}-configs/configs/metasfresh.properties ${SYSTEM_DEPLOY_TARGET_FOLDER}/
		cp -v ./${service_name}-configs/configs/${service_name}.service ${SYSTEM_SERVICE_FILE}
		chmod 0644 ${SYSTEM_SERVICE_FILE}
		systemctl daemon-reload
		
		# if init.d file was previously running: start it to signal "minor_remote.sh" this service shall be
		# started after rollout
		#
		if [[ ${service_isrunning} = "yes" ]]; then
            systemctl start ${service_name}.service
            
            # metasfresh-webui-api is the ONLY service previously running using /etc/init.d/ script
            # we want metasfresh-webui-api to be in autostart (enabled) after a server reboot
            #
            if [[ ${service_name} = "metasfresh-webui-api" ]]; then
                systemctl enable ${service_name}.service
            fi
        fi
        
		echo "!!!  Done !!!"
	else
		echo "OK"
	fi

}

LOCAL_SETTINGS_FILE=$DEFAULT_LOCAL_SETTINGS_FILE

#parse the command line args (getopts doesn't work from inside a procedure)
while getopts "d:s:" OPTION; do
	case "$OPTION" in
		d)
			echo "$OPTION = $OPTARG"
			ROLLOUT_DIR="$OPTARG"
		;;		
		s)
			echo "$OPTION = $OPTARG"
			LOCAL_SETTINGS_FILE="$OPTARG"
	esac
done

source $LOCAL_SETTINGS_FILE

prepare_service_superuser metasfresh-webui-api
