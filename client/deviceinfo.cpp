#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <sys/sysinfo.h>

#include "deviceinfo.h"

bool DeviceInfo::update()
{
	if( !updateHostName() || !updateDomainName() || !updateUserName() 
		|| !updateSystemInfo() )
		return false;

	return true;
}

bool DeviceInfo::updateHostName()
{
	if( gethostname( hostName, sizeof( hostName ) ) == -1 )
		return false;

	printf( "Host name: %s\n", hostName );

	return true;
}

bool DeviceInfo::updateDomainName()
{
	if( getdomainname( domainName, sizeof( domainName ) ) == -1 )
		return false;

	printf( "Domain name: %s\n", domainName );

	return true;
}

bool DeviceInfo::updateUserName()
{
	if( getlogin_r( userName, sizeof( userName ) ) != 0 )
		return false;

	printf( "User name: %s\n", userName );

	return true;
}

bool DeviceInfo::updateSystemInfo()
{
	struct sysinfo info;

	if( sysinfo( &info ) == -1 )
		return false;

	systemInfo.uptime = info.uptime;
	systemInfo.totalRAM = info.totalram;
	systemInfo.freeRAM = info.freeram;
	systemInfo.numProcesses = info.procs;

	printf( "uptime: %ld\n", systemInfo.uptime );
	printf( "total RAM: %ld\n", systemInfo.totalRAM );
	printf( "free RAM: %ld\n", systemInfo.freeRAM );
	printf( "Num processes: %d\n", systemInfo.numProcesses );

	return true;
}
