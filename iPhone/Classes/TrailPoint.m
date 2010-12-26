#import "TrailPoint.h"

@implementation TrailPoint

@synthesize name;
@synthesize description;
@synthesize latitude;
@synthesize longitude;

- (void) dealloc {
    
	[name release];
	[description release];
	[super dealloc];
}

@end
