#ifndef DEVICEINFO_H
#define DEVICEINFO_H

#include <stdlib.h>

struct SystemInfo
{
	long uptime;
	unsigned long totalRAM;
	unsigned long freeRAM;
	short numProcesses;
};

class DeviceInfo
{
public:
	bool update();

private:
	bool updateHostName();
	bool updateDomainName();
	bool updateUserName();
	bool updateSystemInfo();

private:
	char hostName[256];
	char domainName[256];
	char userName[256];
	SystemInfo systemInfo;
};

#endif