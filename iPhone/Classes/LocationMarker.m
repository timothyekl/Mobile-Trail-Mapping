#import "LocationMarker.h"

@implementation LocationMarker

@synthesize coordinate;
@synthesize point;

-(id) initWithPoint: (TrailPoint*) point {
    
	self = [super init];
	if (self != nil) {
		self.point = point;
	}
	return self;
}

#pragma mark -
#pragma mark - Description methods



#pragma mark -
#pragma mark - Dealloc

- (void) dealloc {
    
	[point release];
	[super dealloc];
}

@end
