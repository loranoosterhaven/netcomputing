#include <stdio.h>

#include "deviceinfo.h"

int main( int argc, char* argv[] )
{
	DeviceInfo* deviceInfo = new DeviceInfo();
	
	if( !deviceInfo->update() )
		printf( "Failed to update device information.\n" );

	return 0;
}
